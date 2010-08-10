package tantalum.entities;

/**
 * This is a transient helper class that helps the Tantalum DB mapper
 */
public class ReferenceJoinClause {
	private MetaColumn toColumn;
	private Field toField;
	private MetaColumn fromColumn;
	private Field fromField;

	public MetaColumn getToColumn() {
		return toColumn;
	}

	public void setToColumn(MetaColumn toColumn) {
		this.toColumn = toColumn;
	}

	public Field getToField() {
		return toField;
	}

	public void setToField(Field toField) {
		this.toField = toField;
	}

	public MetaColumn getFromColumn() {
		return fromColumn;
	}

	public void setFromColumn(MetaColumn fromColumn) {
		this.fromColumn = fromColumn;
	}

	public Field getFromField() {
		return fromField;
	}

	public void setFromField(Field fromField) {
		this.fromField = fromField;
	}

}
