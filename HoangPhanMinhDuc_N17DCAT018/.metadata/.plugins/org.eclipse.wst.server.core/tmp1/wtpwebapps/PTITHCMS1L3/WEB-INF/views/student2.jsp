<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<h3>SINH VIÊN PTITHCM</h3>
	<form:form action="student/update.htm" modelAttribute="student">
	<div>Họ và tên</div>
	<form:input path="name"/>
	<div>Điểm</div>
	<form:input path="mark"/>
	<div>Chuyên ngành</div>
	<!-- form:select path="major" items="${majors1}"/-->
	<form:select path="major" items="${majors}"
	itemValue=”id” itemLabel=”name”/> 
	<div>
	<button>Cập nhật</button>
	</div>
	</form:form>
</body>
</html>