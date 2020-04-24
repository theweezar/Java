<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student</title>
</head>
<body>
	<form action="student/update.htm" method="post">
		<div>Họ và tên</div>
		<input name="name" value="${student.name }"/>
		<div>Điểm trung bình</div>
		<input name="mark" value="${student.mark }"/>
		<div>Chuyên ngành</div>
		<label>
			<input name="major" type="radio" value="APP"
			${student.major == 'APP' ? 'checked':'' }/>
			Ứng dụng phần mềm
		</label>
		<label>
			<input name="major" type="radio" value="WEB"
			${student.major == 'WEB' ? 'checked':'' }/>
			Thiết kế trang web
		</label>
		<hr>
		<button>Cập nhật</button>
	</form>
</body>
</html>