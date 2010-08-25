(function() {
	var writer = new Ext.data.JsonWriter( {});

	Tantalum.tableStore = Ext.extend(Ext.data.JsonStore, {
		saveQueue : new Object(),
		constructor : function(cfg) {
			cfg = cfg || {};
			Tantalum.tableStore.superclass.constructor.call(this, Ext.apply( {
				url : '/Tantalum/ws/DefineTable',
				root : 'DefineTable.read',
				batch : true,
				autoSave : false,
				pruneModifiedRecords : true,
				writer : writer,
				idProperty : 'DefineTableTableID',
				fields : [ {
					name : 'DefineTableTableID'
				}, {
					name : 'DefineTableName'
				}, {
					name : 'DefineTableDatabaseName'
				} ]
			}, cfg));
		}
	});
	var tableStore = new Tantalum.tableStore();
	tableStore.on('load', function(store) {
		columnStore.loadData(tableStore.reader.jsonData);
		if (tableStore.getCount() > 0) {
			columnStore.filter('DefineTableColumnTableID', tableStore.getAt(0).data.DefineTableTableID);
		}
	});

	Tantalum.columnStore = Ext.extend(Ext.data.JsonStore, {
		constructor : function(cfg) {
			cfg = cfg || {};
			Tantalum.columnStore.superclass.constructor.call(this, Ext.apply( {
				idProperty : 'DefineTableColumnID',
				autoDestroy : true,
				root : 'Column.read',
				fields : [ {
					name : 'DefineTableColumnID'
				}, {
					name : 'DefineTableColumnName'
				}, {
					name : 'DefineTableColumnDbName'
				}, {
					name : 'DefineTableColumnTableID'
				}, {
					name : 'DefineTableColumnDisplayOrder'
				}, {
					name : 'DefineTableColumnRequired'
				}, {
					name : 'ColumnColumnType'
				} ]
			}, cfg));
		}
	});
	var columnStore = new Tantalum.columnStore();

	tableStore.load();

	Tantalum.tableForm = Ext.extend(Ext.form.FormPanel, {
		defaultType : 'textfield',
		padding : 5,
		onBind : function(record) {
			this.boundRecord = record;
			this.updateBound(record);
		},
		onUnbind : function(record) {
			this.boundRecord = null;
			this.updateBound();
		},
		afterEdit : this.updateBound,
		afterReject : this.updateBound,
		updateBound : function(record) {
			this.form.loadRecord(record ? record : {
				data : {
					DefineTableName : '',
					DefineTableDatabaseName : ''
				}
			});
		},
		initComponent : function() {
			this.items = [ {
				name : 'DefineTableName',
				fieldLabel : 'Table name',
				emptyText : "TableName"
			}, {
				name : 'DefineTableDatabaseName',
				fieldLabel : 'Database implementation',
				emptyText : 'table_name'
			} ];

			Tantalum.tableForm.superclass.initComponent.call(this);
		}
	});
	Ext.reg('tableForm', Tantalum.tableForm);

	var page = new Ext.Panel( {
		currentStore : null,
		title : 'DefineTables',
		layout : 'border',
		refresh : function() {
			tableStore.reload();
		},
		insertRecord : function() {
			if (this.currentStore === null)
				return;
			var r = new this.currentStore.recordType();
			this.currentStore.add(r);
			//this.getSelectionModel().select(this.store.getCount() - 1, 0);
		},
		deleteRecord : function() {
			if (this.currentStore === null)
				return;
		},
		items : [
				{
					xtype : 'editorgrid',
					region : 'west',
					store : tableStore,
					width : 150,
					title : 'Table',
					defaults : {
						"sortable" : true
					},
					listeners : {
						viewready : function(g) {
							//g.getSelectionModel().selectRow(0);
						}
					},
					sm : new Ext.grid.RowSelectionModel( {
						singleSelect : false,
						listeners : {
							rowselect : {
								fn : function(sm, index, record) {
									page.currentStore = tableStore;
									if (record && (this.boundRecord !== record)) {
										Ext.BindMgr.unbind(this.boundRecord);
										Ext.BindMgr.bind(record, [ 'defineTableTableForm' ]);
										this.boundRecord = record;
										columnStore.filter('DefineTableColumnTableID', record.data.DefineTableTableID);
									} else if (!record) {
										Ext.BindMgr.unbind(this.boundRecord);
										this.boundRecord = null;
									}
								}
							}
						}
					}),
					split : true,
					stripeRows : true,
					collapsible : true,
					columns : [ {
						xtype : 'gridcolumn',
						dataIndex : 'DefineTableName',
						sortable : true,
						groupable : false,
						width : 145,
						editable : false,
						hideable : false,
						editor : {
							xtype : 'textfield'
						}
					} ]
				},
				{
					xtype : 'panel',
					layout : 'border',
					region : 'center',
					items : [
							{
								autoHeight : true,
								xtype : 'tableForm',
								region : 'north',
								id : 'defineTableTableForm'
							},
							{
								xtype : 'tabpanel',
								activeTab : 0,
								region : 'center',
								items : [ {
									xtype : 'panel',
									title : 'Columns',
									layout : 'vbox',
									layoutConfig : {
										align : 'stretch'
									},
									items : [ {
										xtype : 'editorgrid',
										flex : 1,
										store : columnStore,
										stripeRows : true,
										sm : new Ext.grid.RowSelectionModel( {
											singleSelect : false,
											listeners : {
												rowselect : {
													fn : function(sm, index, record) {
														page.currentStore = columnStore;
													}
												}
											}
										}),
										columns : [
												{
													xtype : 'numbercolumn',
													dataIndex : 'DefineTableColumnDisplayOrder',
													header : 'Order',
													format : '0,000',
													align : 'right',
													editor : {
														xtype : 'textfield'
													}
												},
												{
													xtype : 'gridcolumn',
													dataIndex : 'DefineTableColumnName',
													header : 'Column Name',
													editor : {
														xtype : 'textfield'
													}
												},
												{
													xtype : 'gridcolumn',
													dataIndex : 'DefineTableColumnDbName',
													header : 'Database',
													editor : {
														xtype : 'textfield'
													}
												},
												{
													xtype : 'gridcolumn',
													dataIndex : 'ColumnColumnType',
													header : 'Type',
													editor : {
														xtype : 'combo',
														typeAhead : true,
														triggerAction : 'all',
														lazyRender : true,
														allowBlank : false,
														forceSelection : true,
														selectOnFocus : true,
														title : 'Column Type',
														mode : 'local',
														listeners : {
															change : function(combo, newVal, oldVal) {
																alert(newVal);
															}
														},
														store : new Ext.data.ArrayStore( {
															id : 0,
															fields : [ 'enumCode', 'meaning' ],
															data : [ [ '0', 'UUID' ], [ '1', 'String' ],
																	[ '2', 'Number' ], [ '3', 'CreationDate' ],
																	[ '5', 'CreatedBy' ], [ '4', 'UpdateDate' ],
																	[ '6', 'UpdatedBy' ] ]
														}),
														displayField : 'meaning'
													}
												}, {
													xtype : 'booleancolumn',
													dataIndex : 'DefineTableColumnRequired',
													header : 'Required',
													sortable : true,
													width : 100,
													editor : {
														xtype : 'checkbox'
													}
												} ]
									} ]
								} ]
							} ]
				} ]
	});

	page.currentStore = columnStore;
	return page;
})();
