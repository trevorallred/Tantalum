package tantalum.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
	@OrderBy(value = "displayOrder")
	private List<MetaColumn> columns = new ArrayList<MetaColumn>();
	@OneToMany(mappedBy = "table")
	@OrderBy(value = "displayOrder")
	private List<MetaIndex> indexes = new ArrayList<MetaIndex>();

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

	public List<MetaIndex> getIndexes() {
		return indexes;
	}

	public void setIndexes(List<MetaIndex> indexes) {
		this.indexes = indexes;
	}

	/**
	 * TODO store this value on tan_table. For now we can assume it's just "id"
	 * 
	 * @return
	 */
	public MetaColumn getPrimaryKey() {
		if (indexes == null)
			return null;
		for (MetaIndex index : indexes) {
			if (index.isUniqueIndex()) {
				return index.getColumns().get(0).getColumn();
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return name + "(" + id + ")";
	}
}
