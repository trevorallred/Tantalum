package com.opentenfold.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

}
