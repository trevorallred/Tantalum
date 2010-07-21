package com.opentenfold.database.content;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opentenfold.database.DatabaseException;

public class PageContent {
	private Map<String, List<PageContentBean>> data = new HashMap<String, List<PageContentBean>>();

	public void addViewContent(String viewName, List<PageContentBean> list) {
		data.put(viewName, list);
	}

	public List<PageContentBean> getRows(String viewName) {
		return data.get(viewName);
	}

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
}
