package tantalum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import tantalum.data.DataReader;
import tantalum.data.DataSaver;
import tantalum.data.InstanceList;
import tantalum.data.PageContent;
import tantalum.data.InstanceUtility;
import tantalum.entities.Page;
import tantalum.entities.View;
import tantalum.ui.PageDAO;
import tantalum.util.UrlRequest;

@SuppressWarnings("serial")
public class JsonServlet extends HttpServlet {
	protected PrintWriter out;
	private PageDAO pageDAO = new PageDAO();
	private Page page = null;
	private UrlRequest urlRequest = null;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json;");
		resp.setStatus(HttpServletResponse.SC_OK);

		out = resp.getWriter();
		urlRequest = new UrlRequest(req);

		page = pageDAO.getWebPageDefinition(urlRequest.getPageName());

		DataReader dao = new DataReader();
		PageContent results = dao.getContent(page, urlRequest);

		out.print(InstanceUtility.convertToJSON(results));
		out.flush();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();
			{
				String line = reader.readLine();
				while (line != null) {
					sb.append(line + "\n");
					line = reader.readLine();
				}
			}
			reader.close();
			String data = sb.toString();

			resp.setContentType("application/json;");
			resp.setStatus(HttpServletResponse.SC_OK);

			out = resp.getWriter();
			urlRequest = new UrlRequest(req);

			page = pageDAO.getWebPageDefinition(urlRequest.getPageName());

			JSONObject root = (JSONObject) JSONValue.parse(data);

			DataSaver saver = new DataSaver();
			try {
				for (View view : page.getParentViews()) {
					JSONObject json = (JSONObject) root.get(view.getName());
					InstanceList list = InstanceUtility.convertFromJSON(json);
					saver.save(view, list);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}

			DataReader dao = new DataReader();
			PageContent results = dao.getContent(page, urlRequest);

			out.print(InstanceUtility.convertToJSON(results));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
