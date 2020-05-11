<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>DELETE RECORD</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	${message }
	<form:form action="./record/delete.htm" modelAttribute="record">
		<div>
			<label>id</label>
			<form:input path="id" />
		</div>
		<div>
			<button>DELETE</button>
		</div>
	</form:form>
</body>
</html>