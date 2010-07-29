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
@Table(name = "tan_region")
public class Region extends BaseNamedTable {
	@ManyToOne
	@JoinColumn(name = "pageID")
	private Page page;
	@Enumerated(EnumType.STRING)
	private RegionType regionType;

	@ManyToOne
	@JoinColumn(name = "parentID")
	private Region parent;
	@ManyToOne
	@JoinColumn(name = "viewID")
	private View view;
	private int displayOrder;

	@OneToMany(mappedBy = "parent")
	private List<Region> childRegions;
	@OneToMany(mappedBy = "region")
	@OrderBy(value = "displayOrder")
	private List<Field> fields;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public RegionType getRegionType() {
		return regionType;
	}

	public void setRegionType(RegionType regionType) {
		this.regionType = regionType;
	}

	public Region getParent() {
		return parent;
	}

	public void setParent(Region parent) {
		this.parent = parent;
	}

	public boolean isRoot() {
		return parent == null;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public List<Region> getChildRegions() {
		return childRegions;
	}

	public void setChildRegions(List<Region> childRegions) {
		this.childRegions = childRegions;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Field> getVisibleFields() {
		List<Field> visibleFields = new ArrayList<Field>();
		for (Field field : fields) {
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
