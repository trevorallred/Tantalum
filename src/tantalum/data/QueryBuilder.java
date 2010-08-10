package tantalum.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tantalum.entities.Field;
import tantalum.entities.JoinColumns;
import tantalum.entities.Reference;
import tantalum.entities.SortDirection;
import tantalum.entities.Model;
import tantalum.util.SelectSQL;

public class QueryBuilder {
	static public SelectSQL buildSelect(Model model) {
		SelectSQL sql = new SelectSQL(model.getBasisTable().getDbName() + " AS t0");
		int aliasCounter = 0;
		// TODO figure out smart way to order references based on dependencies
		for (Reference r : model.getReferences()) {
			aliasCounter++;
			r.setAlias(aliasCounter);
		}
		for (Reference r : model.getReferences()) {
			String join = "LEFT JOIN " + r.getJoin().getToTable().getDbName() + " AS t" + r.getAlias() + " ON ";
			String parentAlias = (r.getParent() == null ? "t0" : "t" + r.getParent().getAlias());
			for (JoinColumns jc : r.getJoin().getJoinColumns()) {
				join += parentAlias + "." + jc.getFromColumn().getDbName() + " = t" + r.getAlias() + "."
						+ jc.getToColumn().getDbName();
			}
			sql.addJoin(join);
		}
		List<OrderByClause> orderBys = new ArrayList<OrderByClause>();
		for (Field field : model.getFields()) {
			if (field.getBasisColumn() != null) {
				String alias = (field.getReference() == null ? "t0" : "t" + field.getReference().getAlias());
				sql.addField(alias + "." + field.getBasisColumn().getDbName() + " AS '" + field.getName() + "'");
				if (field.getSortOrder() != null) {
					orderBys.add(new OrderByClause(field));
				}
			}
		}
		Collections.sort(orderBys);
		for (OrderByClause orderByClause : orderBys) {
			sql.addOrderBy(orderByClause.getClause());
		}
		if (model.getResultsPerPage() > 0)
			sql.setLimit(model.getResultsPerPage());
		return sql;
	}

	static public class OrderByClause implements Comparable<OrderByClause> {
		public int order;
		public Field field;

		public OrderByClause(Field field) {
			this.field = field;
			order = field.getSortOrder();
		}

		public String getClause() {
			if (SortDirection.Descending.equals(field.getSortDirection()))
				return field.getName() + " " + SortDirection.Descending.getAbbreviation();
			return field.getName();
		}

		@Override
		public int compareTo(OrderByClause o) {
			return this.order - o.order;
		}
	}
}
