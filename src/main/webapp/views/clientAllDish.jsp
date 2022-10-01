<%@page import="java.util.ArrayList"%>
<%@page import="com.onlinefoodchaat.entity.Dish"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Dishes:</title>
<%@include file="../components/css.jsp"%>
<%@include file="../components/AjaxJquery.jsp"%>
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

	<%
	if ((Integer) session.getAttribute("loginClientId") != null) {
	%>
	<%@include file="../views/ClientDashBoard.jsp"%>

	<table class="table table-striped offset-lg-2 tableFixHead">
		<thead class="p-3 mb-2 bg-info text-white ">

			<tr>

				<th scope="col">Dish Name</th>
				<th scope="col">Price</th>
				<th scope="col">Image</th>
				<th scope="col">Action</th>

			</tr>
		</thead>
		<tbody>
			<%
			List<Dish> dishes = (ArrayList<Dish>) request.getAttribute("ListOfDishes");
			String path = "C:\\Users\\Dell\\Documents\\workspace-spring-tool-suite-4-4.15.1.RELEASE\\online-food-chaat-Assessment-3\\src\\main\\resources\\static\\DishImage";
			if(dishes!=null)
			for (Dish dish : dishes) {
			%>
			<tr>

				<td><%=dish.getDishName()%></td>
				<td><%=dish.getDishPrice()%></td>
				<td><img src="DishImage/<%=dish.getDishImg()%>"
					style="width: 150px; height: 150px;"></td>
				<td><a href="/editDish?dishId=<%=dish.getId()%>" class="btn btn-sm btn-success">Edit</a>
					<a href="/deleteDish?dishId=<%=dish.getId()%>" class="btn btn-sm btn-danger">Delete</a>
				</td>
			</tr>

			<%
			}
			%>

		</tbody>
	</table>
	<%
	}
	%>

	<%
	if ((Integer) session.getAttribute("loginClientId") == null) {
	%>
	<%
	response.sendRedirect("login.jsp");
	%>
	<%
	}
	%>


</body>
</html>