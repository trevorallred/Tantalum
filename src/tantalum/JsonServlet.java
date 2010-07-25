package tantalum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tantalum.data.DataReader;
import tantalum.data.PageContent;
import tantalum.data.PageContentUtility;
import tantalum.entities.AppPage;
import tantalum.ui.PageDAO;
import tantalum.util.UrlRequest;


@SuppressWarnings("serial")
public class JsonServlet extends HttpServlet {
	protected PrintWriter out;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//resp.setContentType("application/json;");

		out = resp.getWriter();
		UrlRequest urlRequest = new UrlRequest(req);

		PageDAO pageDAO = new PageDAO();
		AppPage page = pageDAO.getWebPageDefinition(urlRequest.getPageName());

		DataReader dao = new DataReader();
		PageContent results = dao.getContent(page, urlRequest);

		out.print(PageContentUtility.convertToJSON(results));
		out.flush();

		super.doGet(req, resp);
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setStatus(HttpServletResponse.SC_CREATED);
		out = resp.getWriter();
		UrlRequest urlRequest = new UrlRequest(req);

		PageDAO pageDAO = new PageDAO();
		AppPage page = pageDAO.getWebPageDefinition(urlRequest.getPageName());

		DataReader dao = new DataReader();
		PageContent results = dao.getContent(page, urlRequest);

		out.print(PageContentUtility.convertToJSON(results));
		out.flush();
		return;
	}
	
}
