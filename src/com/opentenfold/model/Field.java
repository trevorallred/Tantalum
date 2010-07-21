package com.opentenfold.model;

import com.opentenfold.database.content.PageContentBean;

public class Field extends BaseTable {
	private String name;
	private int viewID;
	private String label;
	private boolean visible = true;
	private boolean searchable = false;
	private boolean editable = false;
	private Integer maxSize = null;
	private Column basisColumn;
	private String linkToUrl;
	private Field linkFromField;

	public Field(Integer id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getViewID() {
		return viewID;
	}

	public void setViewID(int viewID) {
		this.viewID = viewID;
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

	public Column getBasisColumn() {
		return basisColumn;
	}

	public void setBasisColumn(Column basisColumn) {
		this.basisColumn = basisColumn;
	}

	public Field getLinkFromField() {
		return linkFromField;
	}

	public void setLinkFromField(Field linkFromField) {
		this.linkFromField = linkFromField;
	}

	public String getLinkToUrl() {
		return linkToUrl;
	}

	public void setLinkToUrl(String linkToUrl) {
		this.linkToUrl = linkToUrl;
	}

	public String getValue(PageContentBean row) {
		return row.getString(name);
		// if (object == null)
		// return "";
		//
		// String value = object.toString();
		// return value;
	}

	public String toString() {
		String out = name + "(" + id + ")";
		return out;
	}

}
