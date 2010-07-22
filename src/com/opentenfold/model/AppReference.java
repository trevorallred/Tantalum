package com.opentenfold.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "dd_reference")
public class AppReference extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "viewID")
	private AppView view;
	@ManyToOne
	@JoinColumn(name = "parentID")
	private AppReference parent;
	private String name;
	private int queryOrder;
	@ManyToOne
	@JoinColumn(name = "joinID")
	private AppJoin join;

	public AppReference getParent() {
		return parent;
	}

	public void setParent(AppReference parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AppView getView() {
		return view;
	}

	public void setView(AppView view) {
		this.view = view;
	}

	public int getQueryOrder() {
		return queryOrder;
	}

	public void setQueryOrder(int queryOrder) {
		this.queryOrder = queryOrder;
	}

	public AppJoin getJoin() {
		return join;
	}

	public void setJoin(AppJoin join) {
		this.join = join;
	}

	public String toString() {
		String out = "";
		if (parent != null)
			out += "\n  Child of " + parent;
		out += "from " + this.join.getFromTable() + " to " + this.join.getToTable() + " on ";
		for (AppJoinColumn jc : this.join.getJoinColumns()) {
			out += jc.toString();
		}
		return out;
	}
}
