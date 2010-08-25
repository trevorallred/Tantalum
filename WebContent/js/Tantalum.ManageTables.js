(function() {
	var writer = new Ext.data.JsonWriter( {});

	Tantalum.tableStore = Ext.extend(Ext.data.JsonStore, {
		saveQueue : new Object(),
		constructor : function(cfg) {
			cfg = cfg || {};
			Tantalum.tableStore.superclass.constructor.call(this, Ext.apply( {
				url : '/Tantalum/ws/ManageTables',
				root : 'ManageTables.read',
				batch : true,
				autoSave : false,
				pruneModifiedRecords : true,
				writer : writer,
				idProperty : 'ManageTablesTableID',
				fields : [ {
					name : 'ManageTablesTableID'
				}, {
					name : 'ManageTablesName',
					allowBlank : false
				}, {
					name : 'ManageTablesDatabaseName',
					allowBlank : false
				} ]
			}, cfg));
		}
	});
	var tableStore = new Tantalum.tableStore();
	tableStore.addListener('beforewrite', function(store, action, rs, options) {
		store.saveQueue[action] = [];
		if (Ext.isArray(rs)) {
			store.saveQueue[action] = rs;
		} else {
			store.saveQueue[action].push(rs);
		}
		return false;
	});
	tableStore.load();

	var page = new Ext.grid.EditorGridPanel( {
		title : 'ManageTables',
		store : tableStore,
		defaults : {
			sortable : true
		},
		stripeRows : true,
		columns : [ {
			header : 'Table ID',
			xtype : 'gridcolumn',
			width : 230,
			dataIndex : 'ManageTablesTableID',
			hidden : true,
			editable : false
		}, {
			header : 'Table name',
			xtype : 'gridcolumn',
			dataIndex : 'ManageTablesName',
			width : 140,
			sortable : true,
			editor : {
				xtype : 'textfield'
			}
		}, {
			header : 'Database Implementation',
			xtype : 'gridcolumn',
			dataIndex : 'ManageTablesDatabaseName',
			width : 170,
			sortable : true,
			editor : {
				xtype : 'textfield'
			}
		} ],
		refresh : function() {
			this.store.reload();
		},
		save : function() {
			this.store.save();

			var savejson = new Object();
			var savejsonModel = new Object();
			for ( var action in this.store.saveQueue) {
				if (Ext.isDefined(action)) {
					savejsonModel[action] = [];
					for ( var i = 0; i < this.store.saveQueue[action].length; i++) {
						savejsonModel[action].push(this.store.saveQueue[action][i].data);
					}
				}
			}
			savejson['ManageTables'] = savejsonModel;

			Ext.Ajax.request( {
				url : this.store.url,
				success : function(response, opts) {
					var obj = Ext.decode(response.responseText);

					if (obj.success === false) {
						alert("Failed to save data: " + obj.errors[0]);
						return;
					}
					for ( var action in this.saveQueue) {
						if (Ext.isDefined(action)) {
							this['on' + Ext.util.Format.capitalize(action) + 'Records'](obj.success,
									this.saveQueue[action], obj.ManageTables[action]);
						}
					}
				},
				failure : function(response, opts) {
					alert("Failed to save data with status code " + response.status);
				},
				params : {
					xaction : 'save'
				},
				scope : this.store,
				jsonData : Ext.util.JSON.encode(savejson)
			});
		},
		insertRecord : function() {
			var r = new this.store.recordType();
			this.store.add(r);
			this.getSelectionModel().select(this.store.getCount() - 1, 0);
		},
		deleteRecord : function() {
			var selectedCell = this.getSelectionModel().getSelectedCell();
			if (selectedCell == null) {
				alert("No rows were selected");
				return;
			}
			var nextRow = selectedCell[0];
			var selectedItem = this.store.getAt(nextRow);
			this.store.remove(selectedItem);

			if (nextRow >= this.store.getCount())
				nextRow = nextRow - 1;
			if (nextRow < 0)
				return;
			this.getSelectionModel().select(nextRow, 0);
		}

	});

	return page;
})();
