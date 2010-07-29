package tantalum.entities;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "tan_submenu")
public class SubMenu extends BaseNamedTable {
	private SubMenu parent;
	private Page page;
}
