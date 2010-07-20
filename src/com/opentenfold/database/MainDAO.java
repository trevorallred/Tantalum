package com.opentenfold.database;

import com.opentenfold.database.content.TenFoldDynaBeanSet;
import com.opentenfold.model.Field;
import com.opentenfold.model.WebPage;
import com.opentenfold.util.Strings;
import com.opentenfold.util.UrlRequest;

public class MainDAO {
	protected DbConnection db = new DbConnection();

	public TenFoldDynaBeanSet getResults(WebPage page, UrlRequest request)
			throws DatabaseException {
		SelectSQL sql = QueryBuilder.build(page);
		if (page.getResultsPerPage() == 1) {
			sql.addWhere("id = '" + request.getPageId() + "'");
		} else {
			String[] orderbys = (String[]) request.getParameters().get(
					"orderby");
			if (orderbys != null) {
				for (String orderby : orderbys) {
					sql.addOrderBy(page.getField(orderby).getBasisColumn());
				}
			}
		}
		return db.select(sql.toString(), true);
	}

	public int getRowCount() {
		return db.getRowCount();
	}

	public void saveRequest(WebPage page, UrlRequest urlRequest) {
		boolean dirty = false;
		UpdateSQL sql = new UpdateSQL();
		sql.setTable(page.getBasisTable());
		for (String param : urlRequest.getParameters().keySet()) {
			if (!param.equalsIgnoreCase("button")) {
				Field field = page.getField(param);
				if (field != null) {
					dirty = true;
					sql.addField(field.getBasisColumn(), urlRequest.getParameters().get(param)[0]);
				}
			}
		}
		String id = urlRequest.getPageId();
		sql.setInsert(Strings.isEmpty(id));
		if (!sql.isInsert())
			sql.addWhere("id = '" + urlRequest.getPageId() + "'");
		
		if (dirty )
			db.execute(sql);
	}
}
