package com.opentenfold;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opentenfold.database.PageDAO;
import com.opentenfold.model.AppPage;
import com.opentenfold.ui.PageBuilder;
import com.opentenfold.util.UrlRequest;

@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {
	// TODO figure out how we're going to collect and report errors

	protected PrintWriter out;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		out = response.getWriter();
		UrlRequest urlRequest = new UrlRequest(request);

		PageDAO pageDAO = new PageDAO();
		System.out.println("Getting page definition");
		AppPage page = pageDAO.getWebPageDefinition(urlRequest.getPageName());

		PageBuilder builder = new PageBuilder();
		System.out.println("Parsing velocity template");
		out.print(builder.draw(page));
	}
}
