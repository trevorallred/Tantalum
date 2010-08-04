package tantalum.data;

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
		Model view = factory.createInvoiceView();
		System.out.println(view.toString());
		InstanceList list = new InstanceList();
		
		Instance invoice = new Instance();
		list.getData().add(invoice);
		invoice.setValue("DefineInvoiceInvoiceID", "123");
		invoice.setValue("DefineInvoiceAccountID", "456");
		invoice.setValue("DefineInvoiceInvoiceDate", "2010-01-01");
		invoice.setValue("DefineInvoiceInvoiceTotal", "123");
		invoice.setValue("DefineInvoiceDescription", "This is a description");
		invoice.setDirty(true);
		
		try {
			saver.save(view, list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Instance item = new Instance();
		//invoice.addChildContent(childViewName, childContent)

	}
	
}
