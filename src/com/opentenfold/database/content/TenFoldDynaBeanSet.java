package com.opentenfold.database.content;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opentenfold.database.DatabaseException;

public class TenFoldDynaBeanSet {
	private List<TenFoldDynaBean> rows = new ArrayList<TenFoldDynaBean>();

	public TenFoldDynaBeanSet(ResultSet rs) throws DatabaseException {
		rows.clear();
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();

			Set<String> columnNames = new HashSet<String>();
			for (int i = 1; i < numberOfColumns + 1; i++) {
				columnNames.add(rsMetaData.getColumnLabel(i));
			}

			while (rs.next()) {
				TenFoldDynaBean row = new TenFoldDynaBean(rs, columnNames);
				rows.add(row);
			}
		} catch (SQLException e) {
			throw new DatabaseException();
		}
	}

	public List<TenFoldDynaBean> getRows() {
		return rows;
	}

	public void setRows(List<TenFoldDynaBean> rows) {
		this.rows = rows;
	}

}
