package com.opentenfold.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.opentenfold.util.Strings;

@Entity
@javax.persistence.Table(name = "dd_view")
public class AppView extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "pageID")
	private AppPage page;
	private String name;
	private int resultsPerPage = 100;
	@ManyToOne
	@JoinColumn(name = "basisTableID")
	private AppTable basisTable;
	@ManyToOne
	@JoinColumn(name = "parentID")
	private AppView parent;
	@ManyToOne
	@JoinColumn(name = "referenceID")
	private AppReference reference;

	@OneToMany(mappedBy = "view")
	private List<AppField> fields = new ArrayList<AppField>();
	@OneToMany(mappedBy = "view")
	private List<AppReference> references = new ArrayList<AppReference>();

	public AppPage getPage() {
		return page;
	}

	public void setPage(AppPage page) {
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

	public AppTable getBasisTable() {
		return basisTable;
	}

	public void setBasisTable(AppTable basisTable) {
		this.basisTable = basisTable;
	}

	public AppView getParent() {
		return parent;
	}

	public void setParent(AppView parent) {
		this.parent = parent;
	}

	public AppReference getReference() {
		return reference;
	}

	public void setReference(AppReference reference) {
		this.reference = reference;
	}

	public List<AppField> getFields() {
		return fields;
	}

	public void setFields(List<AppField> fields) {
		this.fields = fields;
	}

	public List<AppReference> getReferences() {
		return references;
	}

	public void setReferences(List<AppReference> references) {
		this.references = references;
	}

	/** Helper methods **/

	public AppField getField(String name) {
		if (Strings.isEmpty(name))
			return null;
		for (AppField field : fields) {
			if (name.equals(field.getName()))
				return field;
		}
		return null;
	}

	public AppField getField(int fieldID) {
		for (AppField field : fields) {
			if (fieldID == field.getId())
				return field;
		}
		return null;
	}

	@Override
	public String toString() {
		String out = name + "(" + id + ")";
		for (AppField field : fields)
			out += "\n    F: " + field.toString();
		for (AppReference reference : references)
			out += "\n    R: " + reference.toString();
		return out;
	}
}