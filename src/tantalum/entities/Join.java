package tantalum.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tan_join")
public class Join extends BaseTable {
	@Enumerated
	private JoinType joinType = JoinType.ManyToOne;
	private String name;
	@ManyToOne
	@JoinColumn(name = "fromTableID")
	private MetaTable fromTable;
	@ManyToOne
	@JoinColumn(name = "toTableID")
	private MetaTable toTable;

	@OneToMany(mappedBy = "join", fetch = FetchType.EAGER)
	private List<JoinColumns> joinColumns = new ArrayList<JoinColumns>();

	public JoinType getJoinType() {
		return joinType;
	}

	public void setJoinType(JoinType joinType) {
		this.joinType = joinType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MetaTable getFromTable() {
		return fromTable;
	}

	public void setFromTable(MetaTable fromTable) {
		this.fromTable = fromTable;
	}

	public MetaTable getToTable() {
		return toTable;
	}

	public void setToTable(MetaTable toTable) {
		this.toTable = toTable;
	}

	public List<JoinColumns> getJoinColumns() {
		return joinColumns;
	}

	public void setJoinColumns(List<JoinColumns> joinColumns) {
		this.joinColumns = joinColumns;
	}

}
