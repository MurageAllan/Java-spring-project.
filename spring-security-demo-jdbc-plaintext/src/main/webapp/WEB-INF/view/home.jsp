<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page.</title>
</head>
<body>
	<h2>LoveToCode company home page.</h2>
	<hr>
	<p>Welcome to the LoveToCode company .</p>

	<hr>
	<!-- Display user names and roles. -->
	<p>
		<!-- Display's the user Id currently logged in -->
		User:
		<security:authentication property="principal.username" />
		<br> <br>
		<!-- Display user roles currently logged in -->
		Role(s):
		<security:authentication property="principal.authorities" />
	</p>
	<hr>
	<security:authorize access="hasRole('Manager')">
		<!-- link for leaders  for managers-->
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership
				meeting</a>(only for managers)
		</p>
	</security:authorize>
	
	<security:authorize access="hasRole('Admin')">
		<!-- link for systems for admins -->
		<p>
			<a href="${pageContext.request.contextPath}/system"> IT systems
				meeting.</a>(only for admins)

		</p>
	</security:authorize>
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>