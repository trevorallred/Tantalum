package com.opentenfold.database.content;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opentenfold.database.DatabaseException;

public class PageContentUtility {
	public static List<PageContentBean> parseResultSet(ResultSet rs) {
		List<PageContentBean> rows = new ArrayList<PageContentBean>();
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();

			Set<String> columnNames = new HashSet<String>();
			for (int i = 1; i < numberOfColumns + 1; i++) {
				columnNames.add(rsMetaData.getColumnLabel(i));
			}

			while (rs.next()) {
				PageContentBean row = new PageContentBean(rs, columnNames);
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
			for (PageContentBean row : results.getViewContent(view).getData()) {
				JSONObject record = new JSONObject();
				dataArray.add(record);
				
				JSONObject recordFields = new JSONObject();
				record.put("FIELDS", recordFields);
				for (String fieldname : row.getFieldNames()) {
					recordFields.put(fieldname, row.getString(fieldname));
				}
				
				record.put("CHILDREN", convertToJSON(row));
			}
		}
		return json;
	}

}
