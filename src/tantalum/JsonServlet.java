package tantalum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import tantalum.data.DataReader;
import tantalum.data.DataSaver;
import tantalum.data.InstanceUtility;
import tantalum.data.Record;
import tantalum.data.Store;
import tantalum.entities.Model;
import tantalum.ui.PageDAO;
import tantalum.util.RequestFilter;
import tantalum.util.UrlRequest;

@SuppressWarnings("serial")
public class JsonServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;");
		resp.setStatus(HttpServletResponse.SC_OK);

		JSONArray errors = new JSONArray();
		JSONObject json = new JSONObject();
		boolean success = true;

		boolean query = true; // Assume we are running a query
		try {
			if (req.getParameterValues("xaction")[0].equals("save"))
				query = false;
		} catch (Exception e) {
			errors.add("xaction was missing from request object. Using default of 'query'.");
		}

		UrlRequest urlRequest = new UrlRequest(req);
		Model page = new PageDAO().getWebPageDefinition(urlRequest.getPageName());

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
			JSONObject requestJson = (JSONObject) JSONValue.parse(sb.toString());

			if (query) {
				// Read data for set
				DataReader dao = new DataReader();
				RequestFilter filter = new RequestFilter(req);
				Map<Model, List<Record>> results = dao.getContent(page, filter);

				for (Model model : results.keySet()) {
					JSONObject modelJson = new JSONObject();
					modelJson.put("total", dao.getContentCount(model));
					modelJson.put("read", InstanceUtility.convertToJSON(results.get(model)));
					json.put(model.getName(), modelJson);
				}
			} else {
				// Save data for set
				DataSaver saver = new DataSaver();
				Map<Model, Store> stores = InstanceUtility.convertFromJSON(page, requestJson);
				saver.save(page, stores);
				json = InstanceUtility.convertToJSON(page, stores);
			}

		} catch (Exception e) {
			errors.add(e.getMessage());
			success = false;
		}

		json.put("success", success);
		if (errors.size() > 0)
			json.put("errors", errors);

		PrintWriter out = resp.getWriter();
		out.print(json.toJSONString());
		out.flush();
	}
}
