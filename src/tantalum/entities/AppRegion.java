package tantalum.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "dd_region")
public class AppRegion extends BaseNamedTable {
	@ManyToOne
	@JoinColumn(name = "pageID")
	private AppPage page;
	@Enumerated(EnumType.STRING)
	private RegionType regionType;

	@ManyToOne
	@JoinColumn(name = "parentID")
	private AppRegion parent;
	@ManyToOne
	@JoinColumn(name = "viewID")
	private AppView view;
	private int displayOrder;

	@OneToMany(mappedBy = "parent")
	private List<AppRegion> childRegions;
	@OneToMany(mappedBy = "region")
	@OrderBy(value = "displayOrder")
	private List<AppField> fields;

	public AppPage getPage() {
		return page;
	}

	public void setPage(AppPage page) {
		this.page = page;
	}

	public RegionType getRegionType() {
		return regionType;
	}

	public void setRegionType(RegionType regionType) {
		this.regionType = regionType;
	}

	public AppRegion getParent() {
		return parent;
	}

	public void setParent(AppRegion parent) {
		this.parent = parent;
	}

	public boolean isRoot() {
		return parent == null;
	}

	public AppView getView() {
		return view;
	}

	public void setView(AppView view) {
		this.view = view;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public List<AppRegion> getChildRegions() {
		return childRegions;
	}

	public void setChildRegions(List<AppRegion> childRegions) {
		this.childRegions = childRegions;
	}

	public List<AppField> getFields() {
		return fields;
	}

	public void setFields(List<AppField> fields) {
		this.fields = fields;
	}

	public List<AppField> getVisibleFields() {
		List<AppField> visibleFields = new ArrayList<AppField>();
		for (AppField field : fields) {
			if (field.isVisible())
				visibleFields.add(field);
		}
		return visibleFields;
	}

	/**
	 * We may want to just store this value in the DB but for now I think I can
	 * just guess it
	 * 
	 * @return
	 */
	public boolean isShowPreviousNextButtons() {
		if (view == null)
			return false;
		if (getView().getResultsPerPage() == 1)
			return false;
		if (isRoot())
			return true;
		return !view.equals(parent.getView());
	}
}
