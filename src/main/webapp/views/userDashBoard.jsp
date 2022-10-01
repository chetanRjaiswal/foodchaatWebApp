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
<%@include file="../components/css.jsp"%>
<%@include file="../components/AjaxJquery.jsp"%>
</head>
<body>

    <%
    if ((Integer) session.getAttribute("loginUserId") != null)
	{%>
	
	<%@include file="../views/userSideBar.jsp"%>
	
   <div class="offset-lg-2">
	<table class="table table-striped  tableFixHead">
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
			List<Client> clientList = (ArrayList<Client>) request.getAttribute("ListOfClients");
			if(clientList.size()>0){
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
			}
			%>
		</tbody>
		
	</table>
	</div>
	<%} %>
	
	 <%
    if ((Integer) session.getAttribute("loginUserId") == null)
	{%>
	<%
	response.sendRedirect("login.jsp");
	%>
	<%}%>
	
</body>
</html>