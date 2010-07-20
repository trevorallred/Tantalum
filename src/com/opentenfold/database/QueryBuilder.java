package com.opentenfold.database;

import com.opentenfold.model.Field;
import com.opentenfold.model.WebPage;

public class QueryBuilder {
	static public SelectSQL build(WebPage page) {
		SelectSQL sql = new SelectSQL(page.getBasisTable());
		for (Field field : page.getFields()) {
			sql.addField("`" + field.getBasisColumn() + "` AS '" + field.getName() + "'");
		}
		sql.setLimit(page.getResultsPerPage());
		return sql;
	}
}
