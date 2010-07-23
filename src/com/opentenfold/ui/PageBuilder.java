package com.opentenfold.ui;

import com.opentenfold.model.AppField;
import com.opentenfold.model.AppPage;
import com.opentenfold.model.AppView;

public class PageBuilder {
	static public StringBuilder draw(AppPage page) {
		System.out.println("Drawing page for " + page);
		String baseURL = "/TenFoldA";
		StringBuilder out = new StringBuilder();
		out.append("<html><head>");

		out.append("<title>").append(page.getTitle()).append("</title>");
		out
				.append("<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js\"></script>");
		out.append("<script type=\"text/javascript\" src=\"" + baseURL
				+ "/js/main.js\"></script>");

		out.append("<script type=\"text/javascript\">");
		for (AppView view : page.getViews()) {
			if (view.getParent() == null) {
				out.append("jQuery.getJSON('" + baseURL + "/ws/"
						+ page.getUrl() + "/" + view.getName()
						+ "', function(json){fill('" + view.getName()
						+ "',json);}); ");
			}
		}
		out.append("</script></head><body>");
		out.append("[<a href='" + baseURL + "/t/tables'>Manage Tables</a>] ");
		out.append("[<a href='" + baseURL + "/t/webpages'>List Web Pages</a>]");
		out.append("<h1>").append(page.getTitle()).append("</h1>");

		if (page.getExceptions().size() > 0) {
			for (Exception exception : page.getExceptions()) {
				out.append("<div class=>" + exception.getMessage() + "</div>");
			}
			return out;
		}

		for (AppView view : page.getViews()) {
			out.append("<div id=\"" + view.getName() + "\">");
			if (view.getResultsPerPage() == 1) {
				out.append("<form><h2>").append(view.getName()).append(
						"</h2><ul>");
				for (AppField field : view.getFields()) {
					if (field.isVisible()) {
						out.append("<li><label>" + field.getLabel()
								+ ":</label>");
						if (field.isEditable())
							out.append("<input type=\"text\" name=\""
									+ field.getName() + "\"></li>");
						else
							out.append("<span class=\"" + field.getName()
									+ "\"></span>");
						out.append("</li>");
					}
				}
				out
						.append("</ul><button name='button' value='Save'>Save</button></form>");
			} else {

				out.append("<h2>").append(view.getName()).append(
						"</h2><table><thead><tr>");
				for (AppField field : view.getFields()) {
					if (field.isVisible())
						out.append("<th><a href='" + baseURL + "/"
								+ page.getUrl() + "?orderby=" + field.getName()
								+ "'>" + field.getLabel() + "</a></th>");
				}
				out.append("</tr></thead><tbody>");

				out.append("<tr class='template'>");
				for (AppField field : view.getFields()) {
					if (field.isVisible()) {
						out.append("<td class=\"" + field.getName()
								+ " fieldContent\"></td>");
					}
				}
				out.append("</tr>");

				out.append("</tbody></table>");
			}
			out.append("</div>");
		}
		out.append("</body></html>");
		return out;
	}
}
