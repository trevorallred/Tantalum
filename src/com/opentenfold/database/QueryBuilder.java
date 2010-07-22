package com.opentenfold.database;

import com.opentenfold.model.AppField;
import com.opentenfold.model.AppView;

public class QueryBuilder {
	static public SelectSQL build(AppView view) {
		SelectSQL sql = new SelectSQL(view.getBasisTable().getDbName());
		for (AppField field : view.getFields()) {
			sql.addField("`" + field.getBasisColumn().getDbName() + "` AS '" + field.getName() + "'");
		}
		if (view.getResultsPerPage() > 0)
			sql.setLimit(view.getResultsPerPage());
		return sql;
	}
}
