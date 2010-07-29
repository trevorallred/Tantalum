package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tan_button")
public class FieldActionDetail extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "fieldActionID")
	private FieldAction fieldAction;
	@ManyToOne
	@JoinColumn(name = "fromFieldID")
	private Field fromField;
	@ManyToOne
	@JoinColumn(name = "toFieldID")
	private Field toField;

	public FieldAction getFieldAction() {
		return fieldAction;
	}

	public void setFieldAction(FieldAction fieldAction) {
		this.fieldAction = fieldAction;
	}

	public Field getFromField() {
		return fromField;
	}

	public void setFromField(Field fromField) {
		this.fromField = fromField;
	}

	public Field getToField() {
		return toField;
	}

	public void setToField(Field toField) {
		this.toField = toField;
	}

}
