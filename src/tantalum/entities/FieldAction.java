package tantalum.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tan_field_action")
public class FieldAction extends BaseNamedTable {
	@ManyToOne
	@JoinColumn(name = "fieldID")
	private Field field;
	private boolean defaultAction = false;
	@OneToMany(mappedBy = "fieldAction")
	private List<FieldActionDetail> columns;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public boolean isDefaultAction() {
		return defaultAction;
	}

	public void setDefaultAction(boolean defaultAction) {
		this.defaultAction = defaultAction;
	}

	public List<FieldActionDetail> getColumns() {
		return columns;
	}

	public void setColumns(List<FieldActionDetail> columns) {
		this.columns = columns;
	}

}
