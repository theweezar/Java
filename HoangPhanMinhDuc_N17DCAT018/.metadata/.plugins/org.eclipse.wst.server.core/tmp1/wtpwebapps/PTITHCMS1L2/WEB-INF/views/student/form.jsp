<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="student.htm" method="post">
		<div>Họ và tên</div>
		<input name="name">
		<div>Điểm trung bình</div>
		<input name="mark">
		<div>Chuyên ngành</div>
		<label>
			<input name="major" type="radio" value="APP">
			Ứng dụng phần mềm
		</label>
		<label>
			<input name="major" type="radio" value="WEB">
			Thiết kế web
		</label>
		<button type="submit">Lưu</button>
	</form>
</body>
</html>