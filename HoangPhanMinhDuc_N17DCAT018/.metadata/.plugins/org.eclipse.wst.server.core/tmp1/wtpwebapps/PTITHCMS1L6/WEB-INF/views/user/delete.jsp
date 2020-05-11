<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>DELETE USER</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<form:form action="./user/delete.htm" modelAttribute="user">
		<div>
			<label>Username</label>
			<form:input path="username" />
		</div>
		<div>
			<button class="btn btn-default">Delete</button>
		</div>
	</form:form>
</body>
</html>