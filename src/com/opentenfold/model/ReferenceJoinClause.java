package com.opentenfold.model;

public class ReferenceJoinClause {
	private AppColumn toColumn;
	private AppField toField;
	private AppColumn fromColumn;
	private AppField fromField;

	public AppColumn getToColumn() {
		return toColumn;
	}

	public void setToColumn(AppColumn toColumn) {
		this.toColumn = toColumn;
	}

	public AppField getToField() {
		return toField;
	}

	public void setToField(AppField toField) {
		this.toField = toField;
	}

	public AppColumn getFromColumn() {
		return fromColumn;
	}

	public void setFromColumn(AppColumn fromColumn) {
		this.fromColumn = fromColumn;
	}

	public AppField getFromField() {
		return fromField;
	}

	public void setFromField(AppField fromField) {
		this.fromField = fromField;
	}

}
