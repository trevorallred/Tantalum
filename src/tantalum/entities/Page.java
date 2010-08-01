package tantalum.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tan_page")
public class Page extends BaseNamedTable {
	@Transient
	private Field keyField = null;

	@OneToMany(mappedBy = "page")
	private List<View> views = new ArrayList<View>();
	@OneToMany(mappedBy = "page")
	private List<Region> regions = new ArrayList<Region>();
	@Transient
	private List<Exception> exceptions = new ArrayList<Exception>();

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

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	/**
	 * Return a list of the top views (usually only one).
	 * 
	 * @return
	 */
	public List<View> getParentViews() {
		List<View> top = new ArrayList<View>();
		for (View view : views) {
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
	public List<Region> getParentRegions() {
		List<Region> top = new ArrayList<Region>();
		for (Region view : regions) {
			if (view.getParent() == null) {
				top.add(view);
			}
		}
		return top;
	}

	// Helper methods //
	
	
	public boolean isCanSave() {
		for (View view : views) {
			if (isCanSaveView(view))
				return true;
		}
		return false;
	}
	
	public boolean isCanSaveView(View parentView) {
		if (parentView.isAllowAdd())
			return true;
		if (parentView.isAllowEdit())
			return true;
		if (parentView.isAllowDelete())
			return true;
		for (View view : parentView.getChildViews()) {
			if (isCanSaveView(view))
				return true;
		}
		return false;
	}
	
	public String printAll() {
		String out = name + "(" + id + ")";
		for (View view : views)
			out += "\n  V: " + view.toString();
		for (Region region : regions)
			out += "\n  R: " + region.toString();
		return out;
	}

}
