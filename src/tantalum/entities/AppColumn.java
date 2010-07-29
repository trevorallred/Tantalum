package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "dd_column")
public class AppColumn extends BaseNamedTable {
	@ManyToOne
	@JoinColumn(name = "tableID")
	private AppTable table;
	private int displayOrder;
	private String dbName;
	private boolean required = false;
	private ColumnType columnType;
	private Integer size;

	public AppTable getTable() {
		return table;
	}

	public void setTable(AppTable table) {
		this.table = table;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public ColumnType getColumnType() {
		return columnType;
	}

	public void setColumnType(ColumnType columnType) {
		this.columnType = columnType;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return table.getName() + "." + name + "(" + id + ")";
	}
}
