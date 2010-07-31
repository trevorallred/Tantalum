package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tan_index_column")
public class MetaIndexColumn extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "indexID")
	private MetaIndex index;
	@ManyToOne
	@JoinColumn(name = "columnID")
	private MetaColumn column;

	public MetaIndex getIndex() {
		return index;
	}

	public void setIndex(MetaIndex index) {
		this.index = index;
	}

	public MetaColumn getColumn() {
		return column;
	}

	public void setColumn(MetaColumn column) {
		this.column = column;
	}

}
