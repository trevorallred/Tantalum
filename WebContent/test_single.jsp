<html>
<head>
<title>Define Table</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="/TenFoldA/js/main.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<h1>Define Table</h1>
<div id="DefineTable">
<h2>DefineTable</h2>
<button id="previous" onclick="return nav(json.DefineTable, -1);">Previous</button>
<button id="next" onclick="return nav(json.DefineTable, 1);">Next</button>
<form>
<ul>
	<li><label>ID:</label><span class="DefineTableTableID"></span></li>
	<li><label>Table name:</label><input type="text"
		class="DefineTableName" name="DefineTableName"></li>
	<li><label>DB name:</label><input type="text"
		class="DefineTableDatabaseName" name="DefineTableDatabaseName"></li>
</ul>
<button name='button' value='Save'>Save</button>
</form>
</div>
<div id="DefineTableColumn">
<h2>DefineTableColumn</h2>
<table>
	<thead>
		<tr>
			<th><a href='/TenFoldA/table?orderby=DefineTableColumnID'>ID</a></th>
			<th><a
				href='/TenFoldA/table?orderby=DefineTableColumnDisplayOrder'>Order</a></th>
			<th><a href='/TenFoldA/table?orderby=DefineTableColumnName'>Name</a></th>
			<th><a href='/TenFoldA/table?orderby=DefineTableColumnRequired'>Required</a></th>
			<th><a href='/TenFoldA/table?orderby=DefineTableColumnDbName'>Database</a></th>
		</tr>
	</thead>
	<tbody>
		<tr class='template'>
			<td class="DefineTableColumnID fieldContent">.</td>
			<td class="DefineTableColumnDisplayOrder fieldContent">.</td>
			<td class="DefineTableColumnName fieldContent">.</td>
			<td class="DefineTableColumnRequired fieldContent">.</td>
			<td class="DefineTableColumnDbName fieldContent">.</td>
		</tr>
	</tbody>
</table>
</div>
<div id="DefineTableJoinParent">
<h2>DefineTableJoinParent</h2>
<table>
	<thead>
		<tr>
			<th><a href='/TenFoldA/table?orderby=DefineTableJoinFromTableID'>From
			table</a></th>
			<th><a href='/TenFoldA/table?orderby=DefineTableJoinJoinID'>Join
			ID</a></th>
			<th><a href='/TenFoldA/table?orderby=DefineTableJoinToTableID'>To
			table</a></th>
			<th><a href='/TenFoldA/table?orderby=DefineTableJoinToTableName'>To
			table</a></th>
		</tr>
	</thead>
	<tbody>
		<tr class='template'>
			<td class="DefineTableJoinFromTableID fieldContent"></td>
			<td class="DefineTableJoinJoinID fieldContent"></td>
			<td class="DefineTableJoinToTableID fieldContent"></td>
			<td class="DefineTableJoinToTableName fieldContent"></td>
		</tr>
	</tbody>
</table>
</div>

<script type="text/javascript">
	var json = {"DefineTable":[{"DefineTableTableID":"1","DefineTableName":"Table","DefineTableDatabaseName":"dd_table","_CHILDREN_":{"DefineTableColumn":[{"DefineTableColumnID":"1","DefineTableColumnTableID":"1","DefineTableColumnDisplayOrder":"10","DefineTableColumnDbName":"id","DefineTableColumnRequired":"1","DefineTableColumnName":"TableID"},{"DefineTableColumnID":"2","DefineTableColumnTableID":"1","DefineTableColumnDisplayOrder":"20","DefineTableColumnDbName":"name","DefineTableColumnRequired":"1","DefineTableColumnName":"Name"},{"DefineTableColumnID":"3","DefineTableColumnTableID":"1","DefineTableColumnDisplayOrder":"30","DefineTableColumnDbName":"dbName","DefineTableColumnRequired":"1","DefineTableColumnName":"DatabaseName"}]}},{"DefineTableTableID":"2","DefineTableName":"Column","DefineTableDatabaseName":"dd_column","_CHILDREN_":{"DefineTableJoinParent":[{"DefineTableJoinToTableName":"Table","DefineTableJoinToTableID":"1","DefineTableJoinFromTableID":"2","DefineTableJoinJoinID":"1"}],"DefineTableColumn":[{"DefineTableColumnID":"7","DefineTableColumnTableID":"2","DefineTableColumnDisplayOrder":"10","DefineTableColumnDbName":"name","DefineTableColumnRequired":"1","DefineTableColumnName":"Name"},{"DefineTableColumnID":"8","DefineTableColumnTableID":"2","DefineTableColumnDisplayOrder":"5","DefineTableColumnDbName":"id","DefineTableColumnRequired":"1","DefineTableColumnName":"ColumnID"},{"DefineTableColumnID":"9","DefineTableColumnTableID":"2","DefineTableColumnDisplayOrder":"20","DefineTableColumnDbName":"tableID","DefineTableColumnRequired":"1","DefineTableColumnName":"TableID"},{"DefineTableColumnID":"10","DefineTableColumnTableID":"2","DefineTableColumnDisplayOrder":"30","DefineTableColumnDbName":"required","DefineTableColumnRequired":"1","DefineTableColumnName":"Required"},{"DefineTableColumnID":"11","DefineTableColumnTableID":"2","DefineTableColumnDisplayOrder":"40","DefineTableColumnDbName":"displayOrder","DefineTableColumnRequired":"1","DefineTableColumnName":"DisplayOrder"},{"DefineTableColumnID":"18","DefineTableColumnTableID":"2","DefineTableColumnDisplayOrder":"50","DefineTableColumnDbName":"dbName","DefineTableColumnRequired":"0","DefineTableColumnName":"Database"}]}},{"DefineTableTableID":"3","DefineTableName":"Join","DefineTableDatabaseName":"dd_join","_CHILDREN_":{"DefineTableJoinParent":[{"DefineTableJoinToTableName":"Table","DefineTableJoinToTableID":"1","DefineTableJoinFromTableID":"3","DefineTableJoinJoinID":"2"},{"DefineTableJoinToTableName":"Table","DefineTableJoinToTableID":"1","DefineTableJoinFromTableID":"3","DefineTableJoinJoinID":"5"}],"DefineTableColumn":[{"DefineTableColumnID":"14","DefineTableColumnTableID":"3","DefineTableColumnDisplayOrder":"10","DefineTableColumnDbName":"id","DefineTableColumnRequired":"1","DefineTableColumnName":"JoinID"},{"DefineTableColumnID":"15","DefineTableColumnTableID":"3","DefineTableColumnDisplayOrder":"20","DefineTableColumnDbName":"fromTableID","DefineTableColumnRequired":"1","DefineTableColumnName":"FromTableID"},{"DefineTableColumnID":"16","DefineTableColumnTableID":"3","DefineTableColumnDisplayOrder":"30","DefineTableColumnDbName":"toTableID","DefineTableColumnRequired":"1","DefineTableColumnName":"ToTableID"}]}},{"DefineTableTableID":"4","DefineTableName":"WebPages","DefineTableDatabaseName":"dd_page","_CHILDREN_":{"DefineTableColumn":[{"DefineTableColumnID":"4","DefineTableColumnTableID":"4","DefineTableColumnDisplayOrder":"10","DefineTableColumnDbName":"id","DefineTableColumnRequired":"1","DefineTableColumnName":"PageID"},{"DefineTableColumnID":"5","DefineTableColumnTableID":"4","DefineTableColumnDisplayOrder":"20","DefineTableColumnDbName":"name","DefineTableColumnRequired":"1","DefineTableColumnName":"Name"},{"DefineTableColumnID":"6","DefineTableColumnTableID":"4","DefineTableColumnDisplayOrder":"30","DefineTableColumnDbName":"url","DefineTableColumnRequired":"1","DefineTableColumnName":"URL"}]}},{"DefineTableTableID":"5","DefineTableName":"Field","DefineTableDatabaseName":"dd_field","_CHILDREN_":{"DefineTableJoinParent":[{"DefineTableJoinToTableName":"View","DefineTableJoinToTableID":"6","DefineTableJoinFromTableID":"5","DefineTableJoinJoinID":"4"}]}},{"DefineTableTableID":"6","DefineTableName":"View","DefineTableDatabaseName":"dd_view","_CHILDREN_":{"DefineTableJoinParent":[{"DefineTableJoinToTableName":"WebPages","DefineTableJoinToTableID":"4","DefineTableJoinFromTableID":"6","DefineTableJoinJoinID":"3"}],"DefineTableColumn":[{"DefineTableColumnID":"12","DefineTableColumnTableID":"6","DefineTableColumnDisplayOrder":"10","DefineTableColumnDbName":"id","DefineTableColumnRequired":"1","DefineTableColumnName":"ViewID"},{"DefineTableColumnID":"13","DefineTableColumnTableID":"6","DefineTableColumnDisplayOrder":"20","DefineTableColumnDbName":"pageID","DefineTableColumnRequired":"1","DefineTableColumnName":"PageID"},{"DefineTableColumnID":"17","DefineTableColumnTableID":"6","DefineTableColumnDisplayOrder":"30","DefineTableColumnDbName":"resultsPerPage","DefineTableColumnRequired":"0","DefineTableColumnName":"ResultsPerPage"},{"DefineTableColumnID":"19","DefineTableColumnTableID":"6","DefineTableColumnDisplayOrder":"60","DefineTableColumnDbName":"basisTableID","DefineTableColumnRequired":"0","DefineTableColumnName":"BasisTableID"},{"DefineTableColumnID":"20","DefineTableColumnTableID":"6","DefineTableColumnDisplayOrder":"70","DefineTableColumnDbName":"name","DefineTableColumnRequired":"1","DefineTableColumnName":"Name"},{"DefineTableColumnID":"21","DefineTableColumnTableID":"6","DefineTableColumnDisplayOrder":"80","DefineTableColumnDbName":"parentID","DefineTableColumnRequired":"0","DefineTableColumnName":"ParentID"},{"DefineTableColumnID":"22","DefineTableColumnTableID":"6","DefineTableColumnDisplayOrder":"90","DefineTableColumnDbName":"referenceID","DefineTableColumnRequired":"0","DefineTableColumnName":"ReferenceID"}]}},{"DefineTableTableID":"8","DefineTableName":"SmartCodes","DefineTableDatabaseName":"dd_smart_codes"}]};

	var DefineTableDiv = $("#DefineTable");
	var DefineTableColumnTable = $("#DefineTableColumn table tbody");

	json.DefineTable.currentRow = 0;
	paint();

	function nav(what, amount) {
		var maxPosition = what.length - 1;
		what.currentRow += amount;
		if (what.currentRow < 0)
			what.currentRow = 0;
		if (what.currentRow > maxPosition)
			json.DefineTable.currentRow = maxPosition;
		paint();
		return false;
	}
	
	function paint(what) {
		paint();
	}
	function paint() {
		$('#previous').attr("disabled", (json.DefineTable.currentRow == 0));
		$('#next').attr("disabled", (json.DefineTable.currentRow == json.DefineTable.length - 1));

		var row = json.DefineTable[json.DefineTable.currentRow];
		$tr = $(DefineTableDiv).clone();
		$(DefineTableDiv).find('.DefineTableTableID').html(row.DefineTableTableID);
		$(DefineTableDiv).find('.DefineTableName').val(row.DefineTableName);
		$(DefineTableDiv).find('.DefineTableDatabaseName').val(
				row.DefineTableDatabaseName);

		if ($(row._CHILDREN_).length > 0)
			paintTable(DefineTableColumnTable, row._CHILDREN_.DefineTableColumn);
		else
			paintTable(DefineTableColumnTable, null);
	}

	function paintTable(table, rows) {
		$(table).find("tr.data").remove();
		var template = $(table).find('tr.template');
		if ($(rows).length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				addRow(table, template, rows[i]);
			}
		}
		$(template).hide();
	}
	
	function addRow(table, template, row) {
		var rowHTML = $(template).clone();
		rowHTML.show();
		rowHTML.addClass("data");
		rowHTML.removeClass("template");
		rowHTML.find('.DefineTableColumnID').html(row.DefineTableColumnID);
		rowHTML.find('.DefineTableColumnDisplayOrder').html(row.DefineTableColumnDisplayOrder);
		rowHTML.find('.DefineTableColumnName').html(row.DefineTableColumnName);
		rowHTML.find('.DefineTableColumnDbName').html(row.DefineTableColumnDbName);
		table.append(rowHTML);
	};

</script>

</body>
</html>
