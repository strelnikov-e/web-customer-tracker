<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirmation registration</title>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
		<style>
		.error {color:red}
	</style>
</head>
<body>
	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
			
		<div class="panel panel-info">

			<div class="panel-heading">
			<h2>Success!</h2>
			</div>
			<div style="padding-top: 20px" class="panel-body">
			<h4>User registered succesfully!</h4>
				<div style="padding-top: 20px" class="panel-body">
		
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