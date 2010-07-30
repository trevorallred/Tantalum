package tantalum.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import tantalum.entities.CoreFactory;
import tantalum.entities.View;
import tantalum.util.UpdateSQL;

public class QueryBuilderTest {
	@SuppressWarnings("unchecked")
	@Test
	public void buildUpdates() {
		JSONObject json = new JSONObject();
		JSONArray data = new JSONArray();
		json.put("DATA", data);
		
		JSONObject row = new JSONObject();
		data.add(row);
		CoreFactory factory = new CoreFactory();
		View view = factory.createInvoiceView();
		JSONObject rowData = CoreFactory.createInvoiceInstance("123", "456", "2010-01-01", "123.45", "This is you're description");
		row.put("FIELDS", rowData);

		System.out.println(view.toString());
		System.out.println(row.toString());

		for (UpdateSQL updateSQL : QueryBuilder.buildUpdates(view, json)) {
			System.out.println(updateSQL);
		}
	}

}
