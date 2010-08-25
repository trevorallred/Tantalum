package tantalum.data;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import tantalum.entities.CoreFactory;
import tantalum.entities.Model;

public class DataSaverTest {
	private DataSaver saver = new DataSaver();
	private CoreFactory factory = new CoreFactory();

	public DataSaverTest() {
		saver.setTesting(true);
	}

	@Test
	public void save() {
		Model model = factory.createInvoiceView();
		System.out.println(model.toString());
		Map<Model, Store> list = new HashMap<Model, Store>();
		Store store = new Store();
		list.put(model, store);

		Record invoice = new Record();
		store.getUpdates().add(invoice);
		invoice.setValue("DefineInvoiceInvoiceID", "123");
		invoice.setValue("DefineInvoiceAccountID", "456");
		invoice.setValue("DefineInvoiceInvoiceDate", "2010-01-01");
		invoice.setValue("DefineInvoiceInvoiceTotal", "123");
		invoice.setValue("DefineInvoiceDescription", "This is a description");

		try {
			saver.save(model, list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
