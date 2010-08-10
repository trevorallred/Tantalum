package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tan_field_selector")
public class FieldSelector extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "fieldID")
	private Field field;
	@ManyToOne
	@JoinColumn(name = "sourceID")
	private Field source;
	@ManyToOne
	@JoinColumn(name = "targetID")
	private Field target;
	@ManyToOne
	@JoinColumn(name = "whenID")
	private Field when;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Field getSource() {
		return source;
	}

	public void setSource(Field source) {
		this.source = source;
	}

	public Field getTarget() {
		return target;
	}

	public void setTarget(Field target) {
		this.target = target;
	}

	public Field getWhen() {
		return when;
	}

	public void setWhen(Field when) {
		this.when = when;
	}

}
