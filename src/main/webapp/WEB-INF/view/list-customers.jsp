<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.luv2code.springdemo.util.SortUtils" %>

<!DOCTYPE html>

<html>

<head>
	<title>ListCustomers</title>
	
	<!-- reference out style sheet -->
	
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>

<body>

	<div id="wrapper">
		<div id ="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	<p>
		User: <security:authentication property="principal.username"/>
		<br><br>
		Role(s): <security:authentication property="principal.authorities"/>
	</p>
	
	<div id="container">
	
		<div id="content">
			
			<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
			<!-- put new button: Add customer -->
			<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button"
			/>
			</security:authorize>
			
			<!-- add a search form -->
			<form:form action="search" method="GET">
				Search customer: <input type="text" name="theSearchName"/>
				
				<input type="submit" value="Search" class="add-button"/>
			</form:form>
		
			<!-- add out html table here -->
		
			<table>
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
					<th>Action</th>
				</tr>
				
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
						<td>
						
						<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
						<!-- display the update link -->
						<a href="${updateLink}">Update</a>
						</security:authorize>
						
						<security:authorize access="hasRole('ADMIN')">
						|
						<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</security:authorize>
						</td>
					</tr>
					
				</c:forEach>			
			
			</table>
		</div>
	</div>
	<div>
	<br>
			<!-- add a logout button -->
			<form:form action="${pageContext.request.contextPath}/logout" method="POST">
				<input type="submit" value="Logout"/>
			</form:form>
		</div>

</body>

</html>