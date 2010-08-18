Ext.ns('Tantalum');
Tantalum.DefineTableUi = Ext.extend(Ext.Viewport, {
	layout : 'border',
	initComponent : function() {
		var tableStore = new Tantalum.tableStore();
		var columnStore = new Tantalum.columnStore();
		var joinParentStore = new Tantalum.joinParentStore();
		tableStore.on('load', function(store) {
			columnStore.loadData(tableStore.reader.jsonData);
			joinParentStore.loadData(tableStore.reader.jsonData);
			if (tableStore.getCount() > 0) {
				columnStore.filter('DefineTableColumnTableID', tableStore.getAt(0).data.DefineTableTableID);
				joinParentStore.filter('JoinFromTableID', tableStore.getAt(0).data.DefineTableTableID);
			}
		});

		this.items = [ {
			xtype : 'editorgrid',
			store : tableStore,
			width : 150,
			title : 'Table',
			defaults : {
				"sortable" : true
			},
			sm: new Ext.grid.RowSelectionModel({
		    	singleSelect: true,
		    	listeners: {
	        	rowselect: {
		        	fn: function(sm,index,record) {
			        	if(record && (this.boundRecord !== record)) {
			                Ext.BindMgr.unbind(this.boundRecord);
			                Ext.BindMgr.bind(record, ['defineTableTableForm']);
			                this.boundRecord = record;
			                columnStore.filter('DefineTableColumnTableID', record.data.DefineTableTableID);
							joinParentStore.filter('JoinFromTableID', record.data.DefineTableTableID);
			            } else if(!record) {
	                        Ext.BindMgr.unbind(this.boundRecord);
	                        this.boundRecord = null;
	                    }
		        	}
	        	}
	    	}

		    }),
			region : 'west',
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
		}, {
			xtype : 'panel',
			layout : 'border',
			region : 'center',
			items : [ {
				autoHeight : true,
				xtype : 'tableForm',
				region : 'north',
				id : 'defineTableTableForm'
			}, {
				xtype : 'tabpanel',
				activeTab : 0,
				region : 'center',
				items : [ {
					xtype : 'panel',
					title : 'Columns',
					layout : 'vbox',
					layoutConfig : {
						align: 'stretch'
					},
					items : [ {
						xtype : 'editorgrid',
						flex : 1,
						store : columnStore,
						stripeRows : true,
						columns : [ {
							xtype : 'numbercolumn',
							dataIndex : 'DefineTableColumnDisplayOrder',
							header : 'Order',
							format : '0,000',
							align : 'right',
							editor : {
								xtype : 'textfield'
							}
						}, {
							xtype : 'gridcolumn',
							dataIndex : 'DefineTableColumnName',
							header : 'Column Name',
							editor : {
								xtype : 'textfield'
							}
						}, {
							xtype : 'gridcolumn',
							dataIndex : 'DefineTableColumnDbName',
							header : 'Database',
							editor : {
								xtype : 'textfield'
							}
						}, {
							xtype : 'booleancolumn',
							dataIndex : 'DefineTableColumnRequired',
							header : 'Required',
							sortable : true,
							width : 100,
							editor : {
								xtype : 'checkbox',
								boxLabel : 'BoxLabel'
							}
						} ]
					} ]
				}, {
					xtype : 'panel',
					title : 'Joins',
					layout : 'vbox',
					layoutConfig : {
						align: 'stretch'
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
				}, {
					xtype : 'panel',
					title : 'Indexes'
				} ]
			}, {
				xtype : 'toolbar',
				autoHeight : true,
				region : 'south',
				items : [ {
					xtype : 'button',
					text : 'MyButton'
				} ]
			} ]
		} ];
		Tantalum.DefineTableUi.superclass.initComponent.call(this);
		tableStore.load();
	}
});

Tantalum.tableForm = Ext.extend(Ext.form.FormPanel, {
    defaultType: 'textfield',
    onBind:function(record) {
        this.boundRecord = record;
        this.updateBound(record);
    },
    onUnbind:function(record) {
        this.boundRecord = null;
        this.updateBound();
    },
    afterEdit:this.updateBound,
    afterReject:this.updateBound,
    updateBound:function(record) {
        this.form.loadRecord(record ? record : {data:{DefineTableName:'', DefineTableDatabaseName:''}});
    },
    initComponent: function() {
        this.items = [
            {
    	    	name:'DefineTableName',
    			fieldLabel:'Table name',
    			emptyText: "TableName"
    	    },{
    	    	name:'DefineTableDatabaseName',
    			fieldLabel:'Database implementation',
       			emptyText: 'table_name'
            }
        ];
        
        Tantalum.tableForm.superclass.initComponent.call(this);
    }
});
Ext.reg('tableForm', Tantalum.tableForm);

Tantalum.tableStore = Ext.extend(Ext.data.JsonStore, {
	constructor : function(cfg) {
		cfg = cfg || {};
		Tantalum.tableStore.superclass.constructor.call(this, Ext.apply( {
			storeId : 'tableStore',
			idProperty : 'DefineTableTableID',
			autoDestroy : true,
			url : '/Tantalum/ws/DefineTable',
			root : 'DefineTable',
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

Tantalum.columnStore = Ext.extend(Ext.data.JsonStore, {
	constructor : function(cfg) {
		cfg = cfg || {};
		Tantalum.columnStore.superclass.constructor.call(this, Ext.apply( {
			storeId : 'columnStore',
			idProperty : 'DefineTableColumnID',
			autoDestroy : true,
			root : 'Column',
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
			} ]
		}, cfg));
	}
});


Tantalum.joinParentStore = Ext.extend(Ext.data.JsonStore, {
	constructor : function(cfg) {
		cfg = cfg || {};
		Tantalum.joinParentStore.superclass.constructor.call(this, Ext.apply( {
			storeId : 'joinParentStore',
			idProperty : 'JoinJoinID',
			autoDestroy : true,
			root : 'JoinParent',
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
