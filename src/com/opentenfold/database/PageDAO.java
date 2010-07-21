package com.opentenfold.database;

import com.opentenfold.database.content.TenFoldDynaBean;
import com.opentenfold.database.content.TenFoldDynaBeanSet;
import com.opentenfold.model.Field;
import com.opentenfold.model.View;
import com.opentenfold.model.WebPage;

public class PageDAO extends MainDAO {
	public WebPage getWebPageDefinition(String pageName) throws Exception {
		System.out.println("Getting page definition for " + pageName);
		WebPage page = new WebPage();
		{
			SelectSQL sql = new SelectSQL("dd_webpage t");
			sql.addWhere("t.url = '" + pageName + "'");
			TenFoldDynaBean row = db.selectSingle(sql);

			page.setId(row.getInteger("id"));
			page.setUrl(row.getString("url"));
			page.setTitle(row.getString("title"));
			page.setKeyField(new Field(row.getInteger("keyFieldID")));
		}
		{
			SelectSQL sql = new SelectSQL("dd_view t");
			sql.addField("t.*");
			sql.addJoin("LEFT JOIN dd_table bt ON t.basisTableID = bt.id");
			sql.addField("bt.dbName AS tableDbName");
			sql.addWhere("t.pageID = " + page.getId());
			TenFoldDynaBeanSet views = db.select(sql);

			for (TenFoldDynaBean row : views.getRows()) {
				View view = new View();
				view.setId(row.getInteger("id"));
				view.setName(row.getString("name"));
				view.setResultsPerPage(row.getInteger("resultsPerPage"));
				view.setBasisTable(row.getString("tableDbName"));
				view.setParentID(row.getInteger("resultsPerPage"));
				page.getViews().add(view);
			}
		}
		{
			String viewIDs = "0";
			for (View view : page.getViews()) {
				viewIDs += ", " + view.getId();
			}
			
			SelectSQL sql = new SelectSQL("dd_field d");
			sql.addField("d.*");
			sql.addJoin("LEFT JOIN dd_column bc ON d.basisColumnID = bc.id");
			sql.addField("bc.dbName", "columnDbName");
			sql.addJoin("LEFT JOIN dd_table bt ON bc.tableID = bt.id");
			sql.addField("bt.dbName", "tableDbName");

			sql.addJoin("LEFT JOIN dd_field lf ON d.linkToFieldID = lf.id");
			sql.addJoin("LEFT JOIN dd_view lv ON lf.viewID = lv.id");
			sql.addJoin("LEFT JOIN dd_webpage lp ON lv.pageID = lp.id");
			sql.addField("lp.url", "linkToUrl");

			sql.addWhere("d.viewID IN (" + viewIDs + ")");
			sql.addOrderBy("d.displayOrder");
			TenFoldDynaBeanSet fields = db.select(sql);

			for (TenFoldDynaBean row : fields.getRows()) {
				Field field = new Field(row.getInteger("id"));
				field.setViewID(row.getInteger("viewID"));
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
				field.setLinkFromFieldID(row.getInteger("linkFromFieldID"));
				field.setLinkToUrl(row.getString("linkToUrl"));
				
				page.addFieldToView(field);
			}
		}

		return page;
	}
}
