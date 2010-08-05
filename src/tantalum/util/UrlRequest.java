package tantalum.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class UrlRequest {
	private String baseURL = "";
	private String pageName = "error";
	private String pageId = null;
	private String selector = null;
	private String term = null;

	@SuppressWarnings("unchecked")
	private Map parameters = null;
	private int limit = -1;

	public UrlRequest(HttpServletRequest request) {
		baseURL = request.getContextPath();
		String pathInfo = request.getPathInfo();
		if (pathInfo.startsWith("/ws/"))
			pathInfo = pathInfo.substring(3);
		if (pathInfo.startsWith("/t/"))
			pathInfo = pathInfo.substring(2);
		int divider = pathInfo.indexOf("/", 1);
		if (divider > 1) {
			pageName = pathInfo.substring(1, divider);
			pageId = pathInfo.substring(divider + 1);
		} else {
			pageName = pathInfo.substring(1);
		}
		if (request.getParameter("selector") != null)
			selector = request.getParameter("selector").toString();
		if (request.getParameter("term") != null)
			term = request.getParameter("term").toString();
		if (request.getParameter("limit") != null)
			limit  = Integer.parseInt(request.getParameter("limit"));
	}

	public String getBaseURL() {
		return baseURL;
	}

	public String getPageName() {
		return pageName;
	}

	public String getPageId() {
		return pageId;
	}

	public String getSelectorName() {
		return selector;
	}
	
	public String getSelectorValue() {
		return term;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public String toString() {
		if (pageId == null)
			return pageName;
		return pageName + ":" + pageId;
	}
}
