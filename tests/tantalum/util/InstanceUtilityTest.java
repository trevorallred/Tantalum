package tantalum.util;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import tantalum.data.InstanceList;
import tantalum.data.InstanceUtility;
import tantalum.entities.CoreFactory;
import tantalum.entities.Model;

public class InstanceUtilityTest {
	@SuppressWarnings("unchecked")
	@Test
	public void buildUpdates() {
		JSONObject json = new JSONObject();
		JSONArray data = new JSONArray();
		json.put("DATA", data);

		JSONObject row = new JSONObject();
		data.add(row);
		CoreFactory factory = new CoreFactory();
		Model view = factory.createInvoiceView();
		JSONObject rowData = createInvoiceInstance("123", "456", "2010-01-01",
				"123.45", "This is you're description");
		row.put("FIELDS", rowData);
		row.put("ACTION", null);
		row.put("DIRTY", true);

		InstanceList instanceList = InstanceUtility.convertFromJSON(json);

		assertEquals(1, instanceList.getData().size());
		System.out.println(view.toString());
		System.out.println(row.toString());

	}

	@SuppressWarnings("unchecked")
	public static JSONObject createInvoiceInstance(String invoiceID,
			String accountID, String invoiceDate, String invoiceTotal,
			String description) {
		JSONObject row = new JSONObject();
		row.put("DefineInvoiceInvoiceID", invoiceID);
		row.put("DefineInvoiceAccountID", accountID);
		row.put("DefineInvoiceInvoiceDate", invoiceDate);
		row.put("DefineInvoiceInvoiceTotal", invoiceTotal);
		row.put("DefineInvoiceDescription", description);
		return row;
	}

}
