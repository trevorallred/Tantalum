package com.opentenfold.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opentenfold.database.content.PageContentBean;
import com.opentenfold.model.BaseTable;
import com.opentenfold.model.Column;
import com.opentenfold.model.Field;
import com.opentenfold.model.Reference;
import com.opentenfold.model.Table;
import com.opentenfold.model.View;
import com.opentenfold.model.WebPage;

public class PageDAO extends MainDAO {
	private Map<String, Map<Integer, BaseTable>> cache = new HashMap<String, Map<Integer, BaseTable>>();

	private BaseTable getCache(String className, Integer id) {
		Map<Integer, BaseTable> objectList = cache.get(className);
		if (objectList == null)
			return null;

		return objectList.get(id);
	}

	private BaseTable putCache(BaseTable object) {
		if (object == null || object.getId() == 0)
			return null;
		Map<Integer, BaseTable> objectList = cache.get(object.getClass().getSimpleName());
		if (objectList == null) {
			objectList = new HashMap<Integer, BaseTable>();
			cache.put(object.getClass().getSimpleName(), objectList);
		}
		BaseTable existing = getCache(object.getClass().getSimpleName(), object
				.getId());
		if (existing != null)
			return existing;
		objectList.put(object.getId(), object);
		return object;
	}

	public WebPage getWebPageDefinition(String pageName) {
		System.out.println("Getting page definition for " + pageName);
		WebPage page = new WebPage();
		{
			SelectSQL sql = new SelectSQL("dd_webpage t");
			sql.addWhere("t.url = '" + pageName + "'");
			PageContentBean row;
			try {
				row = db.selectSingle(sql);
			} catch (NoResultFoundException e) {
				return null;
			}

			page.setId(row.getInteger("id"));
			page.setUrl(row.getString("url"));
			page.setTitle(row.getString("title"));
			page.setKeyField(new Field(row.getInteger("keyFieldID")));
		}
		String viewIDs = "0";
		{
			SelectSQL sql = new SelectSQL("dd_view t");
			sql.addField("t.*");
			sql.addJoin("LEFT JOIN dd_table bt ON t.basisTableID = bt.id");
			sql.addField("bt.dbName AS tableDbName");
			sql.addWhere("t.pageID = " + page.getId());
			sql.addOrderBy("t.parentID");
			List<PageContentBean> views = db.select(sql);

			for (PageContentBean row : views) {
				View view = new View();
				view.setId(row.getInteger("id"));
				view = (View) putCache(view);
				view.setName(row.getString("name"));
				view.setResultsPerPage(row.getInteger("resultsPerPage"));
				view.setBasisTable(row.getString("tableDbName"));
				view.setParentID(row.getInteger("parentID"));
				view.setReferenceID(row.getInteger("referenceID"));
				page.getViews().add(view);
				viewIDs += ", " + view.getId();
			}
		}
		if (cache.get("View").size() == 0)
			return page;
		{
			SelectSQL sql = new SelectSQL("dd_reference d");
			sql.addField("d.*");
			sql.addJoin("JOIN dd_join j ON d.joinID = j.id");
			sql.addJoin("JOIN dd_table t ON t.id = j.toTableID");
			sql.addField("t.dbName", "tableDbName");
			sql.addJoin("JOIN dd_join_column jc ON j.id = jc.joinID");
			sql.addJoin("LEFT JOIN dd_column fc ON fc.id = jc.fromColumnID");
			sql.addField("fc.dbName", "fromColumnDbName");
			sql.addJoin("LEFT JOIN dd_column tc ON tc.id = jc.toColumnID");
			sql.addField("tc.dbName", "toColumnDbName");
			sql.addWhere("d.viewID IN (" + viewIDs + ")");
			sql.addOrderBy("d.queryOrder, d.parentID");
			List<PageContentBean> references = db.select(sql);
			for (PageContentBean row : references) {
				Reference ref = (Reference) putCache(new Reference(row
						.getInteger("id")));

				ref.setName(row.getString("name"));
				ref.setView((View) getCache("View", row.getInteger("viewID")));
				ref.setParent((Reference) putCache(new Reference(row
						.getInteger("parentID"))));
				ref.setTableDbName(row.getString("tableDbName"));
				ref.setFromColumnDbName(row.getString("fromColumnDbName"));
				// ref.setToColumnDbName(row.getString("toColumnDbName"));
			}
		}
		{
			SelectSQL sql = new SelectSQL("dd_field d");
			sql.addField("d.*");
			sql.addJoin("LEFT JOIN dd_column bc ON d.basisColumnID = bc.id");
			sql.addField("bc.id", "bc_id");
			sql.addField("bc.dbName", "bc_dbName");
			sql.addJoin("LEFT JOIN dd_table bt ON bc.tableID = bt.id");
			sql.addField("bt.id", "bt_id");
			sql.addField("bt.dbName", "bt_dbName");

			sql.addJoin("LEFT JOIN dd_field lf ON d.linkToFieldID = lf.id");
			sql.addJoin("LEFT JOIN dd_view lv ON lf.viewID = lv.id");
			sql.addJoin("LEFT JOIN dd_webpage lp ON lv.pageID = lp.id");
			sql.addField("lp.url", "linkToUrl");

			sql.addWhere("d.viewID IN (" + viewIDs + ")");
			sql.addOrderBy("d.displayOrder");
			List<PageContentBean> fields = db.select(sql);

			for (PageContentBean row : fields) {
				Field field = (Field) putCache(new Field(row.getInteger("id")));
				field.setViewID(row.getInteger("viewID"));
				field.setName(row.getString("name"));
				field.setLabel(row.getString("label"));
				field.setVisible(row.getBoolean("visible"));
				field.setEditable(row.getBoolean("editable"));
				field.setSearchable(row.getBoolean("searchable"));
				Integer basisColumnID = row.getInteger("bc_id");
				if (basisColumnID != null) {
					Column column = new Column();
					column.setId(basisColumnID);
					column = (Column) putCache(column);
					column.setDbName(row.getString("bc_dbName"));
					field.setBasisColumn(column);

					Table table = new Table();
					table.setId(row.getInteger("bt_id"));
					table = (Table) putCache(table);
					table.setDbName(row.getString("bt_dbName"));
					column.setTable(table);
				}
				Integer linkFromFieldID = row.getInteger("linkFromFieldID");
				if (linkFromFieldID != null) {
					field.setLinkFromField((Field) putCache(new Field(
							linkFromFieldID)));
					field.setLinkToUrl(row.getString("linkToUrl"));
				}

				page.addFieldToView(field);
			}
		}

		return page;
	}
}
