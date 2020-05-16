<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home Index</title>
  <style>
  	.form-group{
  		margin: 10px;
  	}
  </style>
</head>
<body>
	<h1>Welcome</h1>
 	<div class="form-group">
 		<div>Username</div>
 		<input name="id" value ="${user.username}" />
 	</div>
 	<div class="form-group">
 		<div>Password</div>
 		<input name="id" value ="${user.password}" />
 	</div>
 	<div class="form-group">
 		<button>Login</button>
 	</div>
</body>
</html>