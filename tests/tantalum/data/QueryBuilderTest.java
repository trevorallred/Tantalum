package tantalum.data;

import org.junit.Test;

import tantalum.entities.CoreFactory;
import tantalum.entities.Model;
import tantalum.util.SelectSQL;

public class QueryBuilderTest {
	@Test
	public void buildSelect() {
		CoreFactory factory = new CoreFactory();
		Model view = factory.createInvoiceView();
		SelectSQL sql = QueryBuilder.buildSelect(view);
		System.out.println(sql);
	}
}
