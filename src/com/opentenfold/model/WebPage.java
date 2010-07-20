package com.opentenfold.model;

import java.util.ArrayList;
import java.util.List;

import com.opentenfold.util.Strings;

public class WebPage extends BaseTable {
	private String name;
	private String url;
	private String title;
	private int resultsPerPage = 100;
	private List<Field> fields = new ArrayList<Field>();
	private List<Exception> exceptions = new ArrayList<Exception>();
	private String basisTable;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<Exception> getExceptions() {
		return exceptions;
	}

	public void addException(Exception exception) {
		this.exceptions.add(exception);
	}

	public Field getField(String name) {
		if (Strings.isEmpty(name))
			return null;
		for (Field field : fields) {
			if (name.equals(field.getName()))
				return field;
		}
		return null;
	}

}
