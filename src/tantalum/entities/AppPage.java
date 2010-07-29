package tantalum.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@javax.persistence.Table(name = "dd_page")
public class AppPage extends BaseNamedTable {
	@Transient
	private AppField keyField = null;

	@OneToMany(mappedBy = "page")
	private List<AppView> views = new ArrayList<AppView>();
	@OneToMany(mappedBy = "page")
	private List<AppRegion> regions = new ArrayList<AppRegion>();
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

	public List<AppRegion> getRegions() {
		return regions;
	}

	public void setRegions(List<AppRegion> regions) {
		this.regions = regions;
	}

	/**
	 * Return a list of the top views (usually only one).
	 * 
	 * @return
	 */
	public List<AppView> getParentViews() {
		List<AppView> top = new ArrayList<AppView>();
		for (AppView view : views) {
			if (view.getParent() == null) {
				top.add(view);
			}
		}
		return top;
	}

	/**
	 * Return a list of the top regions (usually only one).
	 * 
	 * @return
	 */
	public List<AppRegion> getParentRegions() {
		List<AppRegion> top = new ArrayList<AppRegion>();
		for (AppRegion view : regions) {
			if (view.getParent() == null) {
				top.add(view);
			}
		}
		return top;
	}

	// Helper methods //
	
	
	public boolean isCanSave() {
		for (AppView view : views) {
			if (isCanSaveView(view))
				return true;
		}
		return false;
	}
	
	public boolean isCanSaveView(AppView parentView) {
		if (parentView.isAllowAdd())
			return true;
		if (parentView.isAllowEdit())
			return true;
		if (parentView.isAllowDelete())
			return true;
		for (AppView view : parentView.getChildViews()) {
			if (isCanSaveView(view))
				return true;
		}
		return false;
	}
	
	public String printAll() {
		String out = name + "(" + id + ")";
		for (AppView view : views)
			out += "\n  V: " + view.toString();
		return out;
	}

}
