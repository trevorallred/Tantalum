package tantalum.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tan_join")
public class Join extends BaseTable {
	private String joinType;
	@ManyToOne
	@JoinColumn(name = "fromTableID")
	private MetaTable fromTable;
	private String childName;
	@ManyToOne
	@JoinColumn(name = "toTableID")
	private MetaTable toTable;
	private String parentName;

	@OneToMany(mappedBy = "join", fetch = FetchType.EAGER)
	private List<JoinColumns> joinColumns;

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public MetaTable getFromTable() {
		return fromTable;
	}

	public void setFromTable(MetaTable fromTable) {
		this.fromTable = fromTable;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public MetaTable getToTable() {
		return toTable;
	}

	public void setToTable(MetaTable toTable) {
		this.toTable = toTable;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<JoinColumns> getJoinColumns() {
		return joinColumns;
	}

	public void setJoinColumns(List<JoinColumns> joinColumns) {
		this.joinColumns = joinColumns;
	}

}
