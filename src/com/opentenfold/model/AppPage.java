package com.opentenfold.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@javax.persistence.Table(name = "dd_page")
public class AppPage extends BaseTable {
	private String name;
	private String url;
	private String title;
	@Transient
	private AppField keyField = null;

	@OneToMany(mappedBy = "page", fetch = FetchType.EAGER)
	private List<AppView> views = new ArrayList<AppView>();
	@Transient
	private List<PageSection> sections = new ArrayList<PageSection>();
	@Transient
	private List<Exception> exceptions = new ArrayList<Exception>();

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

	public AppField getKeyField() {
		return keyField;
	}

	public void setKeyField(AppField keyField) {
		this.keyField = keyField;
	}

	public List<Exception> getExceptions() {
		return exceptions;
	}

	public void addException(Exception exception) {
		this.exceptions.add(exception);
	}

	public List<AppView> getViews() {
		return views;
	}

	public List<PageSection> getSections() {
		return sections;
	}

	/** Helper methods **/
	public String toString() {
		String out = url + "(" + id + ")";
		for (AppView view : views)
			out += "\n  V: " + view.toString();
		return out;
	}

}
