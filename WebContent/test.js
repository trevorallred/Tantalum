Ext.BLANK_IMAGE_URL = 'ext/resources/images/default/s.gif';

Ext.ns('Tantalum');

Ext.onReady(function() {
	Ext.QuickTips.init();

	var myBorderPanel = new Ext.Viewport( {
		renderTo : document.body,
		layout : 'border',
		border : false,
		items : [ {
			region : 'south',
			height : 200,
			html : 'East',
			title : 'East',
			collapsible : true
		}, {
			region : 'west',
			width : 200,
			html : 'East',
			title : 'East',
			collapsible : true
		}, {
			region : 'center',
			html : 'Center',
			title : 'Center'
		} ]
	});
});
