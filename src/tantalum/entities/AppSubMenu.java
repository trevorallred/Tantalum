package tantalum.entities;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "dd_submenu")
public class AppSubMenu extends BaseNamedTable {
	private AppSubMenu parent;
	private AppPage page;
}
