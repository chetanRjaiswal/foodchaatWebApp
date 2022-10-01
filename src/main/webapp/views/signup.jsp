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
.signUp-btn{

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
						
						<form action="#" method="post" id="formId">
							<h6 id="frmMsg"></h6>
							
							<div class="form-group">
							  <label>Name</label><br>
						      <input type="text" class="form-control" name="username" id="name"
						       placeholder="Please Enter Name">
							</div>

							<div class="form-group">
								<label>Email address</label><br>
								<input type="text" class="form-control" name="useremail" id="mail"
								 placeholder="ex:-abc@gmail.com">
							</div>

							<div class="form-group">
								<label>Phone Number</label> 
								<input type="text" class="form-control" name="userPhone" id="phone"
								placeholder="ex:-1234567890">
							</div>

							<div class="form-group">
							  <label>Password</label>
							  <input type="password" class="form-control" name="userPhone" id="password"
							  placeholder="ex:-1A2#%gh">
							</div>

							<div>
								<label>Select Plans</label> <span id="planmsg"></span> <select
									class="form-control form-control-sm" name="plan" id="plan">
									<option value="one Month">One Month Buy 150 rs</option>
									<option value="two Month">Two Months Buy 290 rs</option>
									<option value="three Month">Three Months Buy 400 rs</option>
								</select>
							</div>
							<br>
							<div class="buttonIn">
	
							<input type="button" class="btn btn-primary" value="Pay" id="payBtn" onclick="pay()"/> 	
							<input type="text" id="hiddenInput" style="border-color:f0f1f2;" placeholder="Enter OTP"/>
							
							</div>
							 <div class="text-center">
							 <button type="button" class="btn btn-primary signUp-btn" onclick="submitForm()" >Sign Up</button>
							</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">	
	//Call payment controller
  function pay() 
	  {
			let mail = $("#mail").val();
			let plan = $("#plan").val();
			
			//setting innertext blank
			$("#paySuccess").text("");
			 $("#frmMsg").text("");
			 
			   if(checkDetalis())
				  {
					$.ajax({
						type : "POST",
						url : "/sendPaymentOtp?email=" + mail,
						success : function(response) {
							document.getElementById("hiddenInput").style.visibility="visible" 
							$("#paySuccess").text("OTP send successfully").css('color','green');
						},
						error : function(data) {
							$("#paySuccess").text("OTP send Failed Try again").css('color','red');
							console.log("errrooooooorrrr");
						}
					});	
				  }
			   else
				   {
				    $("#frmMsg").text("Enter Deatils according to placeholders").css('color','red');
				   }	
	 }

	
function submitForm() 
   {
	var client = {
					"name" : $("#name").val(),
					"email" : $("#mail").val(),
					"phone" : $("#phone").val(),
					"password" : $("#password").val(),
					"plan" : $("#plan").val(),
					"otp" : $("#hiddenInput").val(),
				};
			$("#paySuccess").text("");
			 $("#frmMsg").text("");
			 
     if(checkDetalis())
        {
			$.ajax({
				type : "POST",
				contentType : 'application/json; charset=utf-8',
			 	/* dataType : 'json', */ 
				data: JSON.stringify(client),
				url : "/signUpclient",
				success : function(response) {
					 $("#frmMsg").text("Registered Successfully").css('color','green');
					 window.location.href = "/views/login.jsp";
				},
				error : function(error) {
					$("#frmMsg").text("Server Error or Email already Registered").css('color','red');
					console.log("regisered erroorrrrrrrrrrrrrrr");
				},
			});
    	 }
     else
	   {
	    $("#frmMsg").text("Enter Deatils according to placeholders").css('color','red');
	   }
     
	 }
		
	</script>

</body>
</html>