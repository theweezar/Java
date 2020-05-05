<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${cssLink}.css">
<script src="${jsLink}.js"></script>
<title>Layout</title>
</head>
<body>
	<div class="menu">
		<div class="nav">
			<ul>
				<li><a href="./home.htm">home</a></li>
				<li><a href="#">top 100</a></li>
				<li><a href="#">quốc gia</a></li>
				<li><a href="#">thể loại</a></li>
			</ul>
		</div>
		<div class="searchbar">
			<input type="text" value="" placeholder="Tìm kiếm">
		</div>
		
		<div class="account">
		<c:if test="${!logged}">
			<span id="login">
				đăng nhập
			</span>
			|
			<span id="register">
				đăng ký
			</span>
		</c:if>
		<c:if test="${logged}">
			<span id="logout"><a href="./logout.htm">Thoát</a></span>
			<span><a href="./upload.htm">tải lên</a></span>
		</c:if>
		</div>
		<div>This is file mainLayout.jsp</div>
	</div>
	<!-- ================================================================================ -->
	<c:if test="${!logged }">
	<div id="sign" class="sign" style="display: none">
		<div id="blackglass" class="blackglass">
			<div id="loginForm" class="login-form" style="display: none">
				<div id="closeBtn1" class="close-btn">Close</div>
				<form action="./login.htm" method="post">
					<div class="ip-g">
						<input placeholder="Username" name="username" type="text" value="admin">
					</div>
					<div class="ip-g">
						<input placeholder="Password" name="password" type="password" value="admin">
					</div>
					<button class="subBtn" type="submit">
						Login
					</button>
				</form> 
			</div>
			<div id="registerForm" class="register-form" style="display: none">
				<div id="closeBtn2" class="close-btn">Close</div>
				<form action="./register.htm" method="post">
					<div class="ip-g">
						<input placeholder="Username" name="username" type="text">
					</div>
					<div class="ip-g">
						<input placeholder="Password" name="password" type="text">
					</div>
					<div class="ip-g">
						<input placeholder="Confirm Password" name="rePassword" type="text">
					</div>
					<div class="ip-g">
						<input placeholder="Email" name="email" type="text">
					</div>
					<button class="subBtn" type="submit">
						Sign up
					</button>
				</form>
			</div>
		</div>
	</div>
	</c:if>
	<!-- ============================================================ -->
	<jsp:include page="${render}.jsp"/>
	<!-- ============================================================ -->	
	<div class="a-player">
		<audio id="audio" controls>
			<source id="musicSrc" src type="audio/mpeg">
		</audio>
	</div>
	
	<script src="public/js/script.js"></script>
</body>
</html>