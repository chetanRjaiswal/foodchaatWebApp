<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>SignUp</title>
<%@include file="../components/css.jsp"%>
<%@include file="../components/AjaxJquery.jsp"%>
<script src="../Validation/Validation.js"></script>
<style>
#hiddenInput {
	visibility: hidden;
}

#hiddenBtn {
	visibility: hidden;
}

.signUp-btn {
	
}
</style>

</head>

<body style="background-color: #f0f1f2">
	<%@include file="../components/indexnavbar.jsp"%>
	<div class="container p-2">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h4 class="text-center">Registration Page</h4>
						<h6 id="paySuccess"></h6>

						<form id="formId">
							<h6 id="frmMsg"></h6>

							<div class="form-group">
								<label>Name</label><br> <input type="text"
									class="form-control" name="username" id="name"
									placeholder="Please Enter Name">
							</div>

							<div class="form-group">
								<label>Email address</label><br> <input type="text"
									class="form-control" name="useremail" id="mail"
									placeholder="ex:-abc@gmail.com">
							</div>


							<div class="form-group">
								<label>Password</label> <input type="password"
									class="form-control" name="password" id="password"
									placeholder="ex:-1A2#%gh">
							</div>

							<div><b>Captcha</b>
							<img src="${pageContext.request.contextPath}/captcha">
								<br> <input type="text"
									class="form-control" name="uCaptcha" id="captcha"
									> <br> ${error}
								
							</div>
							<div class="text-center">
								<button type="button" class="btn btn-primary signUp-btn"
									onclick="submitForm()">Sign Up</button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		
		function submitForm() {
			var user = {
				"fullName" : $("#name").val(),
				"email"    : $("#mail").val(),
				"password" : $("#password").val(),
				"captcha"  : $("#captcha").val(),
			};
			
			$("#paySuccess").text(""); 
			$("#frmMsg").text("");

			if (checkUserDetalis() && $("#captcha").val()!='') {
				$.ajax({
					type : "POST",
					contentType : 'application/json; charset=utf-8',
					/* dataType : 'json', */
					data : JSON.stringify(user),
					url : "/userSignUp",
					success : function(response) {
						$("#frmMsg").text("Registered Successfully").css(
								'color', 'green');
						window.location.href = "/views/userLogin.jsp";
					},
					error : function(error) {
						$("#frmMsg").text(
								"Server Error or Email already Registered")
								.css('color', 'red');
						console.log("regisered erroorrrrrrrrrrrrrrr");
					},
				});
			} 
			else
			{
				$("#frmMsg").text("Enter Deatils according to placeholders")
						.css('color', 'red');
			}

		}
	</script>

</body>
</html>