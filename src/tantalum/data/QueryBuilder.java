package tantalum.data;

import tantalum.entities.AppField;
import tantalum.entities.AppJoinColumn;
import tantalum.entities.AppReference;
import tantalum.entities.AppView;
import tantalum.util.SelectSQL;

public class QueryBuilder {
	static public SelectSQL build(AppView view) {
		SelectSQL sql = new SelectSQL(view.getBasisTable().getDbName()
				+ " AS t0");
		int aliasCounter = 0;
		// TODO figure out smart way to order references based on dependencies
		for (AppReference r : view.getReferences()) {
			aliasCounter++;
			r.setAlias(aliasCounter);
		}
		for (AppReference r : view.getReferences()) {
			String join = "LEFT JOIN " + r.getJoin().getToTable().getDbName()
					+ " AS t" + r.getAlias() + " ON ";
			String parentAlias = (r.getParent() == null ? "t0" : "t"
					+ r.getParent().getAlias());
			for (AppJoinColumn jc : r.getJoin().getJoinColumns()) {
				join += parentAlias + "." + jc.getFromColumn().getDbName()
						+ " = t" + r.getAlias() + "."
						+ jc.getToColumn().getDbName();
			}
			sql.addJoin(join);
		}
		for (AppField field : view.getFields()) {
			String alias = (field.getReference() == null ? "t0" : "t"
					+ field.getReference().getAlias());
			sql.addField(alias + "." + field.getBasisColumn().getDbName()
					+ " AS '" + field.getName() + "'");
		}
		if (view.getResultsPerPage() > 0)
			sql.setLimit(view.getResultsPerPage());
		return sql;
	}
}
