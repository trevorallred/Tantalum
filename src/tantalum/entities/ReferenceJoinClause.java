package tantalum.entities;

public class ReferenceJoinClause {
	private TableColumn toColumn;
	private Field toField;
	private TableColumn fromColumn;
	private Field fromField;

	public TableColumn getToColumn() {
		return toColumn;
	}

	public void setToColumn(TableColumn toColumn) {
		this.toColumn = toColumn;
	}

	public Field getToField() {
		return toField;
	}

	public void setToField(Field toField) {
		this.toField = toField;
	}

	public TableColumn getFromColumn() {
		return fromColumn;
	}

	public void setFromColumn(TableColumn fromColumn) {
		this.fromColumn = fromColumn;
	}

	public Field getFromField() {
		return fromField;
	}

	public void setFromField(Field fromField) {
		this.fromField = fromField;
	}

}
