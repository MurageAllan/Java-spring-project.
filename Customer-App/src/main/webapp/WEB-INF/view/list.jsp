<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list customers</title>
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
					<th>Country.</th>
					<th>Hobby.</th>
					<th>Age.</th>
					<th>Os.</th>
					<th>Postal code.</th>
					<th>Action.</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="customer" items="${theCustomers}">
					<!-- update link -->
					<c:url var="updatelink" value="/customer/update">
						<c:param name="Id" value="${customer.customerId}"></c:param>
					</c:url>

					<!-- delete link -->
					<c:url var="deletelink" value="/customer/delete">
						<c:param name="Id" value="${customer.customerId}"></c:param>
					</c:url>
					<tr>
						<td>${ customer.firstName}</td>
						<td>${ customer.lastName}</td>
						<td>${ customer.emailAddress}</td>
						<td>${ customer.country}</td>
						<td>${ customer.hobby}</td>
						<td>${ customer.age}</td>
						<td>${ customer.operatingSystem}</td>
						<td>${ customer.postalCode}</td>
						<td><a href="${updatelink }"> Update </a> | <a
							href="${ deletelink}"
							onclick="if(!(confirm('Are you sure yo want to delete this customer?'))) return false">
								Delete </a></td>
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
		<input type="button" value="Add cutomer"
			onclick="window.location.href='showFormAdd'" class="btn">

	</div>
</body>
</html>