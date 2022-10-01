<%@page import="com.onlinefoodchaat.entity.Client"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client DashBoard</title>
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
<link rel="stylesheet" href="styles.css">
<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap')
	;

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
	font-family: 'Josefin Sans', sans-serif;
}

body {
	background-color: #f3f5f9;
}

.wrapper {
	display: flex;
	position: relative;
}

.wrapper .sidebar {
	width: 200px;
	height: 100%;
	background: #4b4276;
	padding: 30px 0px;
	position: fixed;
}

.wrapper .sidebar h2 {
	color: #fff;
	text-transform: uppercase;
	text-align: center;
	margin-bottom: 30px;
}

.wrapper .sidebar ul li {
	padding: 15px;
	border-bottom: 1px solid #bdb8d7;
	border-bottom: 1px solid rgba(0, 0, 0, 0.05);
	border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.wrapper .sidebar ul li a {
	color: #bdb8d7;
	display: block;
}

.wrapper .sidebar ul li a .fas {
	width: 25px;
}

.wrapper .sidebar ul li:hover {
	background-color: #594f8d;
}

.wrapper .sidebar ul li:hover a {
	color: #fff;
}

.wrapper .sidebar .social_media {
	position: absolute;
	bottom: 0;
	left: 50%;
	transform: translateX(-50%);
	display: flex;
}

.wrapper .sidebar .social_media a {
	display: block;
	width: 40px;
	background: #594f8d;
	height: 40px;
	line-height: 45px;
	text-align: center;
	margin: 0 5px;
	color: #bdb8d7;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.wrapper .main_content {
	width: 100%;
	margin-left: 200px;
}

.wrapper .main_content .header {
	padding: 20px;
	background: #fff;
	color: #717171;
	border-bottom: 1px solid #e0e4e8;
}

.wrapper .main_content .info {
	margin: 20px;
	color: #717171;
	line-height: 25px;
}

.wrapper .main_content .info div {
	margin-bottom: 20px;
}
</style>
</head>
<body>

	<%
	Integer clientId = (Integer) session.getAttribute("loginClientId");
	%>
	<%
	Client client = (Client) session.getAttribute("loginClientObj");
	%>

	<%
	if (client != null && clientId != null) {
	%>
	<h2 style="color: Black; text-align: center;">
		 <b>Hello <%=client.getName()%></b>
	</h2>
	
	<div class="wrapper">
		<div class="sidebar">

			<ul>
				<li><a href="/addResto?clientId=<%=clientId%>"><i
						class="fas fa-plus"></i>Add Resto</a></li>
				<li><a href="/addDish?clientId=<%=clientId%>"><i
						class="fas fa-plus"></i>Add Dish</a></li>
				<li><a href="/viewAllDishesByClient"><i class="fa fa-eye"></i>View
						Dishes</a></li>
				<li><a href="/viewOrders"><i class="fa fa-eye"></i>View
						Orders</a></li>
				<li><a href="/clientnotification?clientId=<%=clientId%>"><i
						class="fa fa-bell"></i>notification</a></li>
				<li><a href="../views/clientAccount.jsp"><i
						class="fas fa-user"></i>My Account</a></li>
				<li><a href="/clientLogout"><i class="fa fa-sign-out"></i>Logout</a></li>

			</ul>

		</div>

	</div>
	<%
	}
	%>
	
</body>
</html>