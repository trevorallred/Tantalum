package tantalum.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tan_index")
public class MetaIndex extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "tableID")
	private MetaTable table;
	private int displayOrder;
	private boolean unique;

	@OneToMany(mappedBy = "index")
	private List<MetaIndexColumn> columns;

	public MetaTable getTable() {
		return table;
	}

	public void setTable(MetaTable table) {
		this.table = table;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public List<MetaIndexColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<MetaIndexColumn> columns) {
		this.columns = columns;
	}

}
