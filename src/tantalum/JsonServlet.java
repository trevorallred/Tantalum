package tantalum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import tantalum.data.DataReader;
import tantalum.data.DataSaver;
import tantalum.data.InstanceList;
import tantalum.data.InstanceUtility;
import tantalum.data.PageContent;
import tantalum.entities.Model;
import tantalum.ui.PageDAO;
import tantalum.util.UrlRequest;

@SuppressWarnings("serial")
public class JsonServlet extends HttpServlet {
	protected PrintWriter out;
	private PageDAO pageDAO = new PageDAO();
	private Model page = null;
	private UrlRequest urlRequest = null;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_OK);

		out = resp.getWriter();
		urlRequest = new UrlRequest(req);

		JSONArray errors = new JSONArray();
		try {
			page = pageDAO.getWebPageDefinition(urlRequest.getPageName());

			DataReader dao = new DataReader();
			PageContent results = dao.getContent(page, urlRequest);

			JSONObject json = InstanceUtility.convertToJSON(results);
			json.put("__STATUS__", "success");
			
			out.print(json);
			resp.setContentType("application/json;");
		} catch (Exception e) {
			errors.add(e.getMessage());
			e.printStackTrace();
			
			JSONObject json = new JSONObject();
			json.put("__STATUS__", "error");
			json.put("errors", errors);
			out.print(json);
		}
		out.flush();
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

			JSONArray errors = new JSONArray();
			DataSaver saver = new DataSaver();
			try {
				JSONObject json = (JSONObject) root.get(page.getName());
				InstanceList list = InstanceUtility.convertFromJSON(json);
				saver.save(page, list);
				DataReader dao = new DataReader();
				PageContent results = dao.getContent(page, urlRequest);

				json = InstanceUtility.convertToJSON(results);
				json.put("__STATUS__", "success");
				out.print(json);
			} catch (Exception e) {
				errors.add(e.toString());
				e.printStackTrace();
				JSONObject json = new JSONObject();
				json.put("__STATUS__", "error");
				json.put("errors", errors);
				out.print(json);
			}
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
