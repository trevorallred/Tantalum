package tantalum.util;

import tantalum.entities.*;

public class Printer {
	public static String print(MetaTable table) {
		StringBuilder out = new StringBuilder();
		out.append("<h2>Table: ").append(table.getName()).append("</h2>");
		append(out, "ID", table.getId());
		out.append("<h3>Indexes</h3><ul>");
		for (MetaIndex index : table.getIndexes()) {
			out.append("<li><b>").append(
					index.isUniqueIndex() ? " UNIQUE " : "").append(
					index.getDisplayOrder()).append("</b>: ");
			for (MetaIndexColumn ic : index.getColumns()) {
				out.append(ic.getColumn().getName()).append(", ");
			}
			out.append("</li>");
		}
		
		out.append("</ul>");
		out.append("<h3>Columns</h3><ul>");
		for (MetaColumn column : table.getColumns()) {
			out.append("<li>").append(column.getName());
			out.append(" (").append(column.getId()).append(")");
			out.append("</li>");
		}
		out.append("</ul>");
		out.append("<h3>Joins</h3><ul>");
		for (Join joinFrom : table.getJoinsFrom()) {
			out.append("<li>");
			out.append(print(joinFrom));
			out.append("</li>");
		}
		out.append("</ul>");
		return out.toString();
	}

	public static String print(Model model) {
		StringBuilder out = new StringBuilder();
		out.append("<h2>Model: ").append(model.getName()).append("</h2>");
		append(out, "ID", model.getId());
		if (model.getBasisTable() != null)
			append(out, "Based on", model.getBasisTable().getName());
		if (model.getReference() != null) {
			out.append("Reference to parent: <ul>");
			out.append(print(model.getReference()));
			out.append("</ul>");
		} else if(model.getParent() != null) {
			out.append("<p style='color: Red'>Missing Reference!!</p>");
		}
		out.append("<h3>Other References</h3><ul>");
		for (Reference reference : model.getReferences()) {
			if (reference != model.getReference())
				out.append("<li>").append(print(reference)).append("</li>");
		}
		out.append("</ul>");
		out.append("<h3>Unattached Fields</h3><ul>");
		for (Field field : model.getFields()) {
			if (field.getView() == null)
				out.append("<li>").append(print(field)).append("</li>");
		}
		out.append("</ul>");
		out.append("<h3>Views</h3><ul>");
		for (View view : model.getViews()) {
			out.append("<li>").append(print(view)).append("</li>");
		}
		out.append("</ul>");
		out.append("<ul>");
		for (Model childModel : model.getChildModels()) {
			out.append("<li>").append(print(childModel)).append("</li>");
		}
		out.append("</ul>");
		return out.toString();
	}

	private static String print(View view) {
		StringBuilder out = new StringBuilder();
		out.append("<h4>" + view.getViewType() + " " + view.getName()			+ "</h4>");
		append(out, "ID", view.getId());
		if (view.getModel() != null)
			append(out, "Basis Model", view.getModel().getName() + " " + view.getModel().getId());
		if (view.getFields().size() > 0) {
			out.append("<h5>Fields</h5><ul>");
			for (Field field : view.getFields()) {
				out.append("<li>");
				out.append(print(field));
				for (FieldAction action : field.getFieldActions()) {
					out.append("<br>Link: " + action.getName());
					out.append("(" + action.getId() + ")");
					for (FieldActionDetail detail : action.getColumns()) {
						out.append(" from ").append(detail.getFromField().getName());
						out.append(" to ").append(detail.getToField().getModel().getName() + "." + detail.getToField().getName());
					}
				}
				out.append("</li>");
			}
			out.append("</ul>");
		}

		out.append("Buttons: <ul>");
		for (Button button : view.getButtons()) {
			out.append("<li>").append(print(button)).append("</li>");
		}
		out.append("</ul>");
		
		out.append("<ul>");
		for (View child : view.getChildViews()) {
			out.append("<li>").append(print(child)).append("</li>");
		}
		out.append("</ul>");

		return out.toString();
	}

	private static Object print(Button button) {
		return button.getLabel() + " (" + button.getId() + ")";
	}

	private static String print(Reference reference) {
		StringBuilder out = new StringBuilder();
		out.append("<h4>" + reference.getName() + "</h4>");
		append(out, "Reference ID", reference.getId());
		out.append(print(reference.getJoin()));
		if (reference.getParent() != null)
			out.append("<br> Then join to: ").append(
					print(reference.getParent()));
		return out.toString();
	}

	private static String print(Join join) {
		if (join == null)
			return "Join: NULL";
		StringBuilder out = new StringBuilder();
		append(out, "Join Name", join.getName());
		append(out, "Join ID", join.getId());
		append(out, "From", join.getFromTable().getName());
		append(out, "To", join.getToTable().getName());
		out.append("On: ");
		for (JoinColumns jc : join.getJoinColumns()) {
			if (jc.getFromColumn() == null)
				out.append("<p>MISSING FROM COLUMN</p>");
			else
				out.append(jc.getFromColumn().getTable().getName()).append(".")
						.append(jc.getFromColumn().getName());
			out.append("=");
			if (jc.getToColumn() == null)
				out.append("<p>MISSING TO COLUMN</p>");
			else
				out.append(jc.getToColumn().getTable().getName()).append(".")
						.append(jc.getToColumn().getName());
			out.append(" ");
		}
		return out.toString();
	}

	private static String print(Field field) {
		String out = field.getName() + " (" + field.getId() + ")";
		if (field.getBasisColumn() != null)
			out += " based on " + field.getBasisColumn().getTable().getName()
					+ "." + field.getBasisColumn().getName();
		if (field.getDefaultField() != null)
			out += " <br>default from " + field.getDefaultField().getName();
		if (field.getReference() != null)
			out += " <br>thru reference " + field.getReference().getName();
		if (field.getSelector() != null) {
			out += " <br>selector from " + field.getSelector().getName();
			out += " with fields: ";
			for (FieldSelector selectorFields : field.getFieldSelectors()) {
				out += selectorFields.getSource().getName();
				if (selectorFields.getTarget() != null)
					out += " to "+ selectorFields.getTarget().getName();
				if (selectorFields.getWhen() != null)
					out += " when " + selectorFields.getWhen().getName();
				out += ", ";
			}
		}
		return out;
	}

	private static void append(StringBuilder out, String label, String value) {
		out.append(label).append(": ").append(value).append("<br>");
	}

}
