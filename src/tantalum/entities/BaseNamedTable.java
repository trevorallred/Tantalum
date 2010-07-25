package tantalum.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseNamedTable extends BaseTable {
	protected String name;
	protected String label;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return name + " (" + id + ")";
	}

}
