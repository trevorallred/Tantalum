Application.PersonnelGrid = Ext.extend(Ext.grid.GridPanel, {
});

Ext.onReady(function() {
	Ext.QuickTips.init();
	var store = new Ext.data.JsonStore({
	    autoDestroy: true,
	    url: '/Tantalum/ws/ManageTables',
	    storeId: 'ManageTables',
	    root: ['ManageTables.DATA'],
	    idProperty: 'FIELDS.ManageTablesTableID',
	    fields: [
 	        {name: 'ManageTablesDatabaseName', mapping: 'FIELDS.ManageTablesDatabaseName'},
 	        {name: 'ManageTablesName', mapping: 'FIELDS.ManageTablesName'},
 	        {name: 'ManageTablesTableID', mapping: 'FIELDS.ManageTablesTableID'}
 	    ],
 	    sortInfo: {field:'ManageTablesName', direction:'ASC'}
	});

	var storeChild = new Ext.data.SimpleStore({
        fields:[
             {name:'teamID'}
            ,{name:'firstName'}
            ,{name:'lastName'}
        ]
        ,data:[
             [1, 'Joe', 'Doe']
            ,[1, 'John', 'Black']
            ,[2, 'Sue', 'Brown']
            ,[1, 'Carin', 'White']
        ]
	});

    var fm = Ext.form;
    
    var cm = new Ext.grid.ColumnModel({
    	columns: [
			{
   				header: "Table Name", 
   				dataIndex: 'ManageTablesName',
   				editor: new fm.TextField({
   	               allowBlank: false,
   	               blankText: "TableName"
   				})
   			},
			{
   				header: "Database", 
   				dataIndex: 'ManageTablesDatabaseName',
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
			            } else if(!record) {
                            Ext.BindMgr.unbind(this.boundRecord);
                            this.boundRecord = null;
                        }
		        	}
	        	}
        	}
        }),
        loadMask: {
    		msg:"Loading ManageTables...",
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
   				header: "Field Name", 
   				dataIndex: 'firstName'
   			},
			{
   				header: "Database", 
   				dataIndex: 'lastName'
   			}
   		],
   		defaults: {sortable: true}
   	});

    var gridChild = new Ext.grid.EditorGridPanel( {
        title: 'Fields',
        height: 100,
        split: true,
        maxSize: 150,
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
        	name:'ManageTablesName',
			fieldLabel:'Table name',
			grow: true,
			growMin: 100,
			emptyText: "TableName"
        },{
        	name:'ManageTablesDatabaseName',
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
    
    store.load({});
    storeChild.filter('teamID', 1);
});
