<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>INDEX</title>
<base href="${pageContext.servletContext.contextPath }/">
</head>
<body>
	<table class="table table-hover">
		<tr>
			<th>Username</th>
			<th>Password</th>
			<th>Fullname</th>
			<th></th>
			<c:forEach var="u" items="${users }">
				<tr>
					<td>${u.username }</td>
					<td>${u.password }</td>
					<td>${u.fullname }</td>
					<td><a href="./user/delete.htm">Delete</a></td>
				</tr>
			</c:forEach>
		</tr>
	</table>
</body>
</html>