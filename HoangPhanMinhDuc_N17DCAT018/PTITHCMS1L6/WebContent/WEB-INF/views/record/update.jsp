<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>UPDATE RECORD</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	${message }
	<form:form action="./record/update.htm" modelAttribute="record">
		<div>
			<label>id</label>
			<form:input path="id" />
		</div>
		<div>
			<label>Nhân viên</label>
			<form:select path="staff.id" items="${staffs }" itemValue="id"
				itemLabel="name" />
		</div>
		<div>
			<label>Loại</label>
			<form:radiobutton path="type" value="1" label="Thành tích" />
			<form:radiobutton path="type" value="0" label="Kỷ luật" />
		</div>
		<div>
			<label>Lý do</label>
			<form:textarea path="reason" row="3" />
		</div>
		<div>
			<button>UPDATE</button>
		</div>
	</form:form>
</body>
</html>