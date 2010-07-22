package com.opentenfold.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@javax.persistence.Table(name = "dd_table")
public class AppTable extends BaseTable {
	@javax.persistence.Column(nullable = false)
	private String name;
	@javax.persistence.Column(nullable = false)
	private String dbName;
	@Transient
	private List<AppColumn> columns;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public List<AppColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<AppColumn> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return name + "(" + id + ")";
	}
}
