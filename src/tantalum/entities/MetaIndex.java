package tantalum.entities;

import java.util.ArrayList;
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
	private boolean uniqueIndex;

	@OneToMany(mappedBy = "index")
	private List<MetaIndexColumn> columns = new ArrayList<MetaIndexColumn>();

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

	public boolean isUniqueIndex() {
		return uniqueIndex;
	}

	public void setUniqueIndex(boolean unique) {
		this.uniqueIndex = unique;
	}

	public List<MetaIndexColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<MetaIndexColumn> columns) {
		this.columns = columns;
	}

}
