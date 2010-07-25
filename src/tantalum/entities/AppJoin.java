package tantalum.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@javax.persistence.Table(name = "dd_join")
public class AppJoin extends BaseTable {
	private String joinType;
	@ManyToOne
	@JoinColumn(name = "fromTableID")
	private AppTable fromTable;
	private String childName;
	@ManyToOne
	@JoinColumn(name = "toTableID")
	private AppTable toTable;
	private String parentName;

	@OneToMany(mappedBy = "join", fetch = FetchType.EAGER)
	private List<AppJoinColumn> joinColumns;

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public AppTable getFromTable() {
		return fromTable;
	}

	public void setFromTable(AppTable fromTable) {
		this.fromTable = fromTable;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public AppTable getToTable() {
		return toTable;
	}

	public void setToTable(AppTable toTable) {
		this.toTable = toTable;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<AppJoinColumn> getJoinColumns() {
		return joinColumns;
	}

	public void setJoinColumns(List<AppJoinColumn> joinColumns) {
		this.joinColumns = joinColumns;
	}

}
