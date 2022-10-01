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
<script src="https://code.jquery.com/jquery-3.1.1.min.js">

</head>
<body>

<%@include file="../views/userSideBar.jsp"%>
	<div class="offset-lg-2">
	<h4 id="cartMsg" class="text-center"></h4>
	<table class="table table-striped tableFixHead">
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
			List<Dish> dishes = (ArrayList<Dish>) request.getAttribute("MenuList");
			String path = "C:\\Users\\Dell\\Documents\\workspace-spring-tool-suite-4-4.15.1.RELEASE\\online-food-chaat-Assessment-3\\src\\main\\resources\\static\\DishImage";
			for (Dish dish : dishes) {
			%>
			<tr>

				<td><%=dish.getDishName()%></td>
				<td><%=dish.getDishPrice()%></td>
				<td><img src="DishImage/<%=dish.getDishImg()%>"
					style="width: 150px; height: 150px;"></td>

				<td>
				    <input type="button" onclick="decrementValue(<%=dish.getId()%>)" value="-" style="width:30px;" />

					<input type="text" name="quantity" value="0" maxlength="2" max="10"
					size="1" id='<%=dish.getId()%>' class="<%=dish.getId()%>" readonly/> 
					
					<input type="button" onclick="incrementValue(<%=dish.getId()%>)" value="+" style="width:30px;" /> 
					
					
					<button class="btn btn-sm btn-success" onclick="addToCart(<%=dish.getId()%>,<%=user.getId()%>,<%=dish.getClient().getId()%>)">Add To Cart</button>
					
				</td>

			</tr>

			<%
			 }
			%>

		</tbody>
	</table>
    </div>

<script type="text/javascript">

		function incrementValue(id) {
			console.log(id);
			var value = document.getElementById(id).value;
			console.log("value +"+value);
			if (value < 10) {
				value++;
				quant=value;
				document.getElementById(id).value = value;
			}
		}
		
		function decrementValue(id) {
			console.log(id);
			var value = parseInt(document.getElementById(id).value);
			if (value >  0) {
				value--;
				quant=value;
				document.getElementById(id).value = value;
			}

		}
		
	   function addToCart(dishId,userId,clientId)
	   {
		  let quan = parseInt(document.getElementById(dishId).value);
		 
		   console.log(" dishId->"+dishId+" userId->"+userId+" quanti->"+quan+" clientId"+clientId);
				
				 
				   if(quan>0)
					  {
					   console.log("indside if true");
						$.ajax({
							type : "POST",
							url : "/saveToCart?dishId=" + dishId + "&userId=" + userId + "&clientId=" +clientId+ "&quantity="+quan,
							success : function(response) {
								$("#cartMsg").text("Added to Cart successfully").css('color','green');
							},
							error : function(data) {
								$("#cartMsg").text("you can add to cart from one resto at a time").css('color','red');
								console.log("errrooooooorrrr");
							}
						});	
					  }
				   else
					   {
					    $("#cartMsg").text("Please Add Quantity").css('color','red');
					   }
				  
		 }
		
		
	</script>

</body>
</html>