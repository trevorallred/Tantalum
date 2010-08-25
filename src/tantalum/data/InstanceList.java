package tantalum.data;

import java.util.ArrayList;
import java.util.List;

/**
 * The result of a single query. This contains a list of rows and some information about those records. This is the same
 * as a "View". This is model after the Ext.data.JsonReader
 */
public class InstanceList {
	private int totalRecords = -1;
	private String idProperty = "id";

	private List<Record> rows = new ArrayList<Record>();

	public InstanceList() {
	}

	public InstanceList(List<Record> list) {
		rows = list;
	}

	public String getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(String idProperty) {
		this.idProperty = idProperty;
	}

	public int getReturnedRecords() {
		return rows.size();
	}

	public int getTotalRecords() {
		if (totalRecords == -1)
			return getReturnedRecords();
		return totalRecords;
	}

	public List<Record> getRows() {
		return rows;
	}

}
