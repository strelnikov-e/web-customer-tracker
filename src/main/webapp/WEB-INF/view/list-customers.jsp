<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.luv2code.springdemo.util.SortUtils" %>

<!doctype html>
<html lang="en">

<head>
	
	<title>CRM Main page</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet" 
		href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</head>

<body>

	<div id="wrapper" style="margin-top: 50px;"
		class="mainbox col-md-8 col-md-offset-2 col-sm-6 col-sm-offset-2">	
		<div id="header" class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title"><h3>CRM - Customer Relationship Manager</h3></div>
			</div>	
			<div style="padding-top: 30px" class="panel-body">
	
	<div id="container">
	
		<div id="content">
			
			<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
			<!-- put new button: Add customer -->
			<div>
				<br>
				<a href="${pageContext.request.contextPath}/customer/showFormForAdd"
					class="btn btn-primary"
					role="button" aria-pressed="true">
					Add Customer
				</a>
			</div>
			<br>
			</security:authorize>
			
			<!-- add a search form -->
			<form:form action="search" method="GET">
				<div style="margin-bottom: 25px" class="input-group" >
					<div class="input-group-append">
						<input type="text" name="theSearchName" placeholder="Search Customer" class="form-control">
						<input type="submit" value="Search" class="btn btn-outline-secondary"/>
					</div>
				</div>
			</form:form>
			
			<!-- add out html table here -->

			<table class="table">
				<thead class="thead-light">
					<!-- counstruct a sort link for first name -->
					<c:url var="sortLinkFirstName" value="/customer/list">
						<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>"/>
					</c:url>
					<!-- counstruct a sort link for last name -->
					<c:url var="sortLinkLastName" value="/customer/list">
						<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>"/>
					</c:url>
					<!-- counstruct a sort link for email -->
					<c:url var="sortLinkEmail" value="/customer/list">
						<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>"/>
					</c:url>
				
					<tr>
						<th><a href="${sortLinkFirstName}">First Name</a></th>
						<th><a href="${sortLinkLastName}">Last Name</a></th>
						<th><a href="${sortLinkEmail}">Email</a></th>
						<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
							<th>Action</th>
						</security:authorize>
					</tr>
				</thead>
					
				<tbody>
					
					<!-- loop over and print out customers -->
					<c:forEach var="tempCustomer" items="${customers}">
						
						<!-- constuct an update link with customer id -->
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${tempCustomer.id}"/>
						</c:url>
						
						<!-- constuct an delete link with customer id -->
						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${tempCustomer.id}"/>
						</c:url>
						
						<tr>
							<td> ${tempCustomer.firstName} </td>
							<td> ${tempCustomer.lastName} </td>
							<td> ${tempCustomer.email} </td>
							
							
							<security:authorize access="hasRole('MANAGER')">
							<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							</td>
							</security:authorize>
							
							<security:authorize access="hasRole('ADMIN')">
							<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
							</td>
							</security:authorize>
						</tr>
						
					</c:forEach>
				</tbody>			 
			
			</table>
		</div>
	</div>

	<div>
	<br>
			<!-- add a logout button -->
			<form:form action="${pageContext.request.contextPath}/logout" method="POST">
				<input type="submit" value="Logout" class="btn btn-primary"/>
			</form:form>
		</div>
	</div>
</div>
</div>


</body>

</html>