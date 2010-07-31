package tantalum.entities;

import org.json.simple.JSONObject;

public class CoreFactory {

	MetaTable account;
	MetaTable invoice;
	MetaTable invoiceItem;

	public void setupModel() {
		account = createTable("Account");
		addColumn(account, "Name", ColumnType.String);

		invoice = createTable("Invoice");
		addColumn(invoice, "AccountID", ColumnType.Integer);
		addJoin(invoice.getColumns().get(1), account.getColumns().get(0));
		addColumn(invoice, "InvoiceDate", ColumnType.Date);
		addColumn(invoice, "InvoiceTotal", ColumnType.Decimal);
		addColumn(invoice, "Description", ColumnType.String);

		invoiceItem = createTable("InvoiceItem");
		addColumn(invoiceItem, "InvoiceID", ColumnType.Integer);
		addJoin(invoiceItem.getColumns().get(1), invoice.getColumns().get(0));
		addColumn(invoiceItem, "Description", ColumnType.String);
		addColumn(invoiceItem, "SubTotal", ColumnType.Decimal);
	}

	public View createInvoiceView() {
		setupModel();
		View view = createView("DefineInvoice");
		view.setBasisTable(invoice);
		for (MetaColumn column : invoice.getColumns()) {
			addField(view, column);
		}
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
		MetaColumn id = addColumn(table, name + "ID", ColumnType.Integer);
		setPrimaryIndex(id);
		return table;
	}

	public static MetaColumn addColumn(MetaTable table, String name,
			ColumnType columnType) {
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

	public static void setPrimaryIndex(MetaColumn column) {
		MetaIndex index = new MetaIndex();
		MetaIndexColumn indexColumn = new MetaIndexColumn();
		indexColumn.setColumn(column);
		indexColumn.setIndex(index);
		index.getColumns().add(indexColumn);
		index.setTable(column.getTable());
		column.getTable().getIndexes().add(index);
		index.setUniqueIndex(true);
	}

	public static Field addField(View view, MetaColumn column) {
		Field field = new Field();
		field.setView(view);
		view.getFields().add(field);
		field.setBasisColumn(column);
		field.setName(view.getName() + column.getName());
		return field;
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
