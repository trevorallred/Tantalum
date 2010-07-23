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
	private String displayOrder;
	private boolean searchable = false;
	private boolean editable = false;
	private String displayType;

	@ManyToOne
	@JoinColumn(name = "basisColumnID")
	private AppColumn basisColumn;
	@ManyToOne
	@JoinColumn(name = "referenceID")
	private AppReference reference;

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

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public AppReference getReference() {
		return reference;
	}

	public void setReference(AppReference reference) {
		this.reference = reference;
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

	/**
	 * Is this field based on a column from the view's basis table
	 */
	public boolean isBasisField() {
		if (view.getBasisTable() == null)
			return false;
		if (basisColumn == null)
			return false;
		return view.getBasisTable().equals(basisColumn.getTable());
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
