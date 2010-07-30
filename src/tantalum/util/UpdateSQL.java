package tantalum.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class UpdateSQL {
	protected String table;
	protected List<String> whereClause = new ArrayList<String>();
	protected Map<String, String> fields = new HashMap<String, String>();
	protected int limit = -1;
	protected boolean insert = false;

	public UpdateSQL(String dbName) {
		setTable(dbName);
	}

	/**
	 * Return the sql clause in this format:<br>
	 * <br>
	 * UPDATE {table} SET {field='<String>'} [,{field='<String>'}...] [WHERE
	 * {whereClause}] [LIMIT {limit}]<br>
	 * OR<br>
	 * INSERT INTO {table} SET {fields<String>}
	 */
	public String toString() {
		StringBuffer sql = new StringBuffer();

		if (insert) {
			sql.append("INSERT INTO ");
		} else {
			sql.append("UPDATE ");
		}
		sql.append(table);
		sql.append(" SET ");

		boolean first = true;
		for (String field : fields.keySet()) {
			if (first)
				first = false;
			else
				sql.append(", ");
			
			String value = fields.get(field);
			sql.append("\n").append(field).append(" = ");
			if (value == null)
				sql.append("NULL");
			else if (value.equals("NOW()"))
				sql.append("NOW()");
			else
				sql.append("'").append(Strings.escapeQuotes(value)).append("'");
		}

		if (!insert) {
			if (whereClause.size() > 0) {
				sql.append("\nWHERE 1");
				for (String whereSQL : this.whereClause) {
					sql.append("\n AND (");
					sql.append(whereSQL);
					sql.append(") ");
				}
			}

			if (this.limit > 0)
				sql.append("\nLIMIT ").append(this.limit);
		}
		return sql.toString();
	}

	public boolean isInsert() {
		return insert;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void addWhere(String whereClause) {
		if (whereClause != null && whereClause.length() > 0)
			this.whereClause.add(whereClause);
	}

	public void addField(String field, String value) {
		if (Strings.isEmpty(field))
			return;
		this.fields.put(field, value);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}