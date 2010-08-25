Ext.ns('Tantalum');
Ext.BLANK_IMAGE_URL = 'ext/resources/images/default/s.gif';

BodyPanel = Ext.extend(Ext.Viewport, {
	layout : 'border',

	initComponent : function() {
		this.items = [ {
			xtype : 'tantalum-menu'
		}, {
			xtype : 'tabpanel',
			id : 'contentPanel',
			activeTab : 0,
			region : 'center',
			defaults : {
				closable : true
			},
			items : [ {
				xtype : 'panel',
				title : 'Welcome',
				html : '<div class="welcome">Welcome to Tantalum</div>'
			} ]
		} ];
		BodyPanel.superclass.initComponent.call(this);

		this.menu = this.items.itemAt(0);
		this.content = this.items.itemAt(1);
	}
});

Ext.onReady(function() {
	Ext.QuickTips.init();

	var bodyPanel = new BodyPanel();

	var map = new Ext.KeyMap(document, [ {
		shift : true,
		ctrl : true,
		key : "i",
		fn : function(keycode, e) {
			insertRecord();
		}
	}, {
		shift : true,
		ctrl : true,
		key : "d",
		fn : function(keycode, e) {
			deleteRecord();
		}
	}, {
		shift : true,
		ctrl : true,
		key : "s",
		fn : function(keycode, e) {
			savePage();
		}
	}, {
		shift : true,
		ctrl : true,
		key : "r",
		fn : function(keycode, e) {
			refresh();
		}
	}, {
		ctrl : true,
		key : "\t",
		fn : function(keycode, e) {
			var contentPanel = Ext.ComponentMgr.get('contentPanel');
			if (contentPanel.items.length < 2)
				return;
			var tab = contentPanel.getActiveTab();
			tab = tab.nextSibling();
			if (tab === null)
				contentPanel.setActiveTab(0);
			else
				contentPanel.setActiveTab(tab);
		}
	} ]);
	
});
