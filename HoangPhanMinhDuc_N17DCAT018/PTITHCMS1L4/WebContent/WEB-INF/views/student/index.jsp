<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	div{
		display: inline-block;
		text-align: center;
		margin: 5px;
		padding: 5px;
		border: 1px solid black;
		border-radius: 5px;
	}
	img{
		width: 150px;
		height: auto;
	}
</style>
</head>
<body>
	<h1>EL & JSTL</h1>
	<div>
		<img src="${photo}">
		<br>
		<strong>${name}</strong>
		<em>${salary*level}</em>
	</div>
	<div>
		<img src="${photo}">
		<br>
		<strong>${name}</strong>
		<em>${salary*level}</em>
	</div>
	<div>
		<img src="${photo}">
		<br>
		<strong>${name}</strong>
		<em>${salary*level}</em>
	</div>
</body>
</html>