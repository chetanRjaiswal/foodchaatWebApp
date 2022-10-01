<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="../views/ClientDashBoard.jsp"%>
	<div class="card-header" style="margin-top: 10px;">Plan Details</div>
		
		<div class="card-body">
			<h5 class="card-title"></h5>
			
			<p class="card-text"><%=request.getAttribute("purchaseDate") %></p>
			
	</div>
		
</body>
</html>