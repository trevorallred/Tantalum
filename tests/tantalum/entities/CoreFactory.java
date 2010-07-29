package tantalum.entities;

public class CoreFactory {

	public static View createInvoice() {
		View view = createView("DefineInvoice");
		MetaTable invoice = createTable("Invoice");
		addColumn(invoice, "InvoiceDate", ColumnType.Date);
		addColumn(invoice, "InvoiceTotal", ColumnType.Decimal);
		addColumn(invoice, "Description", ColumnType.String);

		MetaTable account = createTable("Account");
		MetaColumn invoiceAccountID = addColumn(invoice, "AccountID", ColumnType.Integer);
		addJoin(invoiceAccountID, account.getColumns().get(0));

		view.setBasisTable(invoice);
		return view;
	}

	public static View createView(String name) {
		View view = new View();
		view.setName(name);
		return view;
	}

	public static MetaTable createTable(String name) {
		MetaTable table = new MetaTable();
		table.setName(name);
		table.setDbName(name.toLowerCase());
		addColumn(table, name + "ID", ColumnType.Integer);
		return table;
	}

	public static MetaColumn addColumn(MetaTable table, String name, ColumnType columnType) {
		MetaColumn column = new MetaColumn();
		column.setName(name);
		column.setDbName(name.toLowerCase());
		column.setColumnType(columnType);
		column.setTable(table);
		table.getColumns().add(column);
		return column;
	}

	public static Join addJoin(MetaColumn fromColumn, MetaColumn toColumn) {
		Join join = new Join();
		join.setJoinType("OM");
		join.setFromTable(fromColumn.getTable());
		join.setToTable(toColumn.getTable());
		JoinColumns joinColumn = new JoinColumns();
		joinColumn.setJoin(join);
		join.getJoinColumns().add(joinColumn);

		return join;
	}
}
