package com.opentenfold.database;

import com.opentenfold.model.Field;
import com.opentenfold.model.View;

public class QueryBuilder {
	static public SelectSQL build(View view) {
		SelectSQL sql = new SelectSQL(view.getBasisTable());
		for (Field field : view.getFields()) {
			sql.addField("`" + field.getBasisColumn().getDbName() + "` AS '" + field.getName() + "'");
		}
		if (view.getResultsPerPage() > 0)
			sql.setLimit(view.getResultsPerPage());
		return sql;
	}
}
