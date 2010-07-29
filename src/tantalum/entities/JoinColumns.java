package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "tan_join_column")
public class JoinColumns extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "joinID")
	private Join join;
	@ManyToOne
	@JoinColumn(name = "fromColumnID")
	private TableColumn fromColumn;
	@ManyToOne
	@JoinColumn(name = "toColumnID")
	private TableColumn toColumn;

	public Join getJoin() {
		return join;
	}

	public void setJoin(Join join) {
		this.join = join;
	}

	public TableColumn getFromColumn() {
		return fromColumn;
	}

	public void setFromColumn(TableColumn fromColumn) {
		this.fromColumn = fromColumn;
	}

	public TableColumn getToColumn() {
		return toColumn;
	}

	public void setToColumn(TableColumn toColumn) {
		this.toColumn = toColumn;
	}

	@Override
	public String toString() {
		return fromColumn.toString() + " = " + toColumn.toString();
	}
}
