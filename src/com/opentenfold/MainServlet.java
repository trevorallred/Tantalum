package com.opentenfold;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opentenfold.database.MainDAO;
import com.opentenfold.database.PageDAO;
import com.opentenfold.database.content.TenFoldDynaBeanSet;
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

		PageDAO pageDAO = new PageDAO();
		WebPage page = null;
		TenFoldDynaBeanSet results = null;

		try {
			page = pageDAO.getWebPageDefinition(urlRequest.getPageName());

			MainDAO dao = new MainDAO();
			for (View view : page.getViews()) {
				results = dao.getResults(view, urlRequest);
			}

			if (urlRequest.getParameters().containsKey("button")) {
				for (View view : page.getViews()) {
					dao.saveRequest(view, urlRequest);
					results = dao.getResults(view, urlRequest);
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
