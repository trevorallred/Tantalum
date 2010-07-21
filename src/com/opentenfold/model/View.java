package com.opentenfold.model;

import java.util.ArrayList;
import java.util.List;

import com.opentenfold.util.Strings;

public class View extends BaseTable {
	private String name;
	private int resultsPerPage = 100;
	private String basisTable;
	private Integer parentID = null;

	private List<Field> fields = new ArrayList<Field>();
	private List<Reference> references = new ArrayList<Reference>();

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

	public String getBasisTable() {
		return basisTable;
	}

	public void setBasisTable(String basisTable) {
		this.basisTable = basisTable;
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

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
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

}
