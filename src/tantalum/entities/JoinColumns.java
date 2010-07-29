package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tan_join_column")
public class JoinColumns extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "joinID")
	private Join join;
	@ManyToOne
	@JoinColumn(name = "fromColumnID")
	private MetaColumn fromColumn;
	@ManyToOne
	@JoinColumn(name = "toColumnID")
	private MetaColumn toColumn;

	public Join getJoin() {
		return join;
	}

	public void setJoin(Join join) {
		this.join = join;
	}

	public MetaColumn getFromColumn() {
		return fromColumn;
	}

	public void setFromColumn(MetaColumn fromColumn) {
		this.fromColumn = fromColumn;
	}

	public MetaColumn getToColumn() {
		return toColumn;
	}

	public void setToColumn(MetaColumn toColumn) {
		this.toColumn = toColumn;
	}

	@Override
	public String toString() {
		return fromColumn.toString() + " = " + toColumn.toString();
	}
}
