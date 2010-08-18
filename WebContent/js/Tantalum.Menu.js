Ext.ns('Tantalum');
Tantalum.Menu = Ext.extend(Ext.Toolbar, {
	region : 'north',
    initComponent: function() {
        this.items = [
            {
                xtype: 'button',
                text: 'Button 1',
                menu: {
                    xtype: 'menu',
                    items: [
                        {
                            xtype: 'menuitem',
                            text: 'Menu Item'
                        },
                        {
                            xtype: 'menuseparator'
                        },
                        {
                            xtype: 'menuitem',
                            text: 'Menu Item'
                        },
                        {
                            xtype: 'menuitem',
                            text: 'Menu Item'
                        },
                        {
                            xtype: 'menuitem',
                            text: 'Menu Item'
                        },
                        {
                            xtype: 'menucheckitem',
                            text: 'Menu Item'
                        },
                        {
                            xtype: 'menutextitem'
                        }
                    ]
                }
            },
            {
                xtype: 'button',
                text: 'MyButton',
                menu: {
                    xtype: 'menu',
                    items: [
                        {
                            xtype: 'menuitem',
                            text: 'Menu Item'
                        }
                    ]
                }
            }
        ];
        Tantalum.Menu.superclass.initComponent.call(this);
    }
});

Ext.reg('tantalum-menu', Tantalum.Menu);
