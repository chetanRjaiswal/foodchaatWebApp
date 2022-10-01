<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Dish</title>
<%@include file="../components/css.jsp"%>
<%@include file="../components/AjaxJquery.jsp"%>
</head>
<body>
	<%
	if ((Integer) session.getAttribute("loginClientId") != null) {
	%>
	<%@include file="../views/ClientDashBoard.jsp"%>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">

						<h4 class="text-center">Add Dish Details</h4>

						<form name="regform" action="/addDishDetails" method="post"
							enctype="multipart/form-data" onsubmit="return checkEmpty()">

							<span id="formspan" style="color: red;"></span>

							<div class="form-group">
								<label>Dish Name</label> <input type="text" class="form-control"
									name="dishName" id="dishName">
							</div>

							<div class="form-group">
								<label>Price</label> <input type="text" class="form-control"
									name="price" id="price">
							</div>

							<div class="form-group">
								<label>Upload Image</label> <input type="file" name="dishImg"
									class="form-control-file">
							</div>

							<div class="text-center">
								<button type="submit" class="btn btn-primary signUp-btn">Save</button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
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


	<script type="text/javascript">
		var dishName = document.forms['regform']['dishName'];
		var price = document.forms['regform']['price'];
		var dishImg = document.forms['regform']['dishImg'];
		function checkEmpty() {
			if (dishName.value != '' && price.value != ''
					&& dishImg.value != '') {
				return true;
			} else {
				document.getElementById("formspan").innerHTML = "Please Fill all Details";
				return false;
			}
		}
	</script>

</body>
</html>

