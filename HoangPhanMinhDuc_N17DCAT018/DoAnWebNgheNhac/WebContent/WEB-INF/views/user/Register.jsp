<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:if test="${!logged }">
<div id="sign" class="sign" style="display: none">
	<div id="blackglass" class="blackglass">
		<div id="loginForm" class="login-form" style="display: none">
			<div id="closeBtn1" class="close-btn">Close</div>
			<form action="./login.htm" method="post">
				<div class="ip-g">
					<input placeholder="Username" name="username" type="text">
				</div>
				<div class="ip-g">
					<input placeholder="Password" name="password" type="password">
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