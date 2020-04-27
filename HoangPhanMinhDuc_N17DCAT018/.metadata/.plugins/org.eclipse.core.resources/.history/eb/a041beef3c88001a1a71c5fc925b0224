<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>Id</th>
			<th>Username</th>
			<th>Password</th>
			<th>Email</th>
		</tr>
		<c:forEach var="u" items="${list}">
		<tr>
			<td>${u.getId()}</td>
			<td>${u.getUsername()}</td>
			<td>${u.getPassword()}</td>
			<td>${u.getEmail()}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>