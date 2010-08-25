<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String xaction = request.getParameter("xaction");

	if ("read".equals(xaction)) {
%>{ success: true, total: 4, test: [ {id: 1, name: 'Trevor', age:36}, {id: 2, name: 'Gretchen',
age:33},{id: 3, name: 'Hunter', age:8}, {id: 4, name: 'Gabriel Finn',
age:3} ] }<%
	} else {
		/*
		{
		test: {
			create: [
				{name: 'Colton', age:0}, 
				{name: 'Chuck Pate',age:50}
		 	],
			destroy: [
				{id: 1}
		 	],
			update: [
				{id: 4, age:4}
		 	]
		}
		 */
%>{ success: true, test: { msg: "Success",
			create: [
				{id: 5, name: 'Colton', age:0}, 
				{id: 6, name: 'Chuck Pate',age:50}
		 	],
			update: [
				{id: 4, age:4}
		 	]
}
}
<%
	}
%>
