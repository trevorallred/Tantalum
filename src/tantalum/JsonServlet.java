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
import tantalum.data.PageContent;
import tantalum.data.PageContentUtility;
import tantalum.entities.AppField;
import tantalum.entities.AppPage;
import tantalum.entities.AppView;
import tantalum.ui.PageDAO;
import tantalum.util.UpdateSQL;
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

		BufferedReader reader = req.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line = reader.readLine();
	    while (line != null) {
	        sb.append(line + "\n");
	        line = reader.readLine();
	    }
	    reader.close();
	    String data = sb.toString();
	    
		JSONObject root = (JSONObject)JSONValue.parse(data);

		for (AppView view : page.getViews()) {
			parse(view, root.get(view.getName()));
		}
		
		setup(req, resp);

		DataReader dao = new DataReader();
		dao.saveRequest(page, urlRequest);

		out.flush();
	}
	
	private void parse(AppView view, Object raw) {
		if (raw == null)
			return;
		JSONObject json = (JSONObject)raw;
		JSONArray data = (JSONArray)json.get("DATA");
		for (Object o : data) {
			JSONObject row = (JSONObject)o;
			JSONObject rowData = (JSONObject)row.get("FIELDS");
			// Now save or update this row Data
			UpdateSQL sql = new UpdateSQL();
			for (AppField field : view.getFields()) {
				if (rowData.containsKey(field.getName()))
					sql.addField(field.getName(), rowData.get(field.getName()).toString());
			}
			
			JSONObject children = (JSONObject)row.get("CHILDREN");
			for (AppView childView : view.getChildViews()) {
				parse(childView, children.get(childView.getName()));
			}
		}
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
