<%@page import="com.onlinefoodchaat.entity.Dish"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Dish</title>
<%@include file="../components/css.jsp"%>
<%@include file="../components/AjaxJquery.jsp"%>
</head>
<body>
<%@include file="../views/ClientDashBoard.jsp"%>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
				   
				   <%Dish dish=(Dish)request.getAttribute("dishById"); %> 
						<h4 class="text-center">Edit Dish Details</h4>
						
						<form name="regform" action="/editDishDetails" method="post" enctype="multipart/form-data" onsubmit="return checkEmpty()">
					
						 <span id="formspan" style="color: red;"></span>
						 <input type="hidden" name="id" value="<%= dish.getId() %>"/>
							
						<div class="form-group">
						    <label>Dish Name</label>
						    <input type="text" class="form-control" name="dishName" id="dishName" value="<%=dish.getDishName() %>" >
						</div>
							
						<div class="form-group">
						    <label>Price</label>
						    <input type="text" class="form-control" name="dishPrice" id="price" value="<%= dish.getDishPrice()%>">
						</div>
							
						<div class="form-group">
							 <label>Uploaded Image</label>
							   <img src="DishImage/<%=dish.getDishImg()%>" style="width: 100px; height: 70px;"/>
							   <input type="file" name="dishimg" class="form-control-file" />
			
						</div>
							
						<div class="text-center">
							 <button type="submit" class="btn btn-primary signUp-btn" >Save</button>
						</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
 <script type="text/javascript">
	var dishName=document.forms['regform']['dishName'];
	var price=document.forms['regform']['price'];
	var dishImg=document.forms['regform']['dishImg'];
	function checkEmpty()
	{
		if(dishName.value!='' && price.value!=''){
		return true;
		}
		else{
			document.getElementById("formspan").innerHTML="Please Fill all Details";
			return false;
	  }
    }
 </script>
	
</body>
</html>

