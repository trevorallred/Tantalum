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
@Table(name = "tan_model")
public class Model extends BaseNamedTable {
	private int resultsPerPage = 100;
	@ManyToOne
	@JoinColumn(name = "basisTableID")
	private MetaTable basisTable;
	@ManyToOne
	@JoinColumn(name = "parentID")
	private Model parent;
	@ManyToOne
	@JoinColumn(name = "referenceID")
	private Reference reference;
	private boolean allowAdd;
	private boolean allowEdit;
	private boolean allowDelete;

	@OneToMany(mappedBy = "model")
	private List<Field> fields = new ArrayList<Field>();
	@OneToMany(mappedBy = "model")
	private List<Reference> references = new ArrayList<Reference>();
	@OneToMany(mappedBy = "model")
	private List<View> views = new ArrayList<View>();

	@OneToMany(mappedBy = "parent")
	private List<Model> childModels = new ArrayList<Model>();

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

	public Model getParent() {
		return parent;
	}

	public void setParent(Model parent) {
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

	public List<View> getViews() {
		return views;
	}

	public void setViews(List<View> regions) {
		this.views = regions;
	}

	public List<Model> getChildModels() {
		return childModels;
	}

	public void setChildModels(List<Model> childViews) {
		this.childModels = childViews;
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

	public boolean isCanSave() {
		if (allowAdd)
			return true;
		if (allowEdit)
			return true;
		if (allowDelete)
			return true;
		for (Model childModel : childModels) {
			if (childModel.isCanSave()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String out = name + "(" + id + ")";
		if (basisTable != null)
			out += "\n{    T: " + basisTable.toString();
		for (Model childModel : childModels)
			out += "\n    M: " + childModel.toString();
		for (Field field : fields)
			out += "\n    F: " + field.toString();
		for (Reference reference : references)
			out += "\n    R: " + reference.toString();
		return out += "}\n";
	}

}
