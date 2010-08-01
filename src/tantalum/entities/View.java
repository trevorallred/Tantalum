package tantalum.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tantalum.util.Strings;

@Entity
@Table(name = "tan_view")
public class View extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "pageID")
	private Page page;
	private String name;
	private int resultsPerPage = 100;
	@ManyToOne
	@JoinColumn(name = "basisTableID")
	private MetaTable basisTable;
	@ManyToOne
	@JoinColumn(name = "parentID")
	private View parent;
	@ManyToOne
	@JoinColumn(name = "referenceID")
	private Reference reference;
	private boolean allowAdd;
	private boolean allowEdit;
	private boolean allowDelete;

	@OneToMany(mappedBy = "view")
	private List<Field> fields = new ArrayList<Field>();
	@OneToMany(mappedBy = "view")
	private List<Reference> references = new ArrayList<Reference>();
	@OneToMany(mappedBy = "view")
	private List<Region> regions = new ArrayList<Region>();

	@OneToMany(mappedBy = "parent")
	private List<View> childViews = new ArrayList<View>();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResultsPerPage() {
		return resultsPerPage;
	}

	public void setResultsPerPage(int resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}

	public MetaTable getBasisTable() {
		return basisTable;
	}

	public void setBasisTable(MetaTable basisTable) {
		this.basisTable = basisTable;
	}

	public View getParent() {
		return parent;
	}

	public void setParent(View parent) {
		this.parent = parent;
	}

	public boolean isRoot() {
		return parent == null;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public boolean isAllowAdd() {
		return allowAdd;
	}

	public void setAllowAdd(boolean allowAdd) {
		this.allowAdd = allowAdd;
	}

	public boolean isAllowEdit() {
		return allowEdit;
	}

	public void setAllowEdit(boolean allowEdit) {
		this.allowEdit = allowEdit;
	}

	public boolean isAllowDelete() {
		return allowDelete;
	}

	public void setAllowDelete(boolean allowDelete) {
		this.allowDelete = allowDelete;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Reference> getReferences() {
		return references;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<View> getChildViews() {
		return childViews;
	}

	public void setChildViews(List<View> childViews) {
		this.childViews = childViews;
	}

	/** Helper methods **/

	public Field getField(String name) {
		if (Strings.isEmpty(name))
			return null;
		for (Field field : fields) {
			if (name.equals(field.getName()))
				return field;
		}
		return null;
	}

	public Field getField(int fieldID) {
		for (Field field : fields) {
			if (fieldID == field.getId())
				return field;
		}
		return null;
	}

	public Field getPrimaryKey() {
		if (basisTable == null)
			return null;
		MetaColumn primaryKey = basisTable.getPrimaryKey();
		for (Field field : fields) {
			if (primaryKey.equals(field.getBasisColumn()))
				return field;
		}
		return null;
	}

	@Override
	public String toString() {
		String out = name + "(" + id + ")";
		out += "\n    T: " + basisTable.toString();
		for (Field field : fields)
			out += "\n    F: " + field.toString();
		for (Reference reference : references)
			out += "\n    R: " + reference.toString();
		return out;
	}
}
