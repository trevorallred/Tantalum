(function() {
	var writer = new Ext.data.JsonWriter( {});
	
	Tantalum.tableStore = Ext.extend(Ext.data.JsonStore, {
		saveQueue : new Object(),
		constructor : function(cfg) {
			cfg = cfg || {};
			Tantalum.tableStore.superclass.constructor.call(this, Ext.apply( {
				url : 'ws/DefineTable',
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
		joinParentStore.loadData(tableStore.reader.jsonData);
		tableIndexesStore.loadData(tableStore.reader.jsonData);
		tableIndexColumnsStore.loadData(tableStore.reader.jsonData);
		if (tableStore.getCount() > 0) {
			columnStore.filter('DefineTableColumnTableID', tableStore.getAt(0).data.DefineTableTableID);
			joinParentStore.filter('JoinFromTableID', tableStore.getAt(0).data.DefineTableTableID);
			tableIndexesStore.filter('ModelIndexTableID', tableStore.getAt(0).data.DefineTableTableID);
			if (tableIndexesStore.snapshot.length > 0) {
				tableIndexColumnsStore.filter('TableIndexColumnsIndexID',
						tableIndexesStore.getAt(0).data.ModelIndexIndexID);
			}
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

	Tantalum.joinParentStore = Ext.extend(Ext.data.JsonStore, {
		constructor : function(cfg) {
			cfg = cfg || {};
			Tantalum.joinParentStore.superclass.constructor.call(this, Ext.apply( {
				storeId : 'joinParentStore',
				idProperty : 'JoinJoinID',
				autoDestroy : true,
				root : 'JoinParent.read',
				fields : [ {
					name : 'JoinFromTableID'
				}, {
					name : 'JoinToTableID'
				}, {
					name : 'JoinToTableName'
				}, {
					name : 'JoinName'
				}, {
					name : 'JoinJoinType'
				} ]
			}, cfg));
		}
	});
	var joinParentStore = new Tantalum.joinParentStore();

	Tantalum.TableIndexesStore = Ext.extend(Ext.data.JsonStore, {
		constructor : function(cfg) {
			cfg = cfg || {};
			Tantalum.TableIndexesStore.superclass.constructor.call(this, Ext.apply( {
				idProperty : 'ModelIndexIndexID',
				autoDestroy : true,
				root : 'TableIndexes.read',
				fields : [ {
					name : 'ModelIndexIndexID'
				}, {
					name : 'ModelIndexTableID'
				}, {
					name : 'ModelIndexDisplayOrder'
				}, {
					name : 'ModelIndexUniqueIndex'
				} ]
			}, cfg));
		}
	});
	var tableIndexesStore = new Tantalum.TableIndexesStore();

	Tantalum.TableIndexColumnsStore = Ext.extend(Ext.data.JsonStore, {
		constructor : function(cfg) {
			cfg = cfg || {};
			Tantalum.TableIndexColumnsStore.superclass.constructor.call(this, Ext.apply( {
				idProperty : 'TableIndexColumnsID',
				autoDestroy : true,
				root : 'TableIndexColumns.read',
				fields : [ {
					name : 'TableIndexColumnsID'
				}, {
					name : 'TableIndexColumnsIndexID'
				}, {
					name : 'TableIndexColumnsColumnID'
				}, {
					name : 'TableIndexColumnsColumnName'
				} ]
			}, cfg));
		}
	});
	var tableIndexColumnsStore = new Tantalum.TableIndexColumnsStore();

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
		title : 'DefineTables',
		layout : 'border',
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
					sm : new Ext.grid.RowSelectionModel( {
						singleSelect : true,
						listeners : {
							rowselect : {
								fn : function(sm, index, record) {
									if (record && (this.boundRecord !== record)) {
										Ext.BindMgr.unbind(this.boundRecord);
										Ext.BindMgr.bind(record, [ 'defineTableTableForm' ]);
										this.boundRecord = record;
										columnStore.filter('DefineTableColumnTableID', record.data.DefineTableTableID);
										joinParentStore.filter('JoinFromTableID', record.data.DefineTableTableID);
										tableIndexesStore.filter('ModelIndexTableID', record.data.DefineTableTableID);
										if (tableIndexesStore.snapshot.length > 0) {
											tableIndexColumnsStore.filter('TableIndexColumnsIndexID', tableIndexesStore
													.getAt(0).data.ModelIndexIndexID);
										}
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
						width : 147,
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
								items : [
										{
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
																store : new Ext.data.ArrayStore(
																		{
																			id : 0,
																			fields : [ 'enumCode', 'meaning' ],
																			data : [ [ '0', 'UUID' ],
																					[ '1', 'String' ],
																					[ '2', 'Number' ],
																					[ '3', 'CreationDate' ],
																					[ '5', 'CreatedBy' ],
																					[ '4', 'UpdateDate' ],
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
										},
										{
											xtype : 'panel',
											title : 'Joins',
											layout : 'vbox',
											layoutConfig : {
												align : 'stretch'
											},
											items : [ {
												xtype : 'editorgrid',
												store : joinParentStore,
												flex : 1,
												columns : [ {
													xtype : 'gridcolumn',
													dataIndex : 'JoinJoinType',
													header : 'Type'
												}, {
													xtype : 'gridcolumn',
													dataIndex : 'JoinName',
													header : 'Name'
												}, {
													xtype : 'gridcolumn',
													dataIndex : 'JoinToTableName',
													header : 'To Table'
												} ]
											}, {
												xtype : 'editorgrid',
												store : joinParentStore,
												title : 'Join Column(s)',
												flex : 1,
												columns : [ {
													xtype : 'gridcolumn',
													dataIndex : 'JoinToTableName',
													header : 'To Table'
												}, {
													xtype : 'gridcolumn',
													dataIndex : 'JoinName',
													header : 'Name'
												}, {
													xtype : 'gridcolumn',
													dataIndex : 'JoinJoinType',
													header : 'Type'
												} ]
											} ]
										},
										{
											xtype : 'panel',
											title : 'Indexes',
											layout : 'hbox',
											layoutConfig : {
												align : 'stretch'
											},
											items : [
													{
														xtype : 'editorgrid',
														store : tableIndexesStore,
														flex : 1,
														sm : new Ext.grid.RowSelectionModel( {
															singleSelect : true,
															listeners : {
																rowselect : {
																	fn : function(sm, index, record) {
																		if (record && (this.boundRecord !== record)) {
																			tableIndexColumnsStore.filter(
																					'TableIndexColumnsIndexID',
																					record.data.ModelIndexIndexID);
																		} else if (!record) {
																			tableIndexColumnsStore.filter(
																					'TableIndexColumnsIndexID', '');
																		}
																	}
																}
															}
														}),
														columns : [ {
															xtype : 'gridcolumn',
															dataIndex : 'ModelIndexIndexID',
															hidden : true,
															header : 'ID'
														}, {
															xtype : 'gridcolumn',
															dataIndex : 'ModelIndexDisplayOrder',
															header : 'Order'
														}, {
															xtype : 'gridcolumn',
															dataIndex : 'ModelIndexUniqueIndex',
															header : 'Unique'
														} ]
													}, {
														xtype : 'editorgrid',
														store : tableIndexColumnsStore,
														title : 'Index Columns',
														flex : 1,
														columns : [ {
															xtype : 'gridcolumn',
															dataIndex : 'ModelIndexDisplayOrder',
															header : 'Order'
														}, {
															xtype : 'gridcolumn',
															dataIndex : 'TableIndexColumnsColumnName',
															header : 'Column'
														} ]
													} ]
										} ]
							} ]
				} ]
	});

	return page;
})();
