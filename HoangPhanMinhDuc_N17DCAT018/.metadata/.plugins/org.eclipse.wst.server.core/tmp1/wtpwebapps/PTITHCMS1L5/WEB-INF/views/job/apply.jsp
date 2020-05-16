<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <base href="/Lab5/"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Apply</title>
</head>
<body>
  <p>
  	<h3>Thông tin cá nhân</h3>
  	<img src="files/${photo_name}"/>
  	<br>
  	<h3>${param.fullname}</h3>
  </p>
  <p>
  	<h3>Hồ sơ xin việc</h3>
  	<ul>
  		<li>File Name: ${cv_name}</li>
  		<li>File Type: ${cv_type}</li>
  		<li>File Size: ${cv_size}</li>
  	</ul>
  </p>
</body>
</html>