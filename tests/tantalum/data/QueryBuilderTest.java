package tantalum.data;

import org.junit.Test;

import tantalum.entities.AppView;
import tantalum.entities.CoreFactory;

public class QueryBuilderTest {
	@Test
	public void buildUpdates() {
		AppView view = CoreFactory.createInvoice();
		System.out.println(view.toString());
	}

}
