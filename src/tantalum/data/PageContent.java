package tantalum.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import tantalum.entities.View;

/**
 * A set of 1 or more views. This could be a multi-headed page like a list of
 * Employees and a list of available JobOpenings. Or it could be the children of
 * another view like Columns and Joins for a Table. Finally, it could be just a
 * "single-headed" or single-view page like most reports are.
 */
public class PageContent {
	private Map<String, InstanceList> views = new HashMap<String, InstanceList>();

	public Set<String> getViewNames() {
		return views.keySet();
	}

	public InstanceList getViewContent(String viewName) {
		if (!views.containsKey(viewName))
			views.put(viewName, new InstanceList());
		return views.get(viewName);
	}

	/**
	 * Add an child instance. If the InstanceList for this child type doesn't
	 * exist yet, then create it
	 * 
	 * @param childView
	 * @param childContent
	 */
	public void addChildContent(View childView, Instance childContent) {
		addChildContent(childView.getName(), childContent);
	}

	/**
	 * Add an child instance. If the InstanceList for this child type doesn't
	 * exist yet, then create it
	 * 
	 * @param childView
	 * @param childContent
	 */
	public void addChildContent(String childViewName, Instance childContent) {
		if (!views.containsKey(childViewName))
			views.put(childViewName, new InstanceList());
		views.get(childViewName).getData().add(childContent);
	}

	public void addChildContent(String childViewName, InstanceList instanceList) {
		views.put(childViewName, instanceList);
	}

}
