package tantalum.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tantalum.data.Instance;

@Entity
@Table(name = "tan_field")
public class Field extends BaseNamedTable {
	@ManyToOne
	@JoinColumn(name = "modelID")
	private Model model;
	private boolean visible = true;
	private int displayOrder;
	private boolean searchable = false;
	private boolean editable = true;
	private boolean addable = true;
	@Enumerated(EnumType.STRING)
	private FieldDisplayType displayType;
	private Integer sortOrder;
	@Enumerated(EnumType.ORDINAL)
	private SortDirection sortDirection;
	private int size;
	@ManyToOne
	@JoinColumn(name = "defaultFieldID")
	private Field defaultField;
	@Enumerated(EnumType.STRING)
	private FieldDefaultType defaultFieldType;
	private boolean forceDefault;
	private String defaultValue;
	private String defaultScript;
	@ManyToOne
	@JoinColumn(name = "viewID")
	private View view;

	@ManyToOne
	@JoinColumn(name = "basisColumnID")
	private MetaColumn basisColumn;

	@ManyToOne
	@JoinColumn(name = "referenceID")
	private Reference reference;

	@OneToMany(mappedBy = "field")
	private List<FieldAction> fieldActions = new ArrayList<FieldAction>();

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
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

	public boolean isAddable() {
		return addable;
	}

	public void setAddable(boolean addable) {
		this.addable = addable;
	}

	public MetaColumn getBasisColumn() {
		return basisColumn;
	}

	public void setBasisColumn(MetaColumn basisColumn) {
		this.basisColumn = basisColumn;
	}

	public FieldDisplayType getDisplayType() {
		return displayType;
	}

	public void setDisplayType(FieldDisplayType displayType) {
		this.displayType = displayType;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public SortDirection getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

	public Field getDefaultField() {
		return defaultField;
	}

	public void setDefaultField(Field defaultField) {
		this.defaultField = defaultField;
	}

	public FieldDefaultType getDefaultFieldType() {
		return defaultFieldType;
	}

	public void setDefaultFieldType(FieldDefaultType defaultFieldType) {
		this.defaultFieldType = defaultFieldType;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public boolean isHasLink() {
		return getDefaultAction() != null;
	}

	public FieldAction getDefaultAction() {
		for (FieldAction fa : fieldActions) {
			if (fa.isDefaultAction())
				return fa;
		}
		return null;
	}

	public List<FieldAction> getFieldActions() {
		return fieldActions;
	}

	public void setFieldActions(List<FieldAction> fieldActions) {
		this.fieldActions = fieldActions;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public boolean isForceDefault() {
		return forceDefault;
	}

	public void setForceDefault(boolean forceDefault) {
		this.forceDefault = forceDefault;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDefaultScript() {
		return defaultScript;
	}

	public void setDefaultScript(String defaultScript) {
		this.defaultScript = defaultScript;
	}

	/**
	 * Is this field based on a column from the model's basis table
	 */
	public boolean isBasisField() {
		if (model.getBasisTable() == null)
			return false;
		if (basisColumn == null)
			return false;
		return model.getBasisTable().equals(basisColumn.getTable());
	}

	public String getValue(Instance row) {
		return row.getString(name);
		// if (object == null)
		// return "";
		//
		// String value = object.toString();
		// return value;
	}

}
