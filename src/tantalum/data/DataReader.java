package tantalum.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tantalum.entities.Field;
import tantalum.entities.Model;
import tantalum.entities.ReferenceJoinClause;
import tantalum.util.DbConnection;
import tantalum.util.SelectSQL;
import tantalum.util.Strings;
import tantalum.util.UrlRequest;

public class DataReader {
	protected DbConnection db = new DbConnection();

	private Map<Model, List<Instance>> viewData = new HashMap<Model, List<Instance>>();

	public PageContent getContent(Model model, UrlRequest urlRequest) {
		String where = "";
		if (!Strings.isEmpty(urlRequest.getPageId())) {
			where = "t0." + model.getPrimaryKey().getBasisColumn().getDbName()
					+ " = '" + Strings.escapeQuotes(urlRequest.getPageId())
					+ "'";
		}
		if (urlRequest.getSelectorName() != null && urlRequest.getSelectorValue() != null) {
			Field field = model.getField(urlRequest.getSelectorName());
			if (field.getReference() != null)
				System.out.println("ERROR: We don't support ambiguous queries bases on non-basis fields yet. We'll try it anyway.");
			where =  "t0." + field.getBasisColumn().getDbName()
			+ " LIKE '%" + Strings.escapeQuotes(urlRequest.getSelectorValue())
			+ "%'";
		}
		queryData(model, where);

		PageContent content = new PageContent();
		for (Instance row : viewData.get(model)) {
			content.addChildContent(model, row);
			appendChildren(row, model);
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
