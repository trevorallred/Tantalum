package tantalum.entities;

public class CoreFactory {

	public static View createInvoice() {
		View view = createView("DefineInvoice");
		Table invoice = createTable("Invoice");
		addColumn(invoice, "InvoiceDate", ColumnType.Date);
		addColumn(invoice, "InvoiceTotal", ColumnType.Decimal);
		addColumn(invoice, "Description", ColumnType.String);

		Table account = createTable("Account");
		TableColumn invoiceAccountID = addColumn(invoice, "AccountID", ColumnType.Integer);
		addJoin(invoiceAccountID, account.getColumns().get(0));

		view.setBasisTable(invoice);
		return view;
	}

	public static View createView(String name) {
		View view = new View();
		view.setName(name);
		return view;
	}

	public static Table createTable(String name) {
		Table table = new Table();
		table.setName(name);
		table.setDbName(name.toLowerCase());
		addColumn(table, name + "ID", ColumnType.Integer);
		return table;
	}

	public static TableColumn addColumn(Table table, String name, ColumnType columnType) {
		TableColumn column = new TableColumn();
		column.setName(name);
		column.setDbName(name.toLowerCase());
		column.setColumnType(columnType);
		column.setTable(table);
		table.getColumns().add(column);
		return column;
	}

	public static Join addJoin(TableColumn fromColumn, TableColumn toColumn) {
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
