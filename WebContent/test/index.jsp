<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="tantalum.ui.PageDAO"%>
<%@page import="tantalum.entities.*"%>
<%
	String[] models = request.getParameterValues("model");
	String[] tables = request.getParameterValues("table");
	PageDAO pageDAO = new PageDAO();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="tantalum.util.Printer"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testing</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	//$("ul").attr("onclick", "$(this).toggle()");
});
</script>
<style>
ul {
	border: solid 1px gray;
}
</style>
</head>
<body>
<h1>Tantalum Testing</h1>
<%
	if (tables != null) {
		for (int i = 0; i < tables.length; i++) {
			MetaTable table = pageDAO.getTable(tables[i]);
			%><%=Printer.print(table)%><%
	}
	}
	if (models != null) {
		for (int i = 0; i < models.length; i++) {
			Model model = pageDAO.getWebPageDefinition(models[i]);
			%><%=Printer.print(model)%><%
	}
	}
%>

</body>
</html>