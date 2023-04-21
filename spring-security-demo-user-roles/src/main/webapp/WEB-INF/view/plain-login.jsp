<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>custom login</title>
<style type="text/css">
.failed {
	color: red;
}
</style>
</head>
<body>
	<h3>My custom login page.</h3>

	<form:form action="${pageContext.request.contextPath}/authenticateUser"
		method="POST">
		<!-- Check for login error -->
		<c:if test="${param.error != null }">
			<i class="failed"> Sorry you entered invalid username/password.</i>
		</c:if>
		<P>
			User name: <input type="text" name="username" />
		</P>

		<p>
			Password: <input type="password" name="password" />
		</p>
		<input type="submit" value="Login" />
	</form:form>
</body>
</html>