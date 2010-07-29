package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import tantalum.data.PageContentBean;

@Entity
@javax.persistence.Table(name = "tan_field")
public class Field extends BaseNamedTable {
	@ManyToOne
	@JoinColumn(name = "viewID")
	private View view;
	private boolean visible = true;
	private String displayOrder;
	private boolean searchable = false;
	private boolean editable = false;
	@Enumerated(EnumType.STRING)
	private FieldDisplayType displayType;
	@ManyToOne
	@JoinColumn(name = "regionID")
	private Region region;

	@ManyToOne
	@JoinColumn(name = "basisColumnID")
	private TableColumn basisColumn;
	@ManyToOne
	@JoinColumn(name = "referenceID")
	private Reference reference;

	@ManyToOne
	@JoinColumn(name = "linkToFieldID")
	private Field linkToField;
	@ManyToOne
	@JoinColumn(name = "linkFromFieldID")
	private Field linkFromField;

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
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

	public TableColumn getBasisColumn() {
		return basisColumn;
	}

	public void setBasisColumn(TableColumn basisColumn) {
		this.basisColumn = basisColumn;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public FieldDisplayType getDisplayType() {
		return displayType;
	}

	public void setDisplayType(FieldDisplayType displayType) {
		this.displayType = displayType;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public boolean isHasLink() {
		return linkToField != null;
	}

	public Field getLinkFromField() {
		return linkFromField;
	}

	public void setLinkFromField(Field linkFromField) {
		this.linkFromField = linkFromField;
	}

	public Field getLinkToField() {
		return linkToField;
	}

	public void setLinkToField(Field linkToField) {
		this.linkToField = linkToField;
	}

	// Helper methods //

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

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

}
