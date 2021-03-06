package tantalum.entities;


public class CoreFactory {

	private MetaTable account;
	private MetaTable invoice;
	private MetaTable invoiceItem;
	private Join invoiceAccountJoin;
	private Join itemInvoiceJoin;

	public void setupModel() {
		account = createTable("Account");
		addColumn(account, "Name", ColumnType.String);

		invoice = createTable("Invoice");
		addColumn(invoice, "AccountID", ColumnType.Integer);
		invoiceAccountJoin = addJoin(invoice.getColumns().get(1), account.getColumns().get(0));
		addColumn(invoice, "InvoiceDate", ColumnType.Date);
		addColumn(invoice, "InvoiceTotal", ColumnType.Decimal);
		addColumn(invoice, "Description", ColumnType.String);

		invoiceItem = createTable("InvoiceItem");
		addColumn(invoiceItem, "InvoiceID", ColumnType.Integer);
		itemInvoiceJoin = addJoin(invoiceItem.getColumns().get(1), invoice.getColumns().get(0));
		addColumn(invoiceItem, "Description", ColumnType.String);
		addColumn(invoiceItem, "SubTotal", ColumnType.Decimal);
	}

	public Model createInvoiceView() {
		setupModel();
		Model view = createModel("DefineInvoice");
		view.setBasisTable(invoice);
		for (MetaColumn column : invoice.getColumns()) {
			addField(view, column);
		}
		return view;
	}

	public Model createInvoiceWithItemsView() {
		setupModel();
		Model view = createModel("Invoice");
		view.setBasisTable(invoice);
		for (MetaColumn column : invoice.getColumns()) {
			addField(view, column);
		}
		Model itemView = createModel("Item");
		view.getChildModels().add(itemView);
		itemView.setBasisTable(invoiceItem);
		for (MetaColumn column : invoiceItem.getColumns()) {
			addField(itemView, column);
		}
		itemView.getFields().get(1).setDefaultField(view.getFields().get(0));
		Reference itemInvoiceReference = new Reference();
		itemInvoiceReference.setModel(itemView);
		itemInvoiceReference.setJoin(itemInvoiceJoin);
		itemView.setReference(itemInvoiceReference);
		return view;
	}

	public static Model createModel(String name) {
		Model view = new Model();
		view.setName(name);
		return view;
	}

	public static MetaTable createTable(String name) {
		MetaTable table = new MetaTable();
		table.setName(name);
		table.setDbName(name.toLowerCase());
		MetaColumn id = addColumn(table, name + "ID", ColumnType.Integer);
		setPrimaryIndex(id);
		addColumn(table, "createdBy", ColumnType.CreatedBy);
		addColumn(table, "updatedBy", ColumnType.UpdatedBy);
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

	public static Field addField(Model view, MetaColumn column) {
		Field field = new Field();
		field.setModel(view);
		view.getFields().add(field);
		field.setBasisColumn(column);
		field.setName(view.getName() + column.getName());
		return field;
	}
}
