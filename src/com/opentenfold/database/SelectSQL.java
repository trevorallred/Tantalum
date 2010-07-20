package com.opentenfold.database;

import java.util.ArrayList;

public class SelectSQL {
	protected String fromTable;
	protected ArrayList<String> whereClause = new ArrayList<String>();
	protected ArrayList<String> groupByFields = new ArrayList<String>();
	protected String havingClause = "";
	protected ArrayList<String> fields = new ArrayList<String>();
	protected ArrayList<String> joinClause = new ArrayList<String>();
	protected ArrayList<String> orderBys = new ArrayList<String>();
	protected int startRow = 0;
	protected int limit = -1;
	protected boolean SQL_CALC_FOUND_ROWS = false;

	/**
	 * fullClause allows developers to use the SQLBuilder class as a sql string
	 * instead and explicitly describe the SQL statement in full.
	 */
	String fullClause = "";

	/**
	 * Return the sql clause in this format:
	 * 
	 * SELECT {fields<String>} FROM {fromTable} [{joinClause<String>}] [WHERE
	 * {whereClause}] [GROUP BY {groupByFields<String>] [HAVING {havingClause}]
	 * [ORDER BY {orderBys<String>} [LIMIT {limit}|LIMIT {startRow}, {limit}]
	 */
	public String toString() {
		if (fullClause.length() > 0)
			return fullClause;

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
		if (this.SQL_CALC_FOUND_ROWS) {
			sql.append("SQL_CALC_FOUND_ROWS ");
		}
		if (fields.size() > 0)
			sql.append(combineArray(fields));
		else
			sql.append("*");
		sql.append("\nFROM ");
		sql.append(fromTable);
		for (String joinSQL : this.joinClause) {
			sql.append("\n");
			sql.append(joinSQL);
		}

		if (whereClause.size() > 0) {
			sql.append("\nWHERE 1");
			for (String whereSQL : this.whereClause) {
				sql.append("\n AND (");
				sql.append(whereSQL);
				sql.append(") ");
			}
		}
		if (this.groupByFields.size() > 0) {
			sql.append("\nGROUP BY ");
			sql.append(this.combineArray(this.groupByFields));
			if (havingClause != null && havingClause.length() > 0) {
				sql.append("\nHAVING ");
				sql.append(this.havingClause);
			}
		}
		if (this.orderBys.size() > 0) {
			sql.append("\nORDER BY ");
			sql.append(this.combineArray(this.orderBys));
		}

		if (this.limit >= 0) {
			sql.append("\nLIMIT ");
			if (this.startRow > 0) {
				sql.append(this.startRow);
				sql.append(", ");
			}
			sql.append(this.limit);
		}
		return sql.toString();
	}

	public SelectSQL() {
	}

	public SelectSQL(String from) {
		this.fromTable = from;
	}

	public SelectSQL(String from, String where) {
		this.fromTable = from;
		this.whereClause.add(where);
	}

	public String combineArray(ArrayList<String> list) {
		StringBuilder sql = new StringBuilder();
		Boolean start = true;
		for (String value : list) {
			if (!start)
				sql.append(", ");
			sql.append(value);
			start = false;
		}
		return sql.toString();
	}

	public boolean hasJoin(String tableName) {
		for (String join : joinClause) {
			if (join.contains(tableName))
				return true;
		}
		return false;
	}

	public void setFromTable(String fromTable) {
		this.fromTable = fromTable;
	}

	public void addWhere(String whereClause) {
		if (whereClause != null && whereClause.length() > 0)
			this.whereClause.add(whereClause);
	}

	public void addGroupBy(String groupBy) {
		if (groupBy != null && groupBy.length() > 0)
			this.groupByFields.add(groupBy);
	}

	public void setHavingClause(String havingClause) {
		this.havingClause = havingClause;
	}

	/**
	 * @param join
	 *            Example: JOIN user u ON u.user_id = t.user_id
	 */
	public void addJoin(String join) {
		if (join != null && join.length() > 0)
			this.joinClause.add(join);
	}

	public void addField(String field) {
		if (field != null && field.length() > 0)
			this.fields.add(field);
	}

	public void addOrderBy(String field) {
		if (field != null && field.length() > 0)
			this.orderBys.add(field);
	}

	public void setFullClause(String fullClause) {
		this.fullClause = fullClause;
	}

	public String getOrderBy() {
		return this.combineArray(this.orderBys);
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isSQL_CALC_FOUND_ROWS() {
		return SQL_CALC_FOUND_ROWS;
	}

	public void setSQL_CALC_FOUND_ROWS(boolean sql_calc_found_rows) {
		SQL_CALC_FOUND_ROWS = sql_calc_found_rows;
	}
}