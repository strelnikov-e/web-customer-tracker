<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!doctype html>
<html lang="en">

<head>
	
	<title>Register New User Form</title>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
		<style>
		.error {color:#cc6868}
	</style>

</head>

<body>

	<div>
		
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
			
			<div class="panel panel-primary">

				<div class="panel-heading">
					<div class="panel-title">Register New User</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- Registration Form -->
					<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm" 
						  	   modelAttribute="crmUser"
						  	   class="form-horizontal">

					    <!-- Place for messages: error, alert etc ... -->
					    <div class="form-group">
					        <div class="col-xs-15">
					            <div>
								
									<!-- Check for registration error -->
									<c:if test="${registrationError != null}">
								
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError}
										</div>
		
									</c:if>
																			
					            </div>
					        </div>
					    </div>

						<!-- User name -->
							<form:errors path="userName" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="userName" placeholder="username (*)" class="form-control" />
						</div>

						<!-- Password -->
							<form:errors path="password" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							<form:password path="password" placeholder="password (*)" class="form-control" />
						</div>
						
						<!-- Confirm Password -->
							<form:errors path="matchingPassword" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							<form:password path="matchingPassword" placeholder="confirm password (*)" class="form-control" />
						</div>
					
						
						<!-- First name -->
							<form:errors path="firstName" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="firstName" placeholder="first name (*)" class="form-control" />
						</div>
						
						<!-- Last name -->
							<form:errors path="lastName" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="lastName" placeholder="last name (*)" class="form-control" />
						</div>
						
						<!-- Email -->
							<form:errors path="email" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="email" placeholder="email (*)" class="form-control" />
						</div>
						
						

						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-success">Register</button>
							</div>
						</div>
						
					</form:form>
					
					<!-- Back to login Button -->
						<div style="margin-top: 10px" class="form-group">						
							<a href="${pageContext.request.contextPath}/login"
							class="btn btn-primary"
							role="button" aria-pressed="true">
							Back to login
							</a>
						</div>

				</div>

			</div>

		</div>

	</div>

</body>
</html>