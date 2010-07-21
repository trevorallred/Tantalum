package com.opentenfold;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opentenfold.database.MainDAO;
import com.opentenfold.database.PageDAO;
import com.opentenfold.database.content.PageContent;
import com.opentenfold.database.content.PageContentBean;
import com.opentenfold.model.Field;
import com.opentenfold.model.Reference;
import com.opentenfold.model.View;
import com.opentenfold.model.WebPage;
import com.opentenfold.ui.PageBuilder;
import com.opentenfold.util.UrlRequest;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = -6296094127479855699L;
	protected PrintWriter out;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		out = response.getWriter();

		UrlRequest urlRequest = new UrlRequest(request);
		System.out.println("Starting request for " + urlRequest);

		PageDAO pageDAO = new PageDAO();
		WebPage page = null;
		PageContent results = new PageContent();

		try {
			page = pageDAO.getWebPageDefinition(urlRequest.getPageName());

			MainDAO dao = new MainDAO();
			for (View view : page.getViews()) {
				dao.setView(view);
				if (view.getParentID() == null) {
					if (view.getResultsPerPage() == 1) {
						dao.getSql().addWhere(
								"id = '" + urlRequest.getPageId() + "'");
					} else {
						String[] orderbys = (String[]) urlRequest
								.getParameters().get("orderby");
						if (orderbys != null) {
							for (String orderby : orderbys) {
								dao.getSql()
										.addOrderBy(
												view.getField(orderby)
														.getBasisColumn().getDbName());
							}
						}
					}
				} else {
					View parentView = page.getView(view.getParentID());
					String fieldName = "id";
					for (Field field : parentView.getFields()) {
						if (field.getBasisColumn().getDbName().equals("id"))
							fieldName = field.getName();
					}
					Reference referenceToParent = view.getReference(view
							.getReferenceID());
					String parentIDs = "0";
					for (PageContentBean parentRow : results.getRows(parentView
							.getName())) {

						parentIDs += ", " + parentRow.getInteger(fieldName);
					}
					dao.getSql().addWhere(referenceToParent.getFromColumnDbName() + " IN (" + parentIDs + ")");
				}
				results.addViewContent(view.getName(), dao.getResults());
			}

			if (urlRequest.getParameters().containsKey("button")) {
				for (View view : page.getViews()) {
					dao.saveRequest(view, urlRequest);
					results.addViewContent(view.getName(), dao.getResults());
				}
			}

		} catch (Exception e) {
			page = new WebPage();
			page.setTitle("Error");
			page.addException(e);
		}
		out.print(PageBuilder.draw(page, results));
	}
}
