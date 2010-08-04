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
@Table(name = "tan_view")
public class View extends BaseNamedTable {
	@Enumerated(EnumType.STRING)
	private ViewType viewType;

	@ManyToOne
	@JoinColumn(name = "parentID")
	private View parent;
	
	@ManyToOne
	@JoinColumn(name = "modelID")
	private Model model;
	private int displayOrder;

	@OneToMany(mappedBy = "parent")
	private List<View> childViews;
	@OneToMany(mappedBy = "view")
	@OrderBy(value = "displayOrder")
	private List<Field> fields;

	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}

	public View getParent() {
		return parent;
	}

	public void setParent(View parent) {
		this.parent = parent;
	}

	public boolean isRoot() {
		return parent == null;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public List<View> getChildViews() {
		return childViews;
	}

	public void setChildViews(List<View> childRegions) {
		this.childViews = childRegions;
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
		if (model == null)
			return false;
		if (getModel().getResultsPerPage() == 1)
			return false;
		if (isRoot())
			return true;
		return !model.equals(parent.getModel());
	}
}
