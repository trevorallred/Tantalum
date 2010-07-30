package tantalum.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tan_submenu")
public class SubMenu extends BaseNamedTable {
	private SubMenu parent;
	private Page page;
	private List<SubMenu> subMenus;

	public SubMenu getParent() {
		return parent;
	}

	public void setParent(SubMenu parent) {
		this.parent = parent;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<SubMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<SubMenu> subMenus) {
		this.subMenus = subMenus;
	}

}
