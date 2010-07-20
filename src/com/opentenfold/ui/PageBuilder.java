package com.opentenfold.ui;

import com.opentenfold.database.content.TenFoldDynaBean;
import com.opentenfold.database.content.TenFoldDynaBeanSet;
import com.opentenfold.model.Field;
import com.opentenfold.model.WebPage;

public class PageBuilder {
	static public StringBuilder draw(WebPage page, TenFoldDynaBeanSet content) {
		StringBuilder out = new StringBuilder();
		out.append("<html><head>");
		out.append("<title>").append(page.getTitle()).append("</title>");
		out.append("</head><body>");
		out.append("<h1>").append(page.getTitle()).append("</h1>");

		if (page.getExceptions().size() > 0) {
			for (Exception exception : page.getExceptions()) {
				out.append("<div class=>" + exception.getMessage() + "</div>");
			}
			return out;
		}

		if (page.getResultsPerPage() == 1) {
			TenFoldDynaBean row = content.getRows().get(0);
			out.append("<form><ul>");
			for (Field field : page.getFields()) {
				if (field.isVisible()) {
					out.append("<li><label>" + field.getLabel() + ":</label>");
					if (field.isEditable())
						out.append("<input type=\"text\" name=\""
								+ field.getName() + "\" value=\""
								+ field.getValue(row) + "\"></li>");
					else
						out.append("<span>" + field.getValue(row) + "</span>");
					out.append("</li>");
				}
			}
			out.append("</ul><button name='button' value='Save'>Save</button></form>");
		} else {

			out.append("<table><thead><tr>");
			for (Field field : page.getFields()) {
				if (field.isVisible())
					out.append("<th><a href='" + page.getUrl() + "?orderby="
							+ field.getName() + "'>" + field.getLabel()
							+ "</a></th>");
			}
			out.append("</tr></thead><tbody>");

			for (TenFoldDynaBean row : content.getRows()) {
				out.append("<tr>");
				for (Field field : page.getFields()) {
					if (field.isVisible())
						out.append("<td><a href='table/"
								+ row.getString("ManageTablesTableID") + "'>"
								+ field.getValue(row) + "</a></td>");
				}
				out.append("</tr>");
			}
		}

		out.append("</tbody></table>");
		out.append("</body></html>");
		return out;
	}
}
