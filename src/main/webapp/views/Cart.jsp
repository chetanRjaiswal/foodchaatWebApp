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
<script src="https://code.jquery.com/jquery-3.1.1.min.js">

</head>
<body>

<%@include file="../views/userSideBar.jsp"%>

	<div class="offset-lg-2">
	<h4 id="cartUpdateMsg" class="text-center"></h4>
	<table class="table table-striped tableFixHead">
		<thead class="p-3 mb-2 bg-info text-white ">

			<tr>

				<th scope="col">Dish Name</th>
				<th scope="col">Price</th>
				<th scope="col">Image</th>
				<th scope="col">TotalAmount</th>
				<th scope="col">Action</th>

			</tr>
		</thead>
		<tbody>
			<%
			List<Cart> list = (ArrayList<Cart>) request.getAttribute("listOfCart");
			String path = "C:\\Users\\Dell\\Documents\\workspace-spring-tool-suite-4-4.15.1.RELEASE\\online-food-chaat-Assessment-3\\src\\main\\resources\\static\\DishImage";
			if(list.size()>0){
			for (Cart cart : list) {
				session.setAttribute("CartClientId",cart.getClientId());
			%>
			<tr>

				<td><%=cart.getDishName()%></td>
				<td><%=cart.getDishPrice()%></td>
				<td><img src="DishImage/<%=cart.getDishImage()%>"
					style="width: 50px; height: 50px;"></td>
					
				<%-- <td><%=cart.getQuantity()%></td> --%>
				
				 <td><input type="text" id="<%=cart.getId()+101%>" value="<%=cart.getQuantity()*Integer.parseInt(cart.getDishPrice())%>" style="width: 50px;" readonly/></td>
				
				<td>
				
				 <input type="button" onclick="decrementValue(<%=cart.getId()%>,<%=cart.getDishPrice()%>,<%=cart.getId()+101%>)" value="-" style="width:30px;" />

					<input type="text" name="quantity" value="<%=cart.getQuantity()%>" maxlength="2" max="10"
					size="1" id="<%=cart.getId()%>" readonly/> 
					
					<input type="button" onclick="incrementValue(<%=cart.getId()%>,<%=cart.getDishPrice()%>,<%=cart.getId()+101%>)" value="+" style="width:30px;" /> 
					
				  <button class="btn btn-sm btn-danger" onclick="deleteFromCart(<%=cart.getId()%>)"> Remove from cart </button>
			
				</td>  
			</tr>
			<%
			 }  %>    
		<%	}
			%>
			
		</tbody>
	</table>
	<label style="margin-left:400px;">Total </label>
	<label style="margin-left:15px;" id="finalSum"><%= request.getAttribute("totalSum")%></label>
	<button class="btn btn-sm btn-success" onclick="buy()" style="margin-left:200px;">Buy</button>
    </div>
<script type="text/javascript">
   
		function incrementValue(id,dishPrice,totalId) {
			console.log(totalId);
			var value = document.getElementById(id).value;
			console.log("value +"+value);
			if (value < 10) {
				value++;
				quant=value;
				console.log("quant"+quant);
				document.getElementById(id).value = quant;	
				var total=parseInt(dishPrice)*quant;
				console.log(total);
				var cartId=id;
				//console.log("cartiD"+cartId);
				document.getElementById(totalId).value = total;
				$.ajax({
					type : "POST",
					url : "/updgradeCart?cartId=" + cartId + "&quantity="+quant,
					success : function(response) {
						$("#cartMsg").text("Added to Cart successfully").css('color','green');
						location.reload(true);
						
					},
					error : function(data) {
						$("#cartMsg").text("Failed Try again").css('color','red');
						console.log("errrooooooorrrr");
					}
				});
			
			}
		}
		
		function decrementValue(id,dishPrice,totalId) {
			console.log(id);
			var value = parseInt(document.getElementById(id).value);
			if (value >  0) {
				value--;
				quant=value;
				console.log("quant"+quant);
				document.getElementById(id).value = value;
				var totalIdValue=document.getElementById(totalId).value;
				var total=totalIdValue - dishPrice;
				console.log(total);
				document.getElementById(totalId).value = total;
				var total=parseInt(dishPrice)*quant;
				console.log(total);
				var cartId=id;
				//console.log("cartiD"+cartId);
				document.getElementById(totalId).value = total;
				$.ajax({
					type : "POST",
					url : "/degradeCart?cartId=" + cartId + "&quantity="+quant,
					success : function(response) {
						$("#cartMsg").text("Added to Cart successfully").css('color','green');
						location.reload(true);
					},
					error : function(data) {
						$("#cartMsg").text("Failed Try again").css('color','red');
						console.log("errrooooooorrrr");
					}
				});
			}

		}
		

		function deleteFromCart(cartId) 
		{
			console.log(cartId);
				$.ajax({
					type : "POST",
					url : "/deleteBycartId?cartId=" + cartId,  
					success : function(response) {
						$("#cartMsg").text("Deleted from Cart successfully").css('color','green');
						location.reload(true);
					},
					error : function(data) {
						$("#cartMsg").text("Failed Try again").css('color','red');
						console.log("errrooooooorrrr");
					}
				});
			
			}
		
		
		function placeOrder(clientId,dishName,totalId,cartId) 
		{
			var totalAmount=document.getElementById(totalId).value;
			var quantity=document.getElementById(cartId).value;
			    console.log(totalAmount+""+quantity);
			    if(quantity>0)
			    {
					$.ajax({
						type : "POST",
						url : "/placeOrder?clientId=" + clientId + "&dishName=" + dishName +"&quantity=" +quantity +"&totalAmount=" + totalAmount +"&cartId="+cartId,  
						success : function(response) {
							$("#cartMsg").text("order placed").css('color','green');
							location.reload(true);
						},
						error : function(data) {
							$("#cartMsg").text("Failed Try again").css('color','red');
							console.log("errrooooooorrrr");
						}
					});
			    }
			    else
				   {
				    $("#cartUpdateMsg").text("Please Add Quantity").css('color','red');
				   }	
			}
		
		function buy()
		{
			var finalSum = document.getElementById('finalSum').innerHTML;
			//console.log(finalSum);
			if(finalSum>0)
			{
			$.ajax({
				type : "POST",
				url : "/buy?finalSum=" + finalSum,  
				success : function(response) {
					$("#cartMsg").text("order placed").css('color','green');
					location.reload(true);
				},
				error : function(data) {
					$("#cartMsg").text("Failed Try again").css('color','red');
					console.log("errrooooooorrrr");
				}
			});
			}
			else
			   {
			    $("#cartUpdateMsg").text("Please Add Quantity").css('color','red');
			   }
	    
		 }
		
	</script>

</body>
</html>