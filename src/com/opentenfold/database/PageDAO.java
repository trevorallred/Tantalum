package com.opentenfold.database;

import com.opentenfold.database.content.TenFoldDynaBean;
import com.opentenfold.database.content.TenFoldDynaBeanSet;
import com.opentenfold.model.Field;
import com.opentenfold.model.WebPage;

public class PageDAO extends MainDAO {
	public WebPage getWebPageDefinition(String pageName) throws Exception {
		WebPage page = new WebPage();
		{
			SelectSQL sql = new SelectSQL("dd_webpage t");
			sql.addJoin("LEFT JOIN dd_table bt ON t.basisTableID = bt.id");
			sql.addField("t.*");
			sql.addField("bt.dbName AS tableDbName");
			sql.addWhere("t.url = '" + pageName + "'");
			TenFoldDynaBean row = db.selectSingle(sql);

			page.setId(row.getInteger("id"));
			page.setName(row.getString("name"));
			page.setUrl(row.getString("url"));
			page.setTitle(row.getString("title"));
			page.setResultsPerPage(row.getInteger("resultsPerPage"));
			page.setBasisTable(row.getString("tableDbName"));
		}
		{
			SelectSQL sql = new SelectSQL("dd_field d");
			sql.addField("d.*");
			sql.addJoin("LEFT JOIN dd_column bc ON d.basisColumnID = bc.id");
			sql.addField("bc.dbName columnDbName");
			sql.addJoin("LEFT JOIN dd_table bt ON bc.tableID = bt.id");
			sql.addField("bt.dbName tableDbName");
			sql.addWhere("d.pageID = " + page.getId());
			sql.addOrderBy("d.displayOrder");
			TenFoldDynaBeanSet fields = db.select(sql);

			for (TenFoldDynaBean row : fields.getRows()) {
				Field field = new Field();
				field.setId(row.getInteger("id"));
				field.setName(row.getString("name"));
				field.setLabel(row.getString("label"));
				field.setVisible(row.getBoolean("visible"));
				field.setEditable(row.getBoolean("editable"));
				field.setSearchable(row.getBoolean("searchable"));
				Integer basisColumnID = row.getInteger("basisColumnID");
				if (basisColumnID != null) {
					field.setBasisTable(row.getString("tableDbName"));
					field.setBasisColumn(row.getString("columnDbName"));
				}
				page.getFields().add(field);
			}
		}

		return page;
	}
}
