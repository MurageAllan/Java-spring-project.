<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list customer.</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div class="top-container">
		<div class="header">
			<h3>Customer Relation Model - CRM.</h3>
		</div>
	</div>
	<div class="middle-container">
		<table>
			<thead>
				<tr>
					<th>First name.</th>
					<th>Last name.</th>
					<th>Email.</th>
					<th>Age.</th>
					<th>Country.</th>
					<th>Hobbies</th>
					<th>Operating system.</th>
					<th>Postal code.</th>
					<th>Action.</th>
				</tr>

			</thead>
			<tbody>
				<c:forEach var="customers" items="${theCustomers}">

					<c:url var="updatelink" value="update">
						<c:param name="Id" value="${ customers.customerId}"></c:param>
					</c:url>

					<c:url var="deletelink" value="delete">
						<c:param name="Id" value="${customers.customerId }"></c:param>
					</c:url>

					<tr>
						<td>${customers.firstName }</td>
						<td>${customers.lastName }</td>
						<td>${customers.emailAddress }</td>
						<td>${customers.age }</td>
						<td>${customers.country }</td>
						<td>${customers.hobbies }</td>
						<td>${customers.operatingSystem }</td>
						<td>${customers.postalCode }</td>
						<td><a href="${updatelink}">Update</a> | <a href="${deletelink}"
							onclick="if(!(confirm('Are you sure yo want to delete this customer?'))) return false">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<!-- add a search bar -->
	<div class="bottom-cont">
		<form action="search" method="GET">
			<table>
				<tr>
					<td><label>Search Customers.</label></td>
					<td><input type="text" placeholder="search customer"
						name="theSearchName"></td>
					<td><input type="submit" value="Search"></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- add button  -->
	<div class="bottom-container">
		<input type="button" value="Add"
			onclick="window.location.href='showFormAdd'" class="btn">

	</div>
</body>
</html>