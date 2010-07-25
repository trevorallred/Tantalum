<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Define Table</title>
<link href="/TenFoldA/themes/default/style.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="/TenFoldA/js/main.js"></script>
<script type="text/javascript">

var json;

$(document).ready(function() {
	jQuery.getJSON('/TenFoldA/ws/DefineTable/',
		function(jsonCallback) {
			json = jsonCallback;
						rows_DefineTable = json.DefineTable;
			position_DefineTable();
					}
	);
});

	var rows_DefineTable = null;
	function nav_DefineTable(amount) {
		if (rows_DefineTable == null)
			return false;

		var r = rows_DefineTable;
		var maxPosition = r.DATA.length - 1;
		
		r.POSITION += amount;
		if (r.POSITION < 0)
			r.POSITION = 0;
		if (r.POSITION > maxPosition)
			r.POSITION = maxPosition;

		position_DefineTable(r);
		return false;
	}

	function position_DefineTable() {
		// first paint all child regions (not recursively), and then position all child views (recursively)
									paint_Tables();
							
		if (rows_DefineTable == undefined)
			return;
	
		var row = rows_DefineTable.DATA[rows_DefineTable.POSITION];
		
					rows_DefineTableColumn = row.CHILDREN.DefineTableColumn;
			position_DefineTableColumn();
					rows_DefineTableJoinParent = row.CHILDREN.DefineTableJoinParent;
			position_DefineTableJoinParent();
			}
	var rows_DefineTableColumn = null;
	function nav_DefineTableColumn(amount) {
		if (rows_DefineTableColumn == null)
			return false;

		var r = rows_DefineTableColumn;
		var maxPosition = r.DATA.length - 1;
		
		r.POSITION += amount;
		if (r.POSITION < 0)
			r.POSITION = 0;
		if (r.POSITION > maxPosition)
			r.POSITION = maxPosition;

		position_DefineTableColumn(r);
		return false;
	}

	function position_DefineTableColumn() {
		// first paint all child regions (not recursively), and then position all child views (recursively)
									paint_Columns();
							
		if (rows_DefineTableColumn == undefined)
			return;
	
		var row = rows_DefineTableColumn.DATA[rows_DefineTableColumn.POSITION];
		
			}
	var rows_DefineTableJoinParent = null;
	function nav_DefineTableJoinParent(amount) {
		if (rows_DefineTableJoinParent == null)
			return false;

		var r = rows_DefineTableJoinParent;
		var maxPosition = r.DATA.length - 1;
		
		r.POSITION += amount;
		if (r.POSITION < 0)
			r.POSITION = 0;
		if (r.POSITION > maxPosition)
			r.POSITION = maxPosition;

		position_DefineTableJoinParent(r);
		return false;
	}

	function position_DefineTableJoinParent() {
		// first paint all child regions (not recursively), and then position all child views (recursively)
									paint_Join();
							
		if (rows_DefineTableJoinParent == undefined)
			return;
	
		var row = rows_DefineTableJoinParent.DATA[rows_DefineTableJoinParent.POSITION];
		
			}


	function paintButtons_Tables() {
		var rows = rows_DefineTable;
		if ($(rows).length > 0) {
			$('#previousDefineTable').attr("disabled", (rows.POSITION == 0));
			$('#nextDefineTable').attr("disabled", (rows.POSITION == rows.DATA.length - 1));
		} else {
			$('#previousDefineTable').attr("disabled", true);
			$('#nextDefineTable').attr("disabled", true);
		}
	}

	function paint_Tables() {
		paintButtons_Tables();
		
		var row = rows_DefineTable.DATA[rows_DefineTable.POSITION];
		
									$("#Tables").find('.DefineTableTableID').html(row.FIELDS.DefineTableTableID);
												$("#Tables").find('.DefineTableName').val(row.FIELDS.DefineTableName);
												$("#Tables").find('.DefineTableDatabaseName').val(row.FIELDS.DefineTableDatabaseName);
						}


	function paint_Columns() {
		var tbody = $("#Columns").find("tbody");
		
		$(tbody).find("tr.data").remove();
		var template = $(tbody).find('tr.template');
		var rows = rows_DefineTableColumn;
		if (rows != null) {
			for ( var i = 0; i < rows.DATA.length; i++) {
				addRow_Columns(tbody, template, rows.DATA[i].FIELDS);
			}
		}
		$(template).hide();
	}
	function addRow_Columns(tbody, template, row) {
		var rowHTML = $(template).clone();
		rowHTML.show();
		rowHTML.addClass("data");
		rowHTML.removeClass("template");
					$(rowHTML[1]).find('.DefineTableColumnID').val(row.DefineTableColumnID);
			$(rowHTML).find('.DefineTableColumnID').html(row.DefineTableColumnID);
					$(rowHTML[1]).find('.DefineTableColumnTableID').val(row.DefineTableColumnTableID);
			$(rowHTML).find('.DefineTableColumnTableID').html(row.DefineTableColumnTableID);
					$(rowHTML[1]).find('.DefineTableColumnDisplayOrder').val(row.DefineTableColumnDisplayOrder);
			$(rowHTML).find('.DefineTableColumnDisplayOrder').html(row.DefineTableColumnDisplayOrder);
					$(rowHTML[1]).find('.DefineTableColumnName').val(row.DefineTableColumnName);
			$(rowHTML).find('.DefineTableColumnName').html(row.DefineTableColumnName);
					$(rowHTML[1]).find('.DefineTableColumnRequired').val(row.DefineTableColumnRequired);
			$(rowHTML).find('.DefineTableColumnRequired').html(row.DefineTableColumnRequired);
					$(rowHTML[1]).find('.DefineTableColumnDbName').val(row.DefineTableColumnDbName);
			$(rowHTML).find('.DefineTableColumnDbName').html(row.DefineTableColumnDbName);
				tbody.append(rowHTML);
	}



	function paint_Join() {
		var tbody = $("#Join").find("tbody");
		
		$(tbody).find("tr.data").remove();
		var template = $(tbody).find('tr.template');
		var rows = rows_DefineTableJoinParent;
		if (rows != null) {
			for ( var i = 0; i < rows.DATA.length; i++) {
				addRow_Join(tbody, template, rows.DATA[i].FIELDS);
			}
		}
		$(template).hide();
	}
	function addRow_Join(tbody, template, row) {
		var rowHTML = $(template).clone();
		rowHTML.show();
		rowHTML.addClass("data");
		rowHTML.removeClass("template");
					$(rowHTML[1]).find('.DefineTableJoinFromTableID').val(row.DefineTableJoinFromTableID);
			$(rowHTML).find('.DefineTableJoinFromTableID').html(row.DefineTableJoinFromTableID);
					$(rowHTML[1]).find('.DefineTableJoinJoinID').val(row.DefineTableJoinJoinID);
			$(rowHTML).find('.DefineTableJoinJoinID').html(row.DefineTableJoinJoinID);
					$(rowHTML[1]).find('.DefineTableJoinToTableID').val(row.DefineTableJoinToTableID);
			$(rowHTML).find('.DefineTableJoinToTableID').html(row.DefineTableJoinToTableID);
					$(rowHTML[1]).find('.DefineTableJoinToTableName').val(row.DefineTableJoinToTableName);
			$(rowHTML).find('.DefineTableJoinToTableName').html(row.DefineTableJoinToTableName);
				tbody.append(rowHTML);
	}







</script>
</head>
<body>
[<a href='/TenFoldA/t/ManageTables'>Manage Tables</a>]
[<a href='/TenFoldA/t/WebpageList'>List Web Pages</a>]


<h1>Define Table</h1>

<div id="ManageTables" class="region">
			<div id="Tables" class="region">
<h2>Table</h2>
		<div id="TablesNavigation">
		<button id="previousDefineTable" onclick="return nav_DefineTable(-1);">Previous</button>
		<button id="nextDefineTable" onclick="return nav_DefineTable(1);">Next</button>
	</div>

		
	<ul>
			<li><label>ID:</label>
					<span class="DefineTableTableID"></span>
				</li>
			<li><label>Table name:</label>
					<input type="text" class="DefineTableName" name="DefineTableName">
				</li>
			<li><label>DB name:</label>

					<input type="text" class="DefineTableDatabaseName" name="DefineTableDatabaseName">
				</li>
		</ul>
</div>
			<div id="JoinColumnContainer" class="region">
	<table>
	<tr>
			<td><div id="Columns" class="region">
<h2>Columns</h2>

	<table class="basicTable">
	<thead>
	<tr>
			<th><a href='#'>ID</a></th>
			<th><a href='#'>Order</a></th>
			<th><a href='#'>Name</a></th>
			<th><a href='#'>Required</a></th>

			<th><a href='#'>Database</a></th>
			<th>Actions</th>
	</tr>
	</thead>
	<tbody>
		<tr class='template view'>
					<td class="DefineTableColumnID">DefineTableColumnID</td>

					<td class="DefineTableColumnDisplayOrder">DefineTableColumnDisplayOrder</td>
					<td class="DefineTableColumnName">DefineTableColumnName</td>
					<td class="DefineTableColumnRequired">DefineTableColumnRequired</td>
					<td class="DefineTableColumnDbName">DefineTableColumnDbName</td>
					<td>
				<a href="#" title="Edit">E</a>

				<a href="#" title="Delete">D</a>
			</td>
		</tr>
		<tr class='template edit'>
									<td><span class="DefineTableColumnID"></span></td>
												<td><input type="text" class="DefineTableColumnDisplayOrder" name="DefineTableColumnDisplayOrder"></td>
												<td><input type="text" class="DefineTableColumnName" name="DefineTableColumnName"></td>
												<td><input type="text" class="DefineTableColumnRequired" name="DefineTableColumnRequired"></td>

												<td><input type="text" class="DefineTableColumnDbName" name="DefineTableColumnDbName"></td>
								<td>
				<a href="#" title="Save">S</a>
				<a href="#" title="Cancel">X</a>
			</td>
		</tr>
		<tr class='add view'>
			<td colspan="{5+1}">Add</td>

		</tr>
	</tbody>
	</table>
</div>
</td>
			<td><div id="Join" class="region">
<h2>Joins to children</h2>
	<table class="basicTable">
	<thead>
	<tr>

			<th><a href='#'>From table</a></th>
			<th><a href='#'>To table</a></th>
			<th><a href='#'>To table</a></th>
			<th>Actions</th>
	</tr>
	</thead>
	<tbody>

		<tr class='template view'>
					<td class="DefineTableJoinFromTableID">DefineTableJoinFromTableID</td>
					<td class="DefineTableJoinToTableID">DefineTableJoinToTableID</td>
					<td class="DefineTableJoinToTableName">DefineTableJoinToTableName</td>
					<td>
				<a href="#" title="Edit">E</a>
				<a href="#" title="Delete">D</a>

			</td>
		</tr>
		<tr class='template edit'>
									<td><input type="text" class="DefineTableJoinFromTableID" name="DefineTableJoinFromTableID"></td>
												<td><input type="text" class="DefineTableJoinToTableID" name="DefineTableJoinToTableID"></td>
												<td><input type="text" class="DefineTableJoinToTableName" name="DefineTableJoinToTableName"></td>
								<td>
				<a href="#" title="Save">S</a>

				<a href="#" title="Cancel">X</a>
			</td>
		</tr>
		<tr class='add view'>
			<td colspan="{3+1}">Add</td>
		</tr>
	</tbody>
	</table>

</div>
</td>
		</tr>
	</table>
</div>
	</div>

</body>
</html>
