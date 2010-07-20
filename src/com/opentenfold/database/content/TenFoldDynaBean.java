package com.opentenfold.database.content;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.opentenfold.database.DatabaseException;
import com.opentenfold.util.Strings;

public class TenFoldDynaBean {
	private Map<String, Object> values = new HashMap<String, Object>();

	public TenFoldDynaBean(ResultSet rs, Set<String> columnNames)
			throws DatabaseException {
		try {
			for (String columnName : columnNames) {
				values.put(columnName, rs.getObject(columnName));
			}
		} catch (SQLException e) {
			throw new DatabaseException();
		}
	}

	public void setValue(String columnName, String value) {
		values.put(columnName, value);
	}

	public Integer getInteger(String columnName) {
		String value = getString(columnName);
		if (Strings.isEmpty(value))
			return null;
		return Integer.parseInt(value);
	}

	public String getString(String columnName) {
		Object value = values.get(columnName);
		if (value == null)
			return null;
		return value.toString();
	}

	public Date getDate(String columnName) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(getString(columnName));
		} catch (ParseException e) {
		}
		return null;
	}

	public boolean getBoolean(String columnName) {
		if ("1".equals(getString(columnName)))
			return true;
		if ("true".equalsIgnoreCase(getString(columnName)))
			return true;
		if ("Y".equalsIgnoreCase(getString(columnName)))
			return true;
		if ("Yes".equalsIgnoreCase(getString(columnName)))
			return true;
		return false;
	}
}
