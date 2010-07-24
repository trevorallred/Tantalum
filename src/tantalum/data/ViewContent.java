package tantalum.data;

import java.util.ArrayList;
import java.util.List;

/**
 * The result of a single query. This contains a list of rows and some
 * information about those records.
 */
public class ViewContent {
	private int totalRecords = -1;
	private List<PageContentBean> data = new ArrayList<PageContentBean>();

	public ViewContent() {
	}

	public ViewContent(List<PageContentBean> list) {
		data = list;
	}

	public int getReturnedRecords() {
		return data.size();
	}

	public int getTotalRecords() {
		if (totalRecords == -1)
			return getReturnedRecords();
		return totalRecords;
	}

	public List<PageContentBean> getData() {
		return data;
	}

}
