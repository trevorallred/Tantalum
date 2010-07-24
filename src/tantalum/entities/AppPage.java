package tantalum.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@javax.persistence.Table(name = "dd_page")
public class AppPage extends BaseNamedTable {
	@Transient
	private AppField keyField = null;

	@OneToMany(mappedBy = "page", fetch = FetchType.EAGER)
	private List<AppView> views = new ArrayList<AppView>();
	@Transient
	private List<AppRegion> sections = new ArrayList<AppRegion>();
	@Transient
	private List<Exception> exceptions = new ArrayList<Exception>();


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

	/**
	 * Return a list of the top views (usually only one).
	 * 
	 * @return
	 */
	public List<AppView> getParentViews() {
		List<AppView> topViews = new ArrayList<AppView>();
		for (AppView view : views) {
			if (view.getParent() == null) {
				topViews.add(view);
			}
		}
		return topViews;
	}

	public List<AppRegion> getSections() {
		return sections;
	}

	/** Helper methods **/
	public String toString() {
		String out = name + "(" + id + ")";
		for (AppView view : views)
			out += "\n  V: " + view.toString();
		return out;
	}

}
