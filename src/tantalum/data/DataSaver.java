package tantalum.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import tantalum.entities.AppField;
import tantalum.entities.AppPage;
import tantalum.entities.AppView;
import tantalum.entities.ReferenceJoinClause;
import tantalum.util.DbConnection;
import tantalum.util.SelectSQL;
import tantalum.util.Strings;
import tantalum.util.UpdateSQL;
import tantalum.util.UrlRequest;


public class DataSaver {
	protected DbConnection db = new DbConnection();

	private Map<AppView, List<PageContentBean>> viewData = new HashMap<AppView, List<PageContentBean>>();

	public PageContent getContent(AppPage page, UrlRequest urlRequest) {
		for (AppView view : page.getParentViews()) {
			String where = "";
			if (!Strings.isEmpty(urlRequest.getPageId()))
				// TODO injection on pageID, clean it up
				where = "t0."
						+ view.getPrimaryKey().getBasisColumn().getDbName()
						+ " = '" + Strings.escapeQuotes(urlRequest.getPageId())
						+ "'";
			queryData(view, where);
		}

		PageContent content = new PageContent();
		for (AppView view : page.getParentViews()) {
			for (PageContentBean row : viewData.get(view)) {
				content.addChildContent(view, row);
				appendChildren(row, view);
			}
		}
		return content;
	}

	/**
	 * Query each views data and put into a list. Later we'll organize it by
	 * PageContentBean
	 * 
	 * @param view
	 * @param where
	 */
	private void queryData(AppView view, String where) {
		SelectSQL sql = QueryBuilder.buildSelect(view);
		if (!Strings.isEmpty(where))
			sql.addWhere(where);
		List<PageContentBean> data = db.select(sql.toString(), true);
		viewData.put(view, data);
		// We could just iterate all the views in a page, but this wouldn't
		// ensure we have the parent data for the child in clause
		for (AppView childView : view.getChildViews()) {
			StringBuilder childWhere = new StringBuilder();
			for (ReferenceJoinClause rjc : childView.getReference()
					.getReferenceJoinClauses()) {
				if (childWhere.length() > 0)
					childWhere.append(" AND ");
				childWhere.append("t0.")
						.append(rjc.getFromColumn().getDbName())
						.append(" IN (");
				Set<String> parentIDs = new HashSet<String>();
				for (PageContentBean parentRow : data) {
					parentIDs.add(parentRow.getString(rjc.getToField()
							.getName()));
				}
				childWhere.append(Strings.joinForDB(parentIDs)).append(")");
			}
			queryData(childView, childWhere.toString());
		}
	}

	private void appendChildren(PageContentBean parentContent, AppView view) {
		for (AppView childView : view.getChildViews()) {
			List<String> parentKey = new ArrayList<String>();
			for (ReferenceJoinClause rjc : childView.getReference()
					.getReferenceJoinClauses()) {
				parentKey.add(parentContent.getString(rjc.getToField()));
			}
			for (PageContentBean childContent : viewData.get(childView)) {
				// TODO We should consider storing this childKey on the
				// PageContentBean
				List<String> childKey = new ArrayList<String>();
				for (ReferenceJoinClause rjc : childView.getReference()
						.getReferenceJoinClauses()) {
					childKey.add(childContent.getString(rjc.getFromField()));
				}
				if (parentKey.equals(childKey)) {
					// This child row "belongs" to this parent
					parentContent.addChildContent(childView, childContent);
					appendChildren(childContent, childView);
				}
			}
		}
	}

	public void saveRequest(AppView view, UrlRequest urlRequest) {
		boolean dirty = false;
		UpdateSQL sql = new UpdateSQL();
		sql.setTable(view.getBasisTable().getDbName());
		for (String param : urlRequest.getParameters().keySet()) {
			if (!param.equalsIgnoreCase("button")) {
				AppField field = view.getField(param);
				if (field != null) {
					dirty = true;
					sql.addField(field.getBasisColumn().getDbName(), urlRequest
							.getParameters().get(param)[0]);
				}
			}
		}
		String id = urlRequest.getPageId();
		sql.setInsert(Strings.isEmpty(id));
		if (!sql.isInsert())
			sql.addWhere("id = '" + urlRequest.getPageId() + "'");

		if (dirty)
			db.execute(sql);
	}

}
