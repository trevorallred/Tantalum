package tantalum.entities;

public class CoreFactory {

	public static AppView createInvoice() {
		AppView view = createView("DefineInvoice");
		AppTable invoice = createTable("Invoice");
		addColumn(invoice, "InvoiceDate");
		addColumn(invoice, "InvoiceTotal");
		addColumn(invoice, "Description");

		AppTable account = createTable("Account");
		AppColumn invoiceAccountID = addColumn(invoice, "AccountID");
		addJoin(invoiceAccountID, account.getColumns().get(0));

		view.setBasisTable(invoice);
		return view;
	}

	public static AppView createView(String name) {
		AppView view = new AppView();
		view.setName(name);
		return view;
	}

	public static AppTable createTable(String name) {
		AppTable table = new AppTable();
		table.setName(name);
		table.setDbName(name.toLowerCase());
		addColumn(table, name + "ID");
		return table;
	}

	public static AppColumn addColumn(AppTable table, String name) {
		AppColumn column = new AppColumn();
		column.setName(name);
		column.setDbName(name.toLowerCase());
		table.getColumns().add(column);
		column.setTable(table);
		return column;
	}

	public static AppJoin addJoin(AppColumn fromColumn, AppColumn toColumn) {
		AppJoin join = new AppJoin();
		join.setJoinType("OM");
		join.setFromTable(fromColumn.getTable());
		join.setToTable(toColumn.getTable());
		AppJoinColumn joinColumn = new AppJoinColumn();
		joinColumn.setJoin(join);
		join.getJoinColumns().add(joinColumn);

		return join;
	}
}
