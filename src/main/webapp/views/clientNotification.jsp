<%@page import="java.util.ArrayList"%>
<%@page import="com.onlinefoodchaat.entity.Notification"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="../components/css.jsp"%>
<%@include file="../components/AjaxJquery.jsp"%>
</head>
<body>
	
	<%@include file="../views/ClientDashBoard.jsp"%>
	<h1>client notify</h1>
	<%
	List<Notification> nos = (ArrayList) request.getAttribute("clientNotifys");
	for (Notification n : nos) 
	 {
	%>
	 <h1><%=n.getClientNotification()%></h1>
	<%
	 }
	%>
	
</body>
</html>