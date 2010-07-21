package com.opentenfold.database;

import java.util.List;

import com.opentenfold.database.content.PageContentBean;
import com.opentenfold.model.Field;
import com.opentenfold.model.View;
import com.opentenfold.util.Strings;
import com.opentenfold.util.UrlRequest;

public class MainDAO {
	protected DbConnection db = new DbConnection();

	private SelectSQL sql = null;

	public void setView(View view) {
		System.out.println("Getting results for " + view.getBasisTable());
		sql = QueryBuilder.build(view);
	}
	
	public List<PageContentBean> getResults() {
		return db.select(sql.toString(), true);
	}

	public int getRowCount() {
		return db.getRowCount();
	}

	public void saveRequest(View view, UrlRequest urlRequest) {
		boolean dirty = false;
		UpdateSQL sql = new UpdateSQL();
		sql.setTable(view.getBasisTable());
		for (String param : urlRequest.getParameters().keySet()) {
			if (!param.equalsIgnoreCase("button")) {
				Field field = view.getField(param);
				if (field != null) {
					dirty = true;
					sql.addField(field.getBasisColumn(), urlRequest
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

	public SelectSQL getSql() {
		return sql;
	}

}
