package tantalum.data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import tantalum.DatabaseException;
import tantalum.entities.Model;

public class InstanceUtility {
	public static List<Record> parseResultSet(ResultSet rs) {
		List<Record> rows = new ArrayList<Record>();
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();

			Set<String> columnNames = new HashSet<String>();
			for (int i = 1; i < numberOfColumns + 1; i++) {
				columnNames.add(rsMetaData.getColumnLabel(i));
			}

			while (rs.next()) {
				Record row = new Record(rs, columnNames);
				rows.add(row);
			}
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}

		return rows;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertToJSON(Model model, Map<Model, Store> stores) {
		Store store = stores.get(model);
		JSONObject jsonStore = new JSONObject();
		if (store.getCreates().size() > 0) {
			jsonStore.put(RecordAction.Create.toString().toLowerCase(), convertToJSON(store.getCreates()));
		}
		if (store.getUpdates().size() > 0) {
			jsonStore.put(RecordAction.Update.toString().toLowerCase(), convertToJSON(store.getUpdates()));
		}
		if (store.getDestroys().size() > 0) {
			jsonStore.put(RecordAction.Destroy.toString().toLowerCase(), convertToJSON(store.getDestroys()));
		}

		JSONObject json = new JSONObject();
		json.put(model.getName(), jsonStore);
		for (Model childModel : model.getChildModels()) {
			JSONObject jsonChild = convertToJSON(childModel, stores);
			json.putAll(jsonChild);
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONArray convertToJSON(List<Record> list) {
		JSONArray dataArray = new JSONArray();
		for (Record row : list) {
			JSONObject record = new JSONObject();
			dataArray.add(record);

			for (String fieldname : row.getFieldNames()) {
				record.put(fieldname, row.getString(fieldname));
			}
		}
		return dataArray;
	}

	public static Map<Model, Store> convertFromJSON(Model model, JSONObject jsonStores) {
		Map<Model, Store> storeData = new HashMap<Model, Store>();
		JSONObject modelJson = (JSONObject) jsonStores.get(model.getName());

		storeData.put(model, convertStoreFromJSON(modelJson));
		for (Model childModel : model.getChildModels()) {
			storeData.putAll(convertFromJSON(childModel, jsonStores));
		}
		return storeData;
	}

	private static Store convertStoreFromJSON(JSONObject jsonStore) {
		Store store = new Store();

		for (RecordAction action : RecordAction.values()) {
			JSONArray jsonRecords = (JSONArray) jsonStore.get(action.toString().toLowerCase());
			if (jsonRecords != null) {
				List<Record> list = new ArrayList<Record>();
				for (Object o : jsonRecords) {
					JSONObject row = (JSONObject) o;

					Record instance = new Record(row);
					instance.setAction(action);
					list.add(instance);
				}
				switch (action) {
				case Create:
					store.setCreates(list);
					break;
				case Update:
					store.setUpdates(list);
					break;
				case Destroy:
					store.setDestroys(list);
					break;
				}
			}
		}

		return store;
	}
}
