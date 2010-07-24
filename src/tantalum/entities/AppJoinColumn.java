package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "dd_join_column")
public class AppJoinColumn extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "joinID")
	private AppJoin join;
	@ManyToOne
	@JoinColumn(name = "fromColumnID")
	private AppColumn fromColumn;
	@ManyToOne
	@JoinColumn(name = "toColumnID")
	private AppColumn toColumn;

	public AppJoin getJoin() {
		return join;
	}

	public void setJoin(AppJoin join) {
		this.join = join;
	}

	public AppColumn getFromColumn() {
		return fromColumn;
	}

	public void setFromColumn(AppColumn fromColumn) {
		this.fromColumn = fromColumn;
	}

	public AppColumn getToColumn() {
		return toColumn;
	}

	public void setToColumn(AppColumn toColumn) {
		this.toColumn = toColumn;
	}

	@Override
	public String toString() {
		return fromColumn.toString() + " = " + toColumn.toString();
	}
}
