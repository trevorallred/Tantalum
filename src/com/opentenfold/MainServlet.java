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
import com.opentenfold.model.AppField;
import com.opentenfold.model.AppPage;
import com.opentenfold.model.AppView;
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
		AppPage page = null;
		PageContent results = new PageContent();

		page = pageDAO.getWebPageDefinition(urlRequest.getPageName());
		MainDAO dao = new MainDAO();
		for (AppView view : page.getViews()) {
			dao.setView(view);
			if (view.getParent() == null) {
				if (view.getResultsPerPage() == 1) {
					dao.getSql().addWhere(
							"id = '" + urlRequest.getPageId() + "'");
				} else {
					String[] orderbys = (String[]) urlRequest.getParameters()
							.get("orderby");
					if (orderbys != null) {
						for (String orderby : orderbys) {
							dao.getSql().addOrderBy(
									view.getField(orderby).getBasisColumn()
											.getDbName());
						}
					}
				}
			} else {
				String fieldName = "id";
				for (AppField field : view.getParent().getFields()) {
					if (field.getBasisColumn().getDbName().equals("id"))
						fieldName = field.getName();
				}
				String parentIDs = "0";
				for (PageContentBean parentRow : results.getRows(view
						.getParent().getName())) {

					parentIDs += ", " + parentRow.getInteger(fieldName);
				}
				// TODO fix this
				// dao.getSql().addWhere(
				// view.getReference().getJoin() .getFromTable()
				// FromColumnDbName() + " IN ("
				// + parentIDs + ")");
			}
			results.addViewContent(view.getName(), dao.getResults());
		}

		if (urlRequest.getParameters().containsKey("button")) {
			for (AppView view : page.getViews()) {
				dao.saveRequest(view, urlRequest);
				results.addViewContent(view.getName(), dao.getResults());
			}
		}

		try {
		} catch (Exception e) {
			page = new AppPage();
			page.setTitle("Error");
			page.addException(e);
		}
		out.print(PageBuilder.draw(page, results));
	}
}
