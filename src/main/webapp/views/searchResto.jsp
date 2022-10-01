<%@page import="java.util.ArrayList"%>
<%@page import="com.onlinefoodchaat.entity.Client"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>userDashBoard</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>

</head>
<body>
<%@include file="../views/userSideBar.jsp"%>

	<div class="offset-lg-2">
	<table class="table table-striped tableFixHead">
		<thead class="p-3 mb-2 bg-info text-white ">

			<tr>
				<th scope="col"><h3>Resto Name</h3></th>
				<th scope="col"><h3>Action</h3></th>
				<th scope="col">
					<form action="/searchRestoController" method="post">
					 <input type="search"
					 placeholder="Enter Resto Name" aria-label="Search" name="restoName" style="width:200px;">
					<button class="btn btn-success" type="submit"
					style="color: white;">Search</button></form>
				</th>
			</tr>
		</thead>
		<tbody>

			<%
			List<Client> clientList = (ArrayList<Client>) request.getAttribute("clientsByRestoName");
			for (Client client : clientList) {
			%>

			<tr>

				<td>
					<h4><%=client.getRestoName()%></h4>
				</td>

				<td><a
					href="/viewRestoMenu?getMenuByclientId=<%=client.getId()%>"
					class="btn btn-sm btn-success">View Menu</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
    </div>
</body>
</html>