package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "dd_column")
public class AppColumn extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "tableID")
	private AppTable table;
	private String name;
	private int displayOrder;
	private String dbName;
	private boolean required = false;
	private String columnType;
	private Integer size;
	private String label;

	public AppTable getTable() {
		return table;
	}

	public void setTable(AppTable table) {
		this.table = table;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return table.getName()+"."+name + "(" + id + ")";
	}
}
