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
	private PageDAO pageDAO = new PageDAO();
	private AppPage page = null;
	private UrlRequest urlRequest = null;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		setup(req, resp);

		DataReader dao = new DataReader();
		PageContent results = dao.getContent(page, urlRequest);

		out.print(PageContentUtility.convertToJSON(results));
		out.flush();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		setup(req, resp);

		DataReader dao = new DataReader();
		dao.saveRequest(page, urlRequest);

		out.flush();
	}

	private void setup(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json;");
		// resp.setStatus(HttpServletResponse.SC_CREATED);
		resp.setStatus(HttpServletResponse.SC_OK);

		out = resp.getWriter();
		urlRequest = new UrlRequest(req);

		page = pageDAO.getWebPageDefinition(urlRequest.getPageName());
	}
}
