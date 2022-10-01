<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Resto page</title>
<%@include file="../components/css.jsp"%>
<%@include file="../components/AjaxJquery.jsp"%>
</head>

<body style="background-color: #f0f1f2">
<%@include file="../views/ClientDashBoard.jsp"%>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
				    
						<h4 class="text-center">Add Resto Details</h4>
						<%String msg=(String)session.getAttribute("restoErrMsg"); %>
						<%if(msg!=null) {%>
							<span style="color: red;"><%=msg%></span>
						<%} 
						%>
						
						<% session.removeAttribute("restoErrMsg");	%>
						
						<%
						 Integer loginClientId=(Integer)session.getAttribute("loginClientId");
						%>
						
						<form name="addRestrofrm" action="/addRestoDetails" method="post" onsubmit="return validation()">
												 
						 <span id="formspan" style="color: red;"></span>
						 
						<input type="hidden" value="<%=loginClientId%>" name="clinetId"/> 
							
							<div class="form-group">
							  <label>Name</label>
							  <br>
						      <input type="text" class="form-control" name="restoName" id="name"
						       placeholder="Please Enter Name">
							</div>
						
							<div class="text-center">
							 <button type="submit" class="btn btn-primary signUp-btn" >Add Resto</button>
							</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

 <script type="text/javascript">
		var restoName = document.forms['addRestrofrm']['restoName'];
		
		function validation() {
			if (restoName.value != '') 
			{
				return true;
			}
			else 
			{
				document.getElementById("formspan").innerHTML = "Please Add Resto Name";
				return false;
			}
		}
	</script>

</body>
</html>