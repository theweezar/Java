<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="./public/css/account.css">
<link rel="stylesheet" href="./public/css/bootstrap.css">
</head>
<body>
	<div class="wallpp"></div>
	<c:if test="${mode == 1}">
		<div class="login-page">
			<h3>.Login</h3>
			<form:form action="./login.htm" method="POST" modelAttribute="user">
				<div>
					<form:input placeholder="Username" path="username"/>
				</div>
				<div>
					<form:password placeholder="Password" path="password"/>
				</div>
				<div>
					<form:button type="submit">Login</form:button>
				</div>
			</form:form>
		</div>
	</c:if>
	<c:if test="${mode == 2}">
		<div class="register-page">
			<h3>.Register</h3>
			<form:form action="./register.htm" method="POST" modelAttribute="user">
				<div>
					<form:input placeholder="Username" path="username"/>
				</div>
				<div>
					<form:password placeholder="Password" path="password"/>
				</div>
				<div>
					<input name="rePassword" placeholder="Re password" type="password"/>
				</div>
				<div>
					<form:input placeholder="Email" path="email"/>
				</div>
				<div>
					<form:button type="submit">Submit</form:button>
				</div>
			</form:form>
		</div>
	</c:if>
</body>
</html>