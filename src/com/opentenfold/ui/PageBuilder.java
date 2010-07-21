package com.opentenfold.ui;

import com.opentenfold.database.content.PageContentBean;
import com.opentenfold.database.content.PageContent;
import com.opentenfold.model.Field;
import com.opentenfold.model.View;
import com.opentenfold.model.WebPage;

public class PageBuilder {
	static public StringBuilder draw(WebPage page, PageContent content) {
		System.out.println("Drawing page for " + page);
		String baseURL = "/TenFoldA";
		StringBuilder out = new StringBuilder();
		out.append("<html><head>");
		out.append("<title>").append(page.getTitle()).append("</title>");
		out.append("</head><body>");
		out.append("[<a href='" + baseURL + "/tables'>Manage Tables</a>] ");
		out.append("[<a href='" + baseURL + "/webpages'>List Web Pages</a>]");
		out.append("<h1>").append(page.getTitle()).append("</h1>");

		if (page.getExceptions().size() > 0) {
			for (Exception exception : page.getExceptions()) {
				out.append("<div class=>" + exception.getMessage() + "</div>");
			}
			return out;
		}

		for (View view : page.getViews()) {
			if (view.getResultsPerPage() == 1) {
				PageContentBean row = content.getRows(view.getName()).get(0);
				out.append("<form><h2>").append(view.getName()).append("</h2><ul>");
				for (Field field : view.getFields()) {
					if (field.isVisible()) {
						out.append("<li><label>" + field.getLabel()
								+ ":</label>");
						if (field.isEditable())
							out.append("<input type=\"text\" name=\""
									+ field.getName() + "\" value=\""
									+ field.getValue(row) + "\"></li>");
						else
							out.append("<span>" + field.getValue(row)
									+ "</span>");
						out.append("</li>");
					}
				}
				out
						.append("</ul><button name='button' value='Save'>Save</button></form>");
			} else {

				out.append("<h2>").append(view.getName()).append("</h2><table><thead><tr>");
				for (Field field : view.getFields()) {
					if (field.isVisible())
						out.append("<th><a href='" + baseURL + "/"
								+ page.getUrl() + "?orderby=" + field.getName()
								+ "'>" + field.getLabel() + "</a></th>");
				}
				out.append("</tr></thead><tbody>");

				for (PageContentBean row : content.getRows(view.getName())) {
					out.append("<tr>");
					for (Field field : view.getFields()) {
						if (field.isVisible()) {
							if (field.getLinkFromFieldID() == null)
								out.append("<td>" + field.getValue(row)
										+ "</td>");
							else
								out.append("<td><a href='"
										+ field.getLinkToUrl()
										+ "/"
										+ row.getString(view.getField(
												field.getLinkFromFieldID())
												.getName()) + "'>"
										+ field.getValue(row) + "</a></td>");
						}
					}
					out.append("</tr>");
				}
			}
		}
		out.append("</tbody></table>");
		out.append("</body></html>");
		return out;
	}
}
