package tantalum.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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
	@ManyToOne
	@JoinColumn(name = "joinID")
	private AppJoin join;
	@Transient
	private int alias;
	@Transient
	private Set<ReferenceJoinClause> referenceJoinClauses;

	public AppReference getParent() {
		return parent;
	}

	public void setParent(AppReference parent) {
		this.parent = parent;
	}

	/**
	 * Recursively return the starting point reference
	 * 
	 * @return
	 */
	public AppReference getRoot() {
		if (parent == null)
			return this;
		return parent.getRoot();
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

	public AppJoin getJoin() {
		return join;
	}

	public void setJoin(AppJoin join) {
		this.join = join;
	}

	public int getAlias() {
		return alias;
	}

	public void setAlias(int alias) {
		this.alias = alias;
	}

	public Set<ReferenceJoinClause> getReferenceJoinClauses() {
		if (referenceJoinClauses == null) {
			referenceJoinClauses = new HashSet<ReferenceJoinClause>();
			for (AppJoinColumn jc : join.getJoinColumns()) {
				ReferenceJoinClause rjc = new ReferenceJoinClause();
				referenceJoinClauses.add(rjc);
				rjc.setToColumn(jc.getToColumn());
				rjc.setFromColumn(jc.getFromColumn());
				for (AppField field : view.getParent().getFields()) {
					if (field.isBasisField()
							&& field.getBasisColumn().equals(jc.getToColumn()))
						rjc.setToField(field);
				}
				if (rjc.getToField() == null)
					System.out.println("ERROR: unable to map toField ReferenceJoinClause for " + jc.getToColumn());
				for (AppField field : view.getFields()) {
					if (field.isBasisField()
							&& field.getBasisColumn().equals(jc.getFromColumn()))
						rjc.setFromField(field);
				}
				if (rjc.getFromField() == null) {
					System.out.println("ERROR - unable to map fromField ReferenceJoinClause. Looking for " + jc.getFromColumn() + " view contained fields:");
					for (AppField field : view.getFields()) {
						System.out.println(field);
					}
				}
			}
		}
		return referenceJoinClauses;
	}

	public String toString() {
		String out = "";
		if (parent != null)
			out += "\n  Child of " + parent;
		out += "from " + this.join.getFromTable() + " to "
				+ this.join.getToTable() + " on ";
		for (AppJoinColumn jc : this.join.getJoinColumns()) {
			out += jc.toString();
		}
		return out;
	}
}
