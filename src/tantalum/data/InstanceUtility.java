package tantalum.data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import tantalum.DatabaseException;

public class InstanceUtility {
	public static List<Instance> parseResultSet(ResultSet rs) {
		List<Instance> rows = new ArrayList<Instance>();
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();

			Set<String> columnNames = new HashSet<String>();
			for (int i = 1; i < numberOfColumns + 1; i++) {
				columnNames.add(rsMetaData.getColumnLabel(i));
			}

			while (rs.next()) {
				Instance row = new Instance(rs, columnNames);
				rows.add(row);
			}
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}

		return rows;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertToJSON(PageContent results) {
		JSONObject json = new JSONObject();
		for (String view : results.getViewNames()) {
			JSONObject jsonView = new JSONObject();
			json.put(view, jsonView);
			jsonView.put("POSITION", 0);
			// When we do lazy loading we'll set fullyLoaded this dynamically
			jsonView.put("FULLY_LOADED", true);
			JSONArray dataArray = new JSONArray();
			jsonView.put("DATA", dataArray);
			for (Instance row : results.getViewContent(view).getData()) {
				JSONObject record = new JSONObject();
				dataArray.add(record);

				JSONObject recordFields = new JSONObject();
				record.put("ACTION", null);
				record.put("DIRTY", row.isDirty());
				record.put("FIELDS", recordFields);
				for (String fieldname : row.getFieldNames()) {
					recordFields.put(fieldname, row.getString(fieldname));
				}

				record.put("CHILDREN", convertToJSON(row));
			}
		}
		return json;
	}

	public static InstanceList convertFromJSON(JSONObject json) {
		InstanceList list = new InstanceList();
		if (json == null)
			return list;
		JSONArray data = (JSONArray) json.get("DATA");
		if (data == null)
			return list;
		for (Object o : data) {
			JSONObject row = (JSONObject) o;
			JSONObject rowData = (JSONObject) row.get("FIELDS");

			Instance instance = new Instance(rowData);
			instance.setDirty(row.get("DIRTY").toString().equals("true"));
			Object action = row.get("ACTION");
			if (action != null)
				instance.setDelete(action.toString().equals("DELETE"));
			list.getData().add(instance);

			JSONObject children = (JSONObject) row.get("CHILDREN");
			for (Object childView : children.keySet()) {
				String childViewName = childView.toString();
				InstanceList childContent = convertFromJSON((JSONObject) children
						.get(childViewName));
				instance.addChildContent(childViewName, childContent);
			}
		}
		return list;
	}

}
