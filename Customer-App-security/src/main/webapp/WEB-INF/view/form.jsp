<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer form</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style2.css" />
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>
	<div class="top-container">
		<div class="header">
			<h2>Customer form page.</h2>
			<h3>Fill out all the spaces with * it is required.</h3>
		</div>
	</div>
	<div class="middle-container">
		<form:form modelAttribute="theCustomer" method="POST" action="save">
			<form:hidden path="customerId" />

			<form:label path="firstName"> First name. *</form:label>
			<form:errors path="firstName" cssClass="error"></form:errors>
			<form:input path="firstName" class="text" />


			<form:label path="lastName"> Last name. *</form:label>
			<form:errors path="lastName" cssClass="error"></form:errors>
			<form:input path="lastName" class="text" />


			<form:label path="age"> Age.</form:label>
			<form:errors path="age" cssClass="error"></form:errors>
			<form:input path="age" class="text" />


			<label for="email"> Email.</label>
			<input type="email" name="emailAddress" id="email" class="text">


			<form:label path="country"> Country.</form:label>
			<form:input path="country" class="text" />


			<form:label path="hobby"> Hobby.</form:label>
			<form:input path="hobby" class="text" />


			<form:label path="operatingSystem"> OS.</form:label>
			<form:input path="operatingSystem" class="text" />


			<form:label path="postalCode"> Postal code.</form:label>
			<form:errors path="postalCode" cssClass="error"></form:errors>
			<form:input path="postalCode" class="text" />



			<input type="submit" value="Save" class="submit">
		</form:form>

		<a href="${pageContext.request.contextPath}/customer/list"> Back
			to list page</a>
	</div>
</body>
</html>