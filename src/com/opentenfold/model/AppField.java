package com.opentenfold.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.opentenfold.database.content.PageContentBean;

@Entity
@javax.persistence.Table(name = "dd_field")
public class AppField extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "viewID")
	private AppView view;
	private String name;
	private String label;
	private boolean visible = true;
	private boolean searchable = false;
	private boolean editable = false;
	@ManyToOne
	@JoinColumn(name = "basisColumnID")
	private AppColumn basisColumn;
	@ManyToOne
	@JoinColumn(name = "linkToFieldID")
	private AppField linkToField;
	@ManyToOne
	@JoinColumn(name = "linkFromFieldID")
	private AppField linkFromField;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AppView getView() {
		return view;
	}

	public void setView(AppView view) {
		this.view = view;
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

	public AppColumn getBasisColumn() {
		return basisColumn;
	}

	public void setBasisColumn(AppColumn basisColumn) {
		this.basisColumn = basisColumn;
	}

	public AppField getLinkFromField() {
		return linkFromField;
	}

	public void setLinkFromField(AppField linkFromField) {
		this.linkFromField = linkFromField;
	}

	public AppField getLinkToField() {
		return linkToField;
	}

	public void setLinkToField(AppField linkToField) {
		this.linkToField = linkToField;
	}

	// Helper methods //

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
