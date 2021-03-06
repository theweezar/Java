<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="./public/css/account.css">
<link rel="stylesheet" href="./public/css/bootstrap.css">
<script src="./public/js/jquery.min.js"></script>
</head>
<body>
	<div class="wallpp"></div>
	<c:if test="${mode == 1}">
		<div class="account-page">
			<div class="title">Sign In</div>
			<form:form action="./login.htm" method="POST" modelAttribute="user">
				<div class="ip-g">
					<form:input class="ip" placeholder="Username" path="username"/>
					<form:errors class="err" path="username"/>
				</div>
				<div class="ip-g">
					<form:password class="ip" placeholder="Password" path="password"/>
					<form:errors class="err" path="password"/>
				</div>
				<div class="ip-g">
					<form:button type="submit">Login</form:button>
				</div>
				<table class="option">
					<tr>
						<td><a href="./forgetpw.htm">*Quên mật khẩu</a></td>
						<td style="text-align: right;"><a href="./account.htm?m=register">Đăng ký</a></td>
					</tr>
				</table>
			</form:form>
		</div>
	</c:if>
	<c:if test="${mode == 2}">
		<div class="account-page">
			<div class="title">Register</div>
			<form:form action="./register.htm" method="POST" modelAttribute="user">
				<div class="ip-g">
					<form:input class="ip" placeholder="Username" path="username"/>
					<form:errors class="err" path="username"/>
				</div>
				<div class="ip-g">
					<form:password class="ip" placeholder="Password" path="password"/>
					<form:errors class="err" path="password"/>
				</div>
				<div class="ip-g">
					<input class="ip" name="rePassword" placeholder="Re password" type="password"/>
				</div>
				<div class="ip-g">
					<form:input class="ip" placeholder="Email" path="email"/>
					<form:errors class="err" path="email"/>
				</div>
				<div style="text-align: center;" class="ip-g">
					<form:button type="submit">Submit</form:button>
				</div>
				<div style="text-align: center;">
					<a href="./account.htm?m=login">Quay về đăng nhập</a>
				</div>
			</form:form>
		</div>
	</c:if>
	<c:if test="${mode == 3 }">
		<div class="account-page">
			<div class="title">Change password</div>
			<form:form action="./changepw.htm" method="POST" modelAttribute="user">
				<div class="ip-g">
					<form:password class="ip" placeholder="Current password" path="password"/>
					<form:errors class="err" path="password"/>
				</div>
				<div class="ip-g">
					<input id="newPassword" class="ip" name="newPassword" placeholder="New password" type="password"/>
				</div>
				<div class="ip-g">
					<input id="rePassword" class="ip" name="rePassword" placeholder="Cofirm" type="password"/>
				</div>
				<div style="text-align: center;" class="ip-g">
					<form:button type="submit">Submit</form:button>
				</div>
				<div style="text-align: center;">
					<a href="./home.htm">Quay về trang chủ</a>
				</div>
				<c:if test="${error1 }">
					<script>
						alert("Không để trống");
					</script>
				</c:if>
				<c:if test="${error2 }">
					<script>
						alert("Nhập sai password mới");
					</script>
				</c:if>
			</form:form>
		</div>
	</c:if>
	<c:if test="${mode == 4 }">
		<div class="account-page">
			<div class="title">Retrieve Password</div>
			<form:form action="./forgetpw.htm" method="POST" modelAttribute="user">
				<div class="ip-g">
					<form:input class="ip" placeholder="Username" path="username"/>
				</div>
				<div class="ip-g">
					<input id="code" class="ip" name="code" placeholder="Code" type="text"/>
				</div>
				<div style="text-align: center;" class="ip-g">
					<button id="sendCode" style="margin-bottom: 10px">Send code</button>
					<form:button type="submit">Submit</form:button>
				</div>
				<div style="text-align: center;">
					<a href="./home.htm">Quay về trang chủ</a>
				</div>
			</form:form>
		</div>
		
	</c:if>
	<c:if test="${mode == 5 }">
		<div class="account-page">
			<div class="title">Retrieve Password</div>
			<form:form action="./confirmchangepw.htm" method="POST" modelAttribute="user">
				<div class="ip-g">
					<form:password class="ip" placeholder="New password" path="password"/>
				</div>
				<div class="ip-g">
					<input id="password" class="ip" name="confirm" placeholder="Confirm" type="password"/>
				</div>
				<div style="text-align: center;" class="ip-g">
					<form:button type="submit">Submit</form:button>
				</div>
				<div style="text-align: center;">
					<a href="./home.htm">Quay về trang chủ</a>
				</div>
			</form:form>
		</div>
		<c:if test="${error3 }">
			<script>alert("Nhập sai mật khẩu")</script>
		</c:if>
	</c:if>
	<script src="./public/js/account.js"></script>
</body>
</html>