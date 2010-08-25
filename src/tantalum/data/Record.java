package tantalum.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import tantalum.DatabaseException;
import tantalum.entities.Field;
import tantalum.entities.Model;
import tantalum.util.Strings;

/**
 * A single line returned in a query. This was modeled after Ext.data.Record
 */
public class Record {
	private Map<String, Object> data = new HashMap<String, Object>();
	private Model view = null;
	private RecordAction action = null;

	public Record() {
	}

	public Record(ResultSet rs, Set<String> columnNames) throws DatabaseException {
		try {
			for (String columnName : columnNames) {
				data.put(columnName, rs.getObject(columnName));
			}
		} catch (SQLException e) {
			throw new DatabaseException();
		}
	}

	public Record(JSONObject json) {
		for (Object key : json.keySet()) {
			Object o = json.get(key);
			if (o == null)
				data.put(key.toString(), null);
			else
				data.put(key.toString(), o.toString());
		}
	}

	public void setValue(String columnName, String value) {
		data.put(columnName, value);
	}

	public Set<String> getFieldNames() {
		return data.keySet();
	}

	public Integer getInteger(String columnName) {
		String value = getString(columnName);
		if (Strings.isEmpty(value))
			return null;
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			System.out.println("Failed to convert " + value + " into Integer for " + columnName);
		}
		return null;
	}

	public String getString(Field field) {
		return getString(field.getName());
	}

	public String getStringForDB(Field field) {
		switch (field.getBasisColumn().getColumnType()) {
		case Integer:
			Integer value = getInteger(field.getName());
			if (value == null)
				return null;
			return value.toString();
		case AutoIncrement:
			return getInteger(field.getName()).toString();
		case Boolean:
			// Remember to support Y/N as well somehow
			return getBoolean(field.getName()) ? "1" : "0";
		}
		return getString(field.getName());
	}

	public String getString(String columnName) {
		if (columnName == null) {
			System.out.println("WARNING!! columnName is NULL");
			return null;
		}
		Object value = data.get(columnName);
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

	public Model getView() {
		return view;
	}

	public void setView(Model view) {
		this.view = view;
	}

	public RecordAction getAction() {
		return action;
	}

	public void setAction(RecordAction action) {
		this.action = action;
	}

}
