package tantalum.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@javax.persistence.Table(name = "tan_join")
public class Join extends BaseTable {
	private String joinType;
	@ManyToOne
	@JoinColumn(name = "fromTableID")
	private Table fromTable;
	private String childName;
	@ManyToOne
	@JoinColumn(name = "toTableID")
	private Table toTable;
	private String parentName;

	@OneToMany(mappedBy = "join", fetch = FetchType.EAGER)
	private List<JoinColumns> joinColumns;

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public Table getFromTable() {
		return fromTable;
	}

	public void setFromTable(Table fromTable) {
		this.fromTable = fromTable;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public Table getToTable() {
		return toTable;
	}

	public void setToTable(Table toTable) {
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
