<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Manager</title>
</head>
<body>
	<form action="student-mgr.htm" method="post">
		${message}
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
		<hr>
		<button name="btnInsert">Thêm</button>
		<button name="btnUpdate">Cập nhật</button>
		<button name="btnDelete">Xóa</button>
		<button name="btnReset">Nhập lại</button>
	</form>
	<hr>
	<table border="1" style="width:100%">
		<tr>
			<th>Họ và tên</th>
			<th>Điểm TB</th>
			<th>Chuyên ngành</th>
			<th></th>
		</tr>
		<tr>
			<td>Lê Phạm Tuấn Kiệt</td>
			<td>8.5</td>
			<td>CNTT</td>
			<td><a href="student-mgr.htm?lnkEdit">Sửa</a></td>
		</tr>
	</table>
</body>
</html>