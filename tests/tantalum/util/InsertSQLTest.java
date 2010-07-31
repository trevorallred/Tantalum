package tantalum.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class InsertSQLTest {
	@Test
	public void testInsert() {
		InsertSQL sql = new InsertSQL("person");
		{
			Map<String, String> row = new HashMap<String, String>();
			row.put("firstName", "John");
			row.put("lastName", "Doe");
			sql.addRow(row);
		}
		{
			Map<String, String> row = new HashMap<String, String>();
			row.put("firstName", "Thomas");
			row.put("lastName", "O'Neal");
			row.put("lastUpdateDate", InsertSQL.TIMESTAMP);
			sql.addRow(row);
		}
		System.out.println(sql.toString());
	}
}
