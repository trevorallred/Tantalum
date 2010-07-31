package tantalum.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InsertSQL {
	static public String TIMESTAMP = "NOW()";
	protected String table;
	protected Set<String> fields = new HashSet<String>();
	protected List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	/**
	 * Return the sql clause in this format:<br>
	 * <br>
	 * INSERT INTO {table} (field[, field...]) VALUES
	 * (value[, value])[, (value[, value])]
	 */
	public String toString() {
		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO `");
		sql.append(table.trim());
		sql.append("`\n(");

		boolean first = true;
		for (String field : fields) {
			if (first)
				first = false;
			else
				sql.append(", ");
			sql.append("`").append(field.trim()).append("`");
		}
		sql.append(")\nVALUES");

		boolean firstRow = true;
		for (Map<String, String> row : data) {
			if (firstRow)
				firstRow = false;
			else
				sql.append(", ");
			
			sql.append("\n(");
			first = true;
			for (String field : fields) {
				if (first)
					first = false;
				else
					sql.append(", ");
				
				String value = row.get(field);
				if (value == null)
					sql.append("NULL");
				else if (value.equals(TIMESTAMP))
					sql.append("NOW()");
				else
					sql.append("'").append(Strings.escapeQuotes(value)).append("'");
			}
			sql.append(")");
		}
		return sql.toString();
	}

	public InsertSQL(String table) {
		this.table = table;
	}

	public void addRow(Map<String, String> row) {
		for (String fieldName : row.keySet()) {
			fields.add(fieldName);
		}
		this.data.add(row);
	}

}
