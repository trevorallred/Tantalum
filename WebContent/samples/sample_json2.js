//Tantalum/ws/ManageTables/
var jsonSimpleQuery = {
	"xaction" : "query",
	"DefineTable" : {
		"field" : "DefineTableTableID",
		"value" : "7e640a5b-288e-4c88-9d3b-4f370942f211"
	}
};

var jsonComplexQuery = {
	"xaction" : "query",
	"DefineTable" : {
		"OR" : [ {
			field : "DefineTableTableID",
			operator : "=",
			value : "7e640a5b-288e-4c88-9d3b-4f370942f211"
		}, {
			field : "DefineTableTableID",
			operator : "!=",
			value : "fce920bd-9f57-11df-936f-e37ecc873ea2"
		}, {
			field : "DefineTableTableID",
			operator : "IN",
			value : [ "fce920bd-9f57-11df-936f-e37ecc873ea2", "fce920bd-9f57-11df-936f-e37ecc873ea2" ]
		} ]
	}
};

var jsonQueryResponse = {
	"success" : true,
	"msg" : "Successful",
	"DefineTable" : {
		"total" : 4,
		"data" : [ {
			"DefineTableTableID" : "7e640a5b-288e-4c88-9d3b-4f370942f211",
			"DefineTableName" : "Button",
			"DefineTableDatabaseName" : "tan_button"
		}, {
			"DefineTableTableID" : "fce920bd-9f57-11df-936f-e37ecc873ea2",
			"DefineTableName" : "Table",
			"DefineTableDatabaseName" : "tan_table"
		} ]
	},
	"DefineTableColumns" : {
		"total" : 14,
		"data" : [ {
			"TableColumnColumnID" : "34b8e1dc-9f56-11df-936f-e37ecc873ea2",
			"TableColumnColumnName" : "TableID",
			"TableColumnTableID" : "7e640a5b-288e-4c88-9d3b-4f370942f211"
		}, {
			"TableColumnColumnID" : "34b8f27a-9f56-11df-936f-e37ecc873ea2",
			"TableColumnColumnName" : "ColumnID",
			"TableColumnTableID" : "fce920bd-9f57-11df-936f-e37ecc873ea2"
		} ]
	}
};

var jsonSave = {
	"xaction" : "save",
	"DefineTable" : {
		"create" : [ {
			"DefineTableTableID" : "TEMP-1",
			"DefineTableName" : "Button",
			"DefineTableDatabaseName" : "tan_button"
		}, {
			"DefineTableTableID" : "TEMP-2",
			"DefineTableName" : "Button2",
			"DefineTableDatabaseName" : "tan_button2"
		} ],
		"destroy" : [ {
			"DefineTableTableID" : "7e640a5b-288e-4c88-9d3b-4f370942f211"
		} ],
		"update" : [ {
			"DefineTableTableID" : "fce920bd-9f57-11df-936f-e37ecc873ea2",
			"DefineTableName" : "Tables"
		} ]
	},
	"DefineTableColumns" : {
		"create" : [ {
			"TableColumnColumnID" : "TEMP-3",
			"TableColumnColumnName" : "TableID",
			"TableColumnTableID" : "TEMP-1"
		}, {
			"TableColumnColumnID" : "TEMP-4",
			"TableColumnColumnName" : "ColumnID",
			"TableColumnTableID" : "TEMP-1"
		} ],
		"destroy" : [ {
			"TableColumnColumnID" : "7e640a5b-288e-4c88-9d3b-4f370942f211"
		} ],
		"update" : [ {
			"TableColumnColumnID" : "fce920bd-9f57-11df-936f-e37ecc873ea2",
			"TableColumnColumnName" : "TableId2"
		} ]
	}
};

var jsonSaveResponse = {
	"DefineTable" : {
		"create" : [ {
			"DefineTableTableID" : "7e640a5b-288e-4c88-9d3b-4f370942f211",
			"DefineTableName" : "Button",
			"DefineTableDatabaseName" : "tan_button"
		}, {
			"DefineTableTableID" : "7e640a5b-288e-4c88-9d3b-4f370942f211",
			"DefineTableName" : "Button2",
			"DefineTableDatabaseName" : "tan_button2"
		} ],
		"update" : [ {
			"DefineTableTableID" : "fce920bd-9f57-11df-936f-e37ecc873ea2",
			"DefineTableName" : "Tables"
		} ]
	},
	"DefineTableColumns" : {
		"create" : [ {
			"TableColumnColumnID" : "4e610a5b-288e-4c88-9d3b-4f370942f211",
			"TableColumnColumnName" : "TableID",
			"TableColumnTableID" : "7e640a5b-288e-4c88-9d3b-4f370942f211"
		}, {
			"TableColumnColumnID" : "3e640a5b-288e-4c88-9d3b-4f370942f211",
			"TableColumnColumnName" : "ColumnID",
			"TableColumnTableID" : "7e640a5b-288e-4c88-9d3b-4f370942f211"
		} ],
		"update" : [ {
			"TableColumnColumnID" : "fce920bd-9f57-11df-936f-e37ecc873ea2",
			"TableColumnColumnName" : "TableId2"
		} ]
	}
};
