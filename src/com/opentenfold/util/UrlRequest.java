package com.opentenfold.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class UrlRequest {
	private String pageName = "error";
	private String pageId = null;

	@SuppressWarnings("unchecked")
	private Map parameters = null;

	public UrlRequest(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();
		int divider = pathInfo.indexOf("/", 1);
		if (divider > 1) {
			pageName = pathInfo.substring(1, divider);
			pageId = pathInfo.substring(divider + 1);
		} else {
			pageName = pathInfo.substring(1);
		}
		parameters = request.getParameterMap();
	}

	public String getPageName() {
		return pageName;
	}

	public String getPageId() {
		return pageId;
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
