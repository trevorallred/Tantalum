package tantalum.data;

import org.junit.Test;

import tantalum.entities.View;
import tantalum.entities.CoreFactory;

public class QueryBuilderTest {
	@Test
	public void buildUpdates() {
		View view = CoreFactory.createInvoice();
		System.out.println(view.toString());
	}

}
