package tantalum.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tan_reference")
public class Reference extends BaseTable {
	@ManyToOne
	@JoinColumn(name = "viewID")
	private View view;
	@ManyToOne
	@JoinColumn(name = "parentID")
	private Reference parent;
	private String name;
	@ManyToOne
	@JoinColumn(name = "joinID")
	private Join join;
	@Transient
	private int alias;
	@Transient
	private Set<ReferenceJoinClause> referenceJoinClauses;

	public Reference getParent() {
		return parent;
	}

	public void setParent(Reference parent) {
		this.parent = parent;
	}

	/**
	 * Recursively return the starting point reference
	 * 
	 * @return
	 */
	public Reference getRoot() {
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

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Join getJoin() {
		return join;
	}

	public void setJoin(Join join) {
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
			for (JoinColumns jc : join.getJoinColumns()) {
				ReferenceJoinClause rjc = new ReferenceJoinClause();
				referenceJoinClauses.add(rjc);
				rjc.setToColumn(jc.getToColumn());
				rjc.setFromColumn(jc.getFromColumn());
				for (Field field : view.getParent().getFields()) {
					if (field.isBasisField()
							&& field.getBasisColumn().equals(jc.getToColumn()))
						rjc.setToField(field);
				}
				if (rjc.getToField() == null)
					System.out.println("ERROR: unable to map toField ReferenceJoinClause for " + jc.getToColumn());
				for (Field field : view.getFields()) {
					if (field.isBasisField()
							&& field.getBasisColumn().equals(jc.getFromColumn()))
						rjc.setFromField(field);
				}
				if (rjc.getFromField() == null) {
					System.out.println("ERROR - unable to map fromField ReferenceJoinClause. Looking for " + jc.getFromColumn() + " view contained fields:");
					for (Field field : view.getFields()) {
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
		for (JoinColumns jc : this.join.getJoinColumns()) {
			out += jc.toString();
		}
		return out;
	}
}
