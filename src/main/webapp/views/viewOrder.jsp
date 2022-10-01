<%@page import="com.onlinefoodchaat.entity.OrderList"%>
<%@page import="com.onlinefoodchaat.entity.Order"%>
<%@page import="com.onlinefoodchaat.entity.Cart"%>
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
<script src="../Validation/Validation.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>

<%if(request.getAttribute("loginClientObj")==null) {%>
<%@include file="../views/ClientDashBoard.jsp"%>
	<div class="offset-lg-2">
	<h4 id="cartUpdateMsg" class="text-center"></h4>
	<table class="table table-striped tableFixHead">
		<thead class="p-3 mb-2 bg-info text-white ">

			<tr>
				<th scope="col">OId</th>
				<th scope="col">UserName</th>
				<th scope="col">Quantity</th>
				<th scope="col">TotalAmount</th>
				<th scope="col">Action</th>

			</tr>
		</thead>
		<tbody>
			<%
			List<OrderList> orders = (ArrayList<OrderList>)request.getAttribute("OrderList");
			if(orders!=null){
				for(OrderList order:orders)
			{%>
			<tr>
				<td><%=order.getId()%></td>	
				
				<td><%=order.getUserName()%></td>
				
				<td><%=order.getQuantity()%></td>
				
				<td><%=order.getSum()%></td>
				
				<td>
					<!-- <button class="btn btn-sm btn-danger" onclick="deleteOrder()">Remove Order</button> -->
					<a href="/viewOrderMenu?userId=<%=order.getUserId()%>"><button class="btn btn-sm btn-success" >View Ordered Dish</button></a>
				</td>  
			</tr>
			
			<%}%>    
		<%}%> 
			
		</tbody>
	</table>
    </div>
    <%}%>

</body>
</html>