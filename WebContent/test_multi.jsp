<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Define Table</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="/TenFoldA/js/main.js"></script>
<script type="text/javascript">

var json;
var DefineTableRegion;
var DefineTableColumnRegion;
var DefineTableJoinParentRegion;

$(document).ready(function() {
	jQuery.getJSON('/TenFoldA/ws/DefineTable/',
		function(jsonCallback) {
			json = jsonCallback;
						paint_DefineTable(json.DefineTable);
					}
	);
	
		DefineTableRegion = $("#DefineTable");
		DefineTableColumnRegion = $("#DefineTableColumn");
		DefineTableJoinParentRegion = $("#DefineTableJoinParent");
	});

	function nav_DefineTable(amount) {
					var rows = json.DefineTable;
				var maxPosition = rows.DATA.length - 1;
		
		rows.POSITION += amount;
		if (rows.POSITION < 0)
			rows.POSITION = 0;
		if (rows.POSITION > maxPosition)
			rows.POSITION = maxPosition;
		paint_DefineTable(rows);
		return false;
	}

	function paintButtons_DefineTable(rows) {
		if ($(rows).length > 0) {
			$('#previousDefineTable').attr("disabled", (rows.POSITION == 0));
			$('#nextDefineTable').attr("disabled", (rows.POSITION == rows.DATA.length - 1));
		} else {
			$('#previousDefineTable').attr("disabled", true);
			$('#nextDefineTable').attr("disabled", true);
		}
	}
	function paint_DefineTable(rows) {
		paintButtons_DefineTable(rows);
		
		var row = rows.DATA[json.DefineTable.POSITION];
		
									$(DefineTableRegion).find('.DefineTableTableID').html(row.FIELDS.DefineTableTableID);
												$(DefineTableRegion).find('.DefineTableName').val(row.FIELDS.DefineTableName);
												$(DefineTableRegion).find('.DefineTableDatabaseName').val(row.FIELDS.DefineTableDatabaseName);
							
					var childRows = null;
						if ($(row.CHILDREN).length > 0) {
				childRows = row.CHILDREN.DefineTableColumn;
			}
			paint_DefineTableColumn(childRows);
			childRows = null;
						if ($(row.CHILDREN).length > 0) {
				childRows = row.CHILDREN.DefineTableJoinParent;
			}
			paint_DefineTableJoinParent(childRows);
			childRows = null;
						}
	function nav_DefineTableColumn(amount) {
					var rows = json.DefineTable.DATA[json.DefineTable.POSITION].CHILDREN.DefineTableColumn;
				var maxPosition = rows.DATA.length - 1;
		
		rows.POSITION += amount;
		if (rows.POSITION < 0)
			rows.POSITION = 0;
		if (rows.POSITION > maxPosition)
			rows.POSITION = maxPosition;
		paint_DefineTableColumn(rows);
		return false;
	}

	function paintButtons_DefineTableColumn(rows) {
		if ($(rows).length > 0) {
			$('#previousDefineTableColumn').attr("disabled", (rows.POSITION == 0));
			$('#nextDefineTableColumn').attr("disabled", (rows.POSITION == rows.DATA.length - 1));
		} else {
			$('#previousDefineTableColumn').attr("disabled", true);
			$('#nextDefineTableColumn').attr("disabled", true);
		}
	}
	function paint_DefineTableColumn(rows) {
		paintButtons_DefineTableColumn(rows);
		
		var tbody = DefineTableColumnRegion.find("tbody");
		
		$(tbody).find("tr.data").remove();
		var template = $(tbody).find('tr.template');
		if ($(rows).length > 0) {
			for ( var i = 0; i < rows.DATA.length; i++) {
				addRow_DefineTableColumn(tbody, template, rows.DATA[i].FIELDS);
			}
		}
		$(template).hide();
		
				
	}
	function addRow_DefineTableColumn(tbody, template, row) {
		var rowHTML = $(template).clone();
		rowHTML.show();
		rowHTML.addClass("data");
		rowHTML.removeClass("template");
					rowHTML.find('.DefineTableColumnID').html(row.DefineTableColumnID);
					rowHTML.find('.DefineTableColumnTableID').html(row.DefineTableColumnTableID);
					rowHTML.find('.DefineTableColumnDisplayOrder').html(row.DefineTableColumnDisplayOrder);
					rowHTML.find('.DefineTableColumnName').html(row.DefineTableColumnName);
					rowHTML.find('.DefineTableColumnRequired').html(row.DefineTableColumnRequired);
					rowHTML.find('.DefineTableColumnDbName').html(row.DefineTableColumnDbName);
				tbody.append(rowHTML);
	}

	function nav_DefineTableJoinParent(amount) {
					var rows = json.DefineTable.DATA[json.DefineTable.POSITION].CHILDREN.DefineTableJoinParent;
				var maxPosition = rows.DATA.length - 1;
		
		rows.POSITION += amount;
		if (rows.POSITION < 0)
			rows.POSITION = 0;
		if (rows.POSITION > maxPosition)
			rows.POSITION = maxPosition;
		paint_DefineTableJoinParent(rows);
		return false;
	}

	function paintButtons_DefineTableJoinParent(rows) {
		if ($(rows).length > 0) {
			$('#previousDefineTableJoinParent').attr("disabled", (rows.POSITION == 0));
			$('#nextDefineTableJoinParent').attr("disabled", (rows.POSITION == rows.DATA.length - 1));
		} else {
			$('#previousDefineTableJoinParent').attr("disabled", true);
			$('#nextDefineTableJoinParent').attr("disabled", true);
		}
	}
	function paint_DefineTableJoinParent(rows) {
		paintButtons_DefineTableJoinParent(rows);
		
		var tbody = DefineTableJoinParentRegion.find("tbody");
		
		$(tbody).find("tr.data").remove();
		var template = $(tbody).find('tr.template');
		if ($(rows).length > 0) {
			for ( var i = 0; i < rows.DATA.length; i++) {
				addRow_DefineTableJoinParent(tbody, template, rows.DATA[i].FIELDS);
			}
		}
		$(template).hide();
		
				
	}
	function addRow_DefineTableJoinParent(tbody, template, row) {
		var rowHTML = $(template).clone();
		rowHTML.show();
		rowHTML.addClass("data");
		rowHTML.removeClass("template");
					rowHTML.find('.DefineTableJoinFromTableID').html(row.DefineTableJoinFromTableID);
					rowHTML.find('.DefineTableJoinJoinID').html(row.DefineTableJoinJoinID);
					rowHTML.find('.DefineTableJoinToTableID').html(row.DefineTableJoinToTableID);
					rowHTML.find('.DefineTableJoinToTableName').html(row.DefineTableJoinToTableName);
				tbody.append(rowHTML);
	}



</script>
</head>
<body>
[<a href='/TenFoldA/t/ManageTables'>Manage Tables</a>]
[<a href='/TenFoldA/t/WebpageList'>List Web Pages</a>]


<h1>Define Table</h1>

<div id="DefineTable">
	<h2>DefineTable</h2>
		<button id="previousDefineTable" onclick="return nav_DefineTable(-1);">Previous</button>
		<button id="nextDefineTable" onclick="return nav_DefineTable(1);">Next</button>
			<form>
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
		<button name='button' value='Save'>Save</button>
		</form>
	</div>
<div id="DefineTableColumn">
	<h2>DefineTableColumn</h2>

		<button id="previousDefineTableColumn" onclick="return nav_DefineTableColumn(-1);">Previous</button>
		<button id="nextDefineTableColumn" onclick="return nav_DefineTableColumn(1);">Next</button>
			<table>
		<thead>
		<tr>
									<th><a href='#'>ID</a></th>
																	<th><a href='#'>Order</a></th>

												<th><a href='#'>Name</a></th>
												<th><a href='#'>Required</a></th>
												<th><a href='#'>Database</a></th>
							</tr>
		</thead>
		<tbody>
			<tr class='template'>

												<td class="DefineTableColumnID fieldContent">DefineTableColumnID</td>
																							<td class="DefineTableColumnDisplayOrder fieldContent">DefineTableColumnDisplayOrder</td>
																<td class="DefineTableColumnName fieldContent">DefineTableColumnName</td>
																<td class="DefineTableColumnRequired fieldContent">DefineTableColumnRequired</td>
																<td class="DefineTableColumnDbName fieldContent">DefineTableColumnDbName</td>
										</tr>

		</tbody>
		</table>
	</div>
<div id="DefineTableJoinParent">
	<h2>DefineTableJoinParent</h2>
		<button id="previousDefineTableJoinParent" onclick="return nav_DefineTableJoinParent(-1);">Previous</button>
		<button id="nextDefineTableJoinParent" onclick="return nav_DefineTableJoinParent(1);">Next</button>
			<table>

		<thead>
		<tr>
									<th><a href='#'>From table</a></th>
												<th><a href='#'>Join ID</a></th>
												<th><a href='#'>To table</a></th>
												<th><a href='#'>To table</a></th>
							</tr>

		</thead>
		<tbody>
			<tr class='template'>
												<td class="DefineTableJoinFromTableID fieldContent">DefineTableJoinFromTableID</td>
																<td class="DefineTableJoinJoinID fieldContent">DefineTableJoinJoinID</td>
																<td class="DefineTableJoinToTableID fieldContent">DefineTableJoinToTableID</td>
																<td class="DefineTableJoinToTableName fieldContent">DefineTableJoinToTableName</td>

										</tr>
		</tbody>
		</table>
	</div>

</body>
</html>
