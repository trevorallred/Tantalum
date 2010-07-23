package com.opentenfold.database.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opentenfold.model.AppView;

/**
 * A set of 1 or more views. This could be a multi-headed page like a list of
 * Employees and a list of available JobOpenings. Or it could be the children of
 * another view like Columns and Joins for a Table. Finally, it could be just a
 * "single-headed" or single-view page like most reports are.
 */
public class PageContent {
	private Map<String, ViewContent> views = new HashMap<String, ViewContent>();

	public Set<String> getViewNames() {
		return views.keySet();
	}

	public ViewContent getViewContent(String viewName) {
		if (!views.containsKey(viewName))
			views.put(viewName, new ViewContent());
		return views.get(viewName);
	}

	public void addViewContent(String viewName, List<PageContentBean> list) {
		views.put(viewName, new ViewContent(list));
	}
	
	public void addChildContent(AppView childView, PageContentBean childContent) {
		if (!views.containsKey(childView.getName()))
			views.put(childView.getName(), new ViewContent());
		views.get(childView.getName()).getData().add(childContent);
	}
}
