<%@page import="com.onlinefoodchaat.entity.Client"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<%@include file="../components/css.jsp"%>
<%@include file="../components/AjaxJquery.jsp"%>
<script src="../Validation/Validation.js"></script>
<style type="text/css">
#hiddenInput {
	visibility: hidden;
}
</style>
</head>
<body style="background-color: #f0f1f2">
	<%@include file="../components/indexnavbar.jsp"%>
	<%
	String error= (String)session.getAttribute("error");
	%>
	<div class="container p-3">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h4 class="text-center">User Login Page</h4>
						<%if(error!=null)
						 {%>
						   <span id="loginMsg" style="color: red;">Invalid Credentials</span>
						<%}
						 session.removeAttribute("error");
						%>
						<span id="emptyField" style="color: red;"></span>
						<form action="/userLogin" method="post" onsubmit="return checkEmpty()">

							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label> <input
									type="email" class="form-control" id="mail"
									aria-describedby="emailHelp" name="email" >
							</div>

							<div class="form-group">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="pwd"
									name="password" >
							</div>
							
							<div class="form-group">
							<input type="button" class="btn btn-primary" value="Get OTP" id="payBtn" onclick="getOtp()"/> 	
							<input type="text" id="hiddenInput" style="border-color:f0f1f2;" name="otp" placeholder="Enter OTP" />
							</div>

							<div>
								<button type="submit" class="btn btn-primary">Login</button>
								<br> <a href="../views/signup.jsp">Create Account</a>
							</div>

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	
	function checkEmpty(){
		let email = $("#mail").val();
		let pwd = $("#pwd").val();
		let hiddenInput = $("#hiddenInput").val();
		if(email!='' && pwd!='' && hiddenInput!='')
		 {
			return true;
		 }
		else
		{
		  $("#emptyField").text("Please Enter Email,Password & OTP ").css('color','red');
		  return false;
		}
	}
	
	
	  function getOtp() 
	  {
			let email = $("#mail").val();
			if(email!='')
			   {
					$.ajax({
						type : "POST",
						url : "/sendPaymentOtp?email="+email,
						success : function(response) {
							document.getElementById("hiddenInput").style.visibility="visible" 
						},
						error : function(data) {
							$("#loginMsg").text("OTP send Failed Try again").css('color','red');
							console.log("errrooooooorrrr");
						}
					});	
			   }
			else
				{
				 $("#emptyField").text("Please Enter Email & Password").css('color','red');
				}
		}
			  	
	</script>
	
</body>
</html>