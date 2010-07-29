package tantalum.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "tan_button")
public class FieldAction extends BaseNamedTable {
	@ManyToOne
	@JoinColumn(name = "fieldID")
	private Field field;
	private List<FieldActionDetail> columns;

}
