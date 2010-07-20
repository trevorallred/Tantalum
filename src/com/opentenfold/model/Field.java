package com.opentenfold.model;

import com.opentenfold.database.content.TenFoldDynaBean;

public class Field extends BaseTable {
	private String name;
	private String label;
	private boolean visible = true;
	private boolean searchable = false;
	private boolean editable = false;
	private Integer maxSize = null;
	private String basisTable;
	private String basisColumn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String draw() {
		return label;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	public String getBasisTable() {
		return basisTable;
	}

	public void setBasisTable(String basisTable) {
		this.basisTable = basisTable;
	}

	public String getBasisColumn() {
		return basisColumn;
	}

	public void setBasisColumn(String basisColumn) {
		this.basisColumn = basisColumn;
	}

	public String getValue(TenFoldDynaBean row) {
		return row.getString(name);
		// if (object == null)
		// return "";
		//
		// String value = object.toString();
		// return value;
	}

}
