<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Tables</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="/TenFoldA/js/main.js"></script>
</head>
<body>
<h1>Manage Tables</h1>
<div id="ManageTables">
<h2>ManageTables</h2>
<table>
	<thead>
		<tr>
			<th><a href='/TenFoldA/tables?orderby=ManageTablesTableID'>ID</a></th>
			<th><a href='/TenFoldA/tables?orderby=ManageTablesName'>Table
			name</a></th>
			<th><a href='/TenFoldA/tables?orderby=ManageTablesDatabaseName'>DB
			name</a></th>
		</tr>
	</thead>
	<tbody>
		<tr class='template'>
			<td class="ManageTablesTableID fieldContent">.</td>
			<td class="ManageTablesName fieldContent">.</td>
			<td class="ManageTablesDatabaseName fieldContent">.</td>
		</tr>
	</tbody>
</table>
</div>

<script type="text/javascript">
	var json = {"ManageTables":[{"ManageTablesDatabaseName":"dd_table","ManageTablesName":"Table","ManageTablesTableID":"1"},{"ManageTablesDatabaseName":"dd_column","ManageTablesName":"Column","ManageTablesTableID":"2"},{"ManageTablesDatabaseName":"dd_join","ManageTablesName":"Join","ManageTablesTableID":"3"},{"ManageTablesDatabaseName":"dd_page","ManageTablesName":"WebPages","ManageTablesTableID":"4"},{"ManageTablesDatabaseName":"dd_field","ManageTablesName":"Field","ManageTablesTableID":"5"},{"ManageTablesDatabaseName":"dd_view","ManageTablesName":"View","ManageTablesTableID":"6"},{"ManageTablesDatabaseName":"dd_smart_codes","ManageTablesName":"SmartCodes","ManageTablesTableID":"8"}]};

	var ManageTablesTable = $("#ManageTables table:first");
	var ManageTablesTableTemplate = $(ManageTablesTable).find('tr.template');
	for ( var i = 0; i < json["ManageTables"].length; i++) {
		var row = json["ManageTables"][i];
		addRow(ManageTablesTable, row);
	}
	function addRow(table, row) {
		$tr = $(ManageTablesTableTemplate).clone();
		$tr.find('.ManageTablesTableID').html(row.ManageTablesTableID);
		$tr.find('.ManageTablesName').html(row.ManageTablesName);
		$tr.find('.ManageTablesDatabaseName').html(row.ManageTablesDatabaseName);
		$(table).append($tr);
	};
</script>

</body>
</html>
