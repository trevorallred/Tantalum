package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tan_submenu")
public class SubMenu extends BaseNamedTable {
	private SubMenu parent;
	private Page page;
}
