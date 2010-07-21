package com.opentenfold.model;

public class Reference extends BaseTable {
	private View view;
	private Reference parent;
	private String name;
	private String tableDbName;
	private String fromColumnDbName;
	private int toColumnID;

	public Reference(Integer id) {
		super(id);
	}

	public Reference getParent() {
		return parent;
	}

	public void setParent(Reference parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTableDbName() {
		return tableDbName;
	}

	public void setTableDbName(String tableDbName) {
		this.tableDbName = tableDbName;
	}

	public String getFromColumnDbName() {
		return fromColumnDbName;
	}

	public void setFromColumnDbName(String fromColumnDbName) {
		this.fromColumnDbName = fromColumnDbName;
	}

	public int getToColumnID() {
		return toColumnID;
	}

	public void setToColumnID(int toColumnID) {
		this.toColumnID = toColumnID;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
		if (!view.getReferences().contains(this))
			view.getReferences().add(this);
	}

	public String toString() {
		String out = "";
		if (parent != null)
			out += "\n  Child of " + parent;
		out += "from " + tableDbName + "." + fromColumnDbName + " to " + toColumnID;
		return out;
	}
}
