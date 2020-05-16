<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Xin viec</title>
  <style>
  	.form-group{
  		margin: 10px;
  	}
  </style>
</head>
<body>
  <h1>NỘP ĐƠN XIN VIỆC</h1>
  ${message}
  <form action ="../job/apply.htm" method="POST" enctype="multipart/form-data">
  	<div class="form-group">
  		<div>Họ và tên ứng viên</div>
  		<input type="text" name="fullname"/>
  	</div>
  	<div class="form-group">
  		<div>Hình ảnh</div>
  		<input type="file" name="photo"/>
  	 </div>
  	<div class="form-group">
  		<div>Hồ sơ xin việc</div>
  		<input type="file" name="cv"/>
  	</div>
  	<div class="form-group">
  		<button type="submit">Send</button>
  	</div>
  </form> 
</body>
</html>