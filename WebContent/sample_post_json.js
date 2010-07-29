var json = {
	"DefineTable" : {
		"DATA" : [ {
			"FIELDS" : {
				"DefineTableTableID" : "1",
				"DefineTableName" : "Table",
				"DefineTableDatabaseName" : "dd_table"
			},
			"CHILDREN" : {
				"DefineTableColumn" : {
					"FULLY_LOADED" : true,
					"DATA" : [ {
						"FIELDS" : {
							"DefineTableColumnID" : "1",
							"DefineTableColumnTableID" : "1",
							"DefineTableColumnDisplayOrder" : "10",
							"DefineTableColumnDbName" : "id",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "TableID"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "2",
							"DefineTableColumnTableID" : "1",
							"DefineTableColumnDisplayOrder" : "20",
							"DefineTableColumnDbName" : "name",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "Name"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "3",
							"DefineTableColumnTableID" : "1",
							"DefineTableColumnDisplayOrder" : "30",
							"DefineTableColumnDbName" : "dbName",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "DatabaseName"
						},
						"CHILDREN" : {}
					} ],
					"POSITION" : 0
				}
			}
		}, {
			"FIELDS" : {
				"DefineTableTableID" : "2",
				"DefineTableName" : "Column",
				"DefineTableDatabaseName" : "dd_column"
			},
			"CHILDREN" : {
				"DefineTableJoinParent" : {
					"FULLY_LOADED" : true,
					"DATA" : [ {
						"FIELDS" : {
							"DefineTableJoinToTableName" : "Table",
							"DefineTableJoinToTableID" : "1",
							"DefineTableJoinFromTableID" : "2",
							"DefineTableJoinJoinID" : "1"
						},
						"CHILDREN" : {}
					} ],
					"POSITION" : 0
				},
				"DefineTableColumn" : {
					"FULLY_LOADED" : true,
					"DATA" : [ {
						"FIELDS" : {
							"DefineTableColumnID" : "7",
							"DefineTableColumnTableID" : "2",
							"DefineTableColumnDisplayOrder" : "10",
							"DefineTableColumnDbName" : "name",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "Name"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "8",
							"DefineTableColumnTableID" : "2",
							"DefineTableColumnDisplayOrder" : "5",
							"DefineTableColumnDbName" : "id",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "ColumnID"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "9",
							"DefineTableColumnTableID" : "2",
							"DefineTableColumnDisplayOrder" : "20",
							"DefineTableColumnDbName" : "tableID",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "TableID"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "10",
							"DefineTableColumnTableID" : "2",
							"DefineTableColumnDisplayOrder" : "30",
							"DefineTableColumnDbName" : "required",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "Required"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "11",
							"DefineTableColumnTableID" : "2",
							"DefineTableColumnDisplayOrder" : "40",
							"DefineTableColumnDbName" : "displayOrder",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "DisplayOrder"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "18",
							"DefineTableColumnTableID" : "2",
							"DefineTableColumnDisplayOrder" : "50",
							"DefineTableColumnDbName" : "dbName",
							"DefineTableColumnRequired" : "0",
							"DefineTableColumnName" : "Database"
						},
						"CHILDREN" : {}
					} ],
					"POSITION" : 0
				}
			}
		}, {
			"FIELDS" : {
				"DefineTableTableID" : "3",
				"DefineTableName" : "Join",
				"DefineTableDatabaseName" : "dd_join"
			},
			"CHILDREN" : {
				"DefineTableJoinParent" : {
					"FULLY_LOADED" : true,
					"DATA" : [ {
						"FIELDS" : {
							"DefineTableJoinToTableName" : "Table",
							"DefineTableJoinToTableID" : "1",
							"DefineTableJoinFromTableID" : "3",
							"DefineTableJoinJoinID" : "2"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableJoinToTableName" : "Table",
							"DefineTableJoinToTableID" : "1",
							"DefineTableJoinFromTableID" : "3",
							"DefineTableJoinJoinID" : "5"
						},
						"CHILDREN" : {}
					} ],
					"POSITION" : 0
				},
				"DefineTableColumn" : {
					"FULLY_LOADED" : true,
					"DATA" : [ {
						"FIELDS" : {
							"DefineTableColumnID" : "14",
							"DefineTableColumnTableID" : "3",
							"DefineTableColumnDisplayOrder" : "10",
							"DefineTableColumnDbName" : "id",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "JoinID"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "15",
							"DefineTableColumnTableID" : "3",
							"DefineTableColumnDisplayOrder" : "20",
							"DefineTableColumnDbName" : "fromTableID",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "FromTableID"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "16",
							"DefineTableColumnTableID" : "3",
							"DefineTableColumnDisplayOrder" : "30",
							"DefineTableColumnDbName" : "toTableID",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "ToTableID"
						},
						"CHILDREN" : {}
					} ],
					"POSITION" : 0
				}
			}
		}, {
			"FIELDS" : {
				"DefineTableTableID" : "4",
				"DefineTableName" : "WebPages",
				"DefineTableDatabaseName" : "dd_page"
			},
			"CHILDREN" : {
				"DefineTableColumn" : {
					"FULLY_LOADED" : true,
					"DATA" : [ {
						"FIELDS" : {
							"DefineTableColumnID" : "4",
							"DefineTableColumnTableID" : "4",
							"DefineTableColumnDisplayOrder" : "10",
							"DefineTableColumnDbName" : "id",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "PageID"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "5",
							"DefineTableColumnTableID" : "4",
							"DefineTableColumnDisplayOrder" : "20",
							"DefineTableColumnDbName" : "name",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "Name"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "6",
							"DefineTableColumnTableID" : "4",
							"DefineTableColumnDisplayOrder" : "30",
							"DefineTableColumnDbName" : "label",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "Title"
						},
						"CHILDREN" : {}
					} ],
					"POSITION" : 0
				}
			}
		}, {
			"FIELDS" : {
				"DefineTableTableID" : "5",
				"DefineTableName" : "Field",
				"DefineTableDatabaseName" : "dd_field"
			},
			"CHILDREN" : {
				"DefineTableJoinParent" : {
					"FULLY_LOADED" : true,
					"DATA" : [ {
						"FIELDS" : {
							"DefineTableJoinToTableName" : "View",
							"DefineTableJoinToTableID" : "6",
							"DefineTableJoinFromTableID" : "5",
							"DefineTableJoinJoinID" : "4"
						},
						"CHILDREN" : {}
					} ],
					"POSITION" : 0
				}
			}
		}, {
			"FIELDS" : {
				"DefineTableTableID" : "6",
				"DefineTableName" : "View",
				"DefineTableDatabaseName" : "dd_view"
			},
			"CHILDREN" : {
				"DefineTableJoinParent" : {
					"FULLY_LOADED" : true,
					"DATA" : [ {
						"FIELDS" : {
							"DefineTableJoinToTableName" : "WebPages",
							"DefineTableJoinToTableID" : "4",
							"DefineTableJoinFromTableID" : "6",
							"DefineTableJoinJoinID" : "3"
						},
						"CHILDREN" : {}
					} ],
					"POSITION" : 0
				},
				"DefineTableColumn" : {
					"FULLY_LOADED" : true,
					"DATA" : [ {
						"FIELDS" : {
							"DefineTableColumnID" : "12",
							"DefineTableColumnTableID" : "6",
							"DefineTableColumnDisplayOrder" : "10",
							"DefineTableColumnDbName" : "id",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "ViewID"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "13",
							"DefineTableColumnTableID" : "6",
							"DefineTableColumnDisplayOrder" : "20",
							"DefineTableColumnDbName" : "pageID",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "PageID"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "17",
							"DefineTableColumnTableID" : "6",
							"DefineTableColumnDisplayOrder" : "30",
							"DefineTableColumnDbName" : "resultsPerPage",
							"DefineTableColumnRequired" : "0",
							"DefineTableColumnName" : "ResultsPerPage"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "19",
							"DefineTableColumnTableID" : "6",
							"DefineTableColumnDisplayOrder" : "60",
							"DefineTableColumnDbName" : "basisTableID",
							"DefineTableColumnRequired" : "0",
							"DefineTableColumnName" : "BasisTableID"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "20",
							"DefineTableColumnTableID" : "6",
							"DefineTableColumnDisplayOrder" : "70",
							"DefineTableColumnDbName" : "name",
							"DefineTableColumnRequired" : "1",
							"DefineTableColumnName" : "Name"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "21",
							"DefineTableColumnTableID" : "6",
							"DefineTableColumnDisplayOrder" : "80",
							"DefineTableColumnDbName" : "parentID",
							"DefineTableColumnRequired" : "0",
							"DefineTableColumnName" : "ParentID"
						},
						"CHILDREN" : {}
					}, {
						"FIELDS" : {
							"DefineTableColumnID" : "22",
							"DefineTableColumnTableID" : "6",
							"DefineTableColumnDisplayOrder" : "90",
							"DefineTableColumnDbName" : "referenceID",
							"DefineTableColumnRequired" : "0",
							"DefineTableColumnName" : "ReferenceID"
						},
						"CHILDREN" : {}
					} ],
					"POSITION" : 0
				}
			}
		}, {
			"FIELDS" : {
				"DefineTableTableID" : "8",
				"DefineTableName" : "SmartCodes",
				"DefineTableDatabaseName" : "dd_smart_codes"
			},
			"CHILDREN" : {}
		} ],
		"POSITION" : 0
	}
};
