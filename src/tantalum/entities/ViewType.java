package tantalum.entities;

public enum ViewType {
	BasicTable, FormRegion, VerticalContainer, HorizontalContainer;

	public boolean isBasicTable() {
		return this == BasicTable;
	}

	public boolean isFormRegion() {
		return this == FormRegion;
	}

	public boolean isVerticalContainer() {
		return this == VerticalContainer;
	}

	public boolean isHorizontalContainer() {
		return this == HorizontalContainer;
	}
	
	public boolean isHasFields() {
		if (isBasicTable())
			return true;
		if (isFormRegion())
			return true;
		return false;
	}
}
