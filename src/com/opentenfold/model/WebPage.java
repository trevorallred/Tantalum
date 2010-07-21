package com.opentenfold.model;

import java.util.ArrayList;
import java.util.List;

public class WebPage extends BaseTable {
	private String name;
	private String url;
	private String title;
	private Field keyField = null;
	private List<View> views = new ArrayList<View>();
	private List<PageSection> sections = new ArrayList<PageSection>();

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

	public Field getKeyField() {
		return keyField;
	}

	public void setKeyField(Field keyField) {
		this.keyField = keyField;
	}

	public List<Exception> getExceptions() {
		return exceptions;
	}

	public void addException(Exception exception) {
		this.exceptions.add(exception);
	}

	public List<View> getViews() {
		return views;
	}

	public List<PageSection> getSections() {
		return sections;
	}

	/** Helper methods **/

	public void addFieldToView(Field field) {
		getView(field).getFields().add(field);
	}

	public View getView(Field field) {
		for (View view : views) {
			if (view.getId() == field.getViewID())
				return view;
		}
		return null;
	}

	public View getView(Integer viewID) {
		if (viewID == null)
			return null;

		for (View view : views) {
			if (view.getId() == viewID)
				return view;
		}
		return null;
	}

	public String toString() {
		String out = url + "(" + id + ")";
		for (View view : views)
			out += "\n  V: " + view.toString();
		return out;
	}

}
