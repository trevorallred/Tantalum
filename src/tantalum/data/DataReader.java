package tantalum.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tantalum.entities.Model;
import tantalum.entities.ReferenceJoinClause;
import tantalum.util.DbConnection;
import tantalum.util.RequestFilter;
import tantalum.util.SelectSQL;
import tantalum.util.Strings;

public class DataReader {
	protected DbConnection db = new DbConnection();
	private Map<Model, List<Record>> content;
	private Map<Model, Integer> contentCount;

	public Map<Model, List<Record>> getContent(Model model, RequestFilter filter) {
		content = new HashMap<Model, List<Record>>();
		contentCount = new HashMap<Model, Integer>();

		String where = "";
		/*
		 * if (!Strings.isEmpty(urlRequest.getPageId())) { where = "t0." +
		 * model.getPrimaryKey().getBasisColumn().getDbName() + " = '" + Strings.escapeQuotes(urlRequest.getPageId()) +
		 * "'"; } if (urlRequest.getSelectorName() != null && urlRequest.getSelectorValue() != null) { Field field =
		 * model.getField(urlRequest.getSelectorName()); if (field.getReference() != null) System.out
		 * .println("ERROR: We don't support ambiguous queries bases on non-basis fields yet. We'll try it anyway.");
		 * where = "t0." + field.getBasisColumn().getDbName() + " LIKE '%" +
		 * Strings.escapeQuotes(urlRequest.getSelectorValue()) + "%'"; }
		 */
		queryData(model, where);
		return content;
	}

	/**
	 * Query each views data and put into a list. Later we'll organize it by PageContentBean
	 * 
	 * @param model
	 * @param where
	 */
	private void queryData(Model model, String where) {
		SelectSQL sql = QueryBuilder.buildSelect(model);
		if (!Strings.isEmpty(where))
			sql.addWhere(where);
		List<Record> data = db.select(sql.toString(), true);
		content.put(model, data);
		contentCount.put(model, db.getRowCount());

		if (data.size() > 0) {
			// We could just iterate all the views in a page, but this wouldn't
			// ensure we have the parent data for the child in clause
			for (Model childView : model.getChildModels()) {
				if (childView.getReference() == null)
					System.out.println("WARNING: skipping ChildModel " + childView.getName()
							+ ". It's missing its reference.");
				else {
					System.out.println("Reading data for " + childView.getName() + " child of " + model.getName()
							+ " with reference " + childView.getReference().getName());
					StringBuilder childWhere = new StringBuilder();
					for (ReferenceJoinClause rjc : childView.getReference().getReferenceJoinClauses()) {
						if (childWhere.length() > 0)
							childWhere.append(" AND ");
						childWhere.append("t0.").append(rjc.getFromColumn().getDbName()).append(" IN (");
						StringBuilder parentIDs = new StringBuilder();
						for (Record parentRow : data) {
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
		}
	}

	public List<Record> getContent(Model model) {
		return content.get(model);
	}

	public Integer getContentCount(Model model) {
		return contentCount.get(model);
	}
}
