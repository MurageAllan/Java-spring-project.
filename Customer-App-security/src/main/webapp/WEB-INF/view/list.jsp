<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.LoveToCode.springmvc.util.SortUtil"%>
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
	<div class="container">
		<p>
			User:
			<security:authentication property="principal.username" />
			<br> Role(s):
			<security:authentication property="principal.authorities" />
		</p>
	</div>
	<div class="middle-container">
		<table>
			<!-- setup header links for sorting -->

			<!-- construct a sort link for first name -->
			<c:url var="sortLinkFirstName" value="/customer/list">
				<c:param name="sort"
					value="<%=Integer.toString(SortUtil.FIRSTNAME)%>" />
			</c:url>
			<!-- construct a sort link for last name -->
			<c:url var="sortLinkLastName" value="/customer/list">
				<c:param name="sort"
					value="<%=Integer.toString(SortUtil.LASTNAME)%>" />
			</c:url>

			<!-- construct a sort link for email -->
			<c:url var="sortLinkEmail" value="/customer/list">
				<c:param name="sort" value="<%=Integer.toString(SortUtil.EMAIL)%>" />
			</c:url>
			<thead>
				<tr>
					<th><a href="${sortLinkFirstName}">First name. </a></th>
					<th><a href="${sortLinkLastName}">Last name. </a></th>
					<th><a href="${sortLinkEmail}">Email.</a></th>
					<th>Country.</th>
					<th>Hobby.</th>
					<th>Age.</th>
					<th>Os.</th>
					<th>Postal code.</th>
					<security:authorize access="hasAnyRole('MANAGER' , 'ADMIN')">
						<th>Action.</th>
					</security:authorize>
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
						<security:authorize access="hasAnyRole('MANAGER' , 'ADMIN')">
							<td><security:authorize
									access="hasAnyRole('MANAGER' , 'ADMIN')">
									<a href="${updatelink }"> Update </a>
								</security:authorize> <security:authorize access="hasAnyRole('ADMIN')">
									|<a href="${ deletelink}"
										onclick="if(!(confirm('Are you sure yo want to delete this customer?'))) return false">
										Delete </a>
								</security:authorize></td>
						</security:authorize>
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

	<security:authorize access="hasAnyRole('MANAGER' , 'ADMIN')">
		<div class="bottom-container">
			<input type="button" value="Add cutomer"
				onclick="window.location.href='showFormAdd'; return false; "
				class="btn">

		</div>
	</security:authorize>
	<p></p>

	<div>
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">
			<input type="submit" value="logout" class="btn">
		</form:form>
	</div>
</body>
</html>