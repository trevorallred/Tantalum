package tantalum.data;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import tantalum.entities.Field;
import tantalum.entities.JoinColumns;
import tantalum.entities.Reference;
import tantalum.entities.View;
import tantalum.util.SelectSQL;
import tantalum.util.UpdateSQL;

public class QueryBuilder {
	static public SelectSQL buildSelect(View view) {
		SelectSQL sql = new SelectSQL(view.getBasisTable().getDbName()
				+ " AS t0");
		int aliasCounter = 0;
		// TODO figure out smart way to order references based on dependencies
		for (Reference r : view.getReferences()) {
			aliasCounter++;
			r.setAlias(aliasCounter);
		}
		for (Reference r : view.getReferences()) {
			String join = "LEFT JOIN " + r.getJoin().getToTable().getDbName()
					+ " AS t" + r.getAlias() + " ON ";
			String parentAlias = (r.getParent() == null ? "t0" : "t"
					+ r.getParent().getAlias());
			for (JoinColumns jc : r.getJoin().getJoinColumns()) {
				join += parentAlias + "." + jc.getFromColumn().getDbName()
						+ " = t" + r.getAlias() + "."
						+ jc.getToColumn().getDbName();
			}
			sql.addJoin(join);
		}
		for (Field field : view.getFields()) {
			String alias = (field.getReference() == null ? "t0" : "t"
					+ field.getReference().getAlias());
			sql.addField(alias + "." + field.getBasisColumn().getDbName()
					+ " AS '" + field.getName() + "'");
		}
		if (view.getResultsPerPage() > 0)
			sql.setLimit(view.getResultsPerPage());
		return sql;
	}
	
	static public List<UpdateSQL> buildUpdates(View view, Object raw) {
		if (raw == null)
			return null;
		JSONObject json = (JSONObject)raw;
		JSONArray data = (JSONArray)json.get("DATA");
		for (Object o : data) {
			JSONObject row = (JSONObject)o;
			JSONObject rowData = (JSONObject)row.get("FIELDS");
			// Now save or update this row Data
			UpdateSQL sql = new UpdateSQL();
			for (Field field : view.getFields()) {
				if (rowData.containsKey(field.getName()))
					sql.addField(field.getName(), rowData.get(field.getName()).toString());
			}
			
			JSONObject children = (JSONObject)row.get("CHILDREN");
			for (View childView : view.getChildViews()) {
				buildUpdates(childView, children.get(childView.getName()));
			}
		}
		return null;
	}

}
