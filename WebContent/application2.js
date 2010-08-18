Ext.ns('Tantalum');
Ext.BLANK_IMAGE_URL = 'ext/resources/images/default/s.gif';


Ext.onReady(function() {
	Ext.QuickTips.init();

	var storeChild = new Ext.data.JsonStore({
	    root: ['Column'],
	    idProperty: 'DefineTableColumnID',
	    fields: [
		        {name: 'DefineTableColumnName'},
		        {name: 'DefineTableColumnDbName'},
		        {name: 'DefineTableColumnTableID'},
		        {name: 'DefineTableColumnID'},
		        {name: 'DefineTableColumnDisplayOrder', type: 'int'}
		    ],
		    sortInfo: {field:'DefineTableColumnDisplayOrder', direction:'ASC'}
	});

	var store = new Ext.data.JsonStore({
	    autoDestroy: true,
	    url: '/Tantalum/ws/DefineTable',
	    storeId: 'DefineTable',
	    root: ['DefineTable'],
	    idProperty: 'DefineTableTableID',
	    fields: [
		        {name: 'DefineTableDatabaseName'},
		        {name: 'DefineTableName'},
		        {name: 'DefineTableTableID'}
		    ],
		    sortInfo: {field:'DefineTableName', direction:'ASC'}
	});


	var fm = Ext.form;

	var cm = new Ext.grid.ColumnModel({
		columns: [
			{
					header: "Table Name", 
					dataIndex: 'DefineTableName',
					editor: new fm.TextField({
		               allowBlank: false,
		               blankText: "TableName"
					})
				},
			{
					header: "Database", 
					dataIndex: 'DefineTableDatabaseName',
					editor: new fm.TextField({
		               allowBlank: false
					})
				}
			],
			defaults: {sortable: true}
		});
	cm.defaultSortable = true;

	var grid = new Ext.grid.EditorGridPanel( {
		id: 'grid',
		store: store,
		cm: cm,
		title: 'Manage Tables',
	    frame: true,
	    clicksToEdit: 2,
	    region: 'center',
	    layout: 'fit',
	    sm: new Ext.grid.RowSelectionModel({
	    	singleSelect: true,
	    	listeners: {
	        	rowselect: {
		        	fn: function(sm,index,record) {
			        	if(record && (this.boundRecord !== record)) {
			                Ext.BindMgr.unbind(this.boundRecord);
			                Ext.BindMgr.bind(record, ['form']);
			                this.boundRecord = record;
	                        storeChild.filter('DefineTableColumnTableID', record.data.DefineTableTableID);
			            } else if(!record) {
	                        Ext.BindMgr.unbind(this.boundRecord);
	                        this.boundRecord = null;
	                    }
		        	}
	        	}
	    	}
	    }),
	    loadMask: {
			msg:"Loading DefineTable...",
			store: store
		},
	    bbar: new Ext.PagingToolbar({
	    	pageSize: 3,
	    	store: store
	    	}),
	   	listeners: {
	   		afteredit: function(e){
	   			e.record.commit();
	    	}
	 	},
	    tbar: [{
	        text:'Add Record'
	       ,iconCls:'icon-plus'
	       ,scope:this
	       ,handler:function() {
	           var s = grid.getStore();
	           var r = new (s.recordType)({});
	           s.add(r);
	           grid.startEditing(s.indexOf(r), 0);
	       }
	   },{
	       text:'Unselect'
	           //,scope:this
	       ,iconCls:'icon-undo'
	       ,handler:function() {
	           var sm = grid.getSelectionModel().clearSelections();
	       }
	   }]
	});

	var cmChild = new Ext.grid.ColumnModel({
		columns: [
			{
					header: "Order", 
					dataIndex: 'DefineTableColumnDisplayOrder'
				},
			{
					header: "Column Name", 
					dataIndex: 'DefineTableColumnName'
				}
			],
			defaults: {sortable: true}
		});

	var gridChild = new Ext.grid.EditorGridPanel( {
	    title: 'Fields',
	    height: 200,
	    split: true,
	    layout: 'fit',
		cm: cmChild,
		store: storeChild,
	    region: 'south'
	});

	var form = new Ext.form.FormPanel({
		id: 'form',
	    title: 'Define Table',
	    region: 'west',
	    defaultType: 'textfield',
	    items: [{
	    	name:'DefineTableName',
			fieldLabel:'Table name',
			grow: true,
			growMin: 100,
			emptyText: "TableName"
	    },{
	    	name:'DefineTableDatabaseName',
			fieldLabel:'Database implementation'
	    }],
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
	        this.form.loadRecord(record ? record : {data:{firstName:'', lastName:''}});
	    },
	    width: 400,
	    collapsible: true
	});

	var myBorderPanel = new Ext.Viewport({
	    renderTo: document.body,
	    height: 500,
	    layout: 'border',
	    border:false,
	    items: [form, gridChild, grid]
	});

	store.on('load', function(store) {
		   var data = store.reader.jsonData;
		   storeChild.loadData(data);
		}
	);
	store.load({});
});

