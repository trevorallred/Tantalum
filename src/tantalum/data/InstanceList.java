package tantalum.data;

import java.util.ArrayList;
import java.util.List;

/**
 * The result of a single query. This contains a list of rows and some
 * information about those records. This is the same as a "View"
 */
public class InstanceList {
	private int totalRecords = -1;
	private List<Instance> data = new ArrayList<Instance>();

	public InstanceList() {
	}

	public InstanceList(List<Instance> list) {
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

	public List<Instance> getData() {
		return data;
	}

}
