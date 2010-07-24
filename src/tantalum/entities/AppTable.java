package tantalum.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@javax.persistence.Table(name = "dd_table")
public class AppTable extends BaseTable {
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String dbName;
	@OneToMany(mappedBy = "table")
	private List<AppColumn> columns;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public List<AppColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<AppColumn> columns) {
		this.columns = columns;
	}

	/**
	 * TODO store this value on dd_table. For now we can assume it's just "id"
	 * 
	 * @return
	 */
	public AppColumn getPrimaryKey() {
		for (AppColumn column : columns) {
			if (column.getDbName().equals("id"))
				return column;
		}
		return null;
	}

	@Override
	public String toString() {
		return name + "(" + id + ")";
	}
}
