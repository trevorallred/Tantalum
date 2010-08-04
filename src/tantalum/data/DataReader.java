package tantalum.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tantalum.entities.Model;
import tantalum.entities.ReferenceJoinClause;
import tantalum.util.DbConnection;
import tantalum.util.SelectSQL;
import tantalum.util.Strings;
import tantalum.util.UrlRequest;

public class DataReader {
	protected DbConnection db = new DbConnection();

	private Map<Model, List<Instance>> viewData = new HashMap<Model, List<Instance>>();

	public PageContent getContent(Model view, UrlRequest urlRequest) {
		String where = "";
		if (!Strings.isEmpty(urlRequest.getPageId()))
			// TODO injection on pageID, clean it up
			where = "t0." + view.getPrimaryKey().getBasisColumn().getDbName()
					+ " = '" + Strings.escapeQuotes(urlRequest.getPageId())
					+ "'";
		queryData(view, where);

		PageContent content = new PageContent();
		for (Instance row : viewData.get(view)) {
			content.addChildContent(view, row);
			appendChildren(row, view);
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
	private void queryData(Model view, String where) {
		SelectSQL sql = QueryBuilder.buildSelect(view);
		if (!Strings.isEmpty(where))
			sql.addWhere(where);
		List<Instance> data = db.select(sql.toString(), true);
		viewData.put(view, data);
		// We could just iterate all the views in a page, but this wouldn't
		// ensure we have the parent data for the child in clause
		for (Model childView : view.getChildModels()) {
			System.out.println("Reading data for " + childView.getName()
					+ " child of " + view.getName() + " with reference "
					+ childView.getReference());
			StringBuilder childWhere = new StringBuilder();
			for (ReferenceJoinClause rjc : childView.getReference()
					.getReferenceJoinClauses()) {
				if (childWhere.length() > 0)
					childWhere.append(" AND ");
				childWhere.append("t0.")
						.append(rjc.getFromColumn().getDbName())
						.append(" IN (");
				StringBuilder parentIDs = new StringBuilder();
				for (Instance parentRow : data) {
					if (parentIDs.length() > 0)
						parentIDs.append(",");
					parentIDs.append("'");
					parentIDs.append(Strings.escapeQuotes(parentRow.getString(rjc.getToField().getName())));
					parentIDs.append("'");
				}
				childWhere.append(parentIDs).append(")");
			}
			queryData(childView, childWhere.toString());
		}
	}

	private void appendChildren(Instance parentContent, Model view) {
		for (Model childView : view.getChildModels()) {
			List<String> parentKey = new ArrayList<String>();
			for (ReferenceJoinClause rjc : childView.getReference()
					.getReferenceJoinClauses()) {
				parentKey.add(parentContent.getString(rjc.getToField()));
			}
			for (Instance childContent : viewData.get(childView)) {
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

}
