package tantalum.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tan_table")
public class MetaTable extends BaseTable {
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String dbName;
	@ManyToOne
	@JoinColumn(name = "databaseID")
	private Database database;
	@OneToMany(mappedBy = "table")
	private List<MetaColumn> columns = new ArrayList<MetaColumn>();

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

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public List<MetaColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<MetaColumn> columns) {
		this.columns = columns;
	}

	/**
	 * TODO store this value on tan_table. For now we can assume it's just "id"
	 * 
	 * @return
	 */
	public MetaColumn getPrimaryKey() {
		for (MetaColumn column : columns) {
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
