<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	table{
		border-collapse: collapse;
		width: 100%
	}
	td, td{
		line-height: 25px;
		border: 1px solid black;
		padding: 5px;
	}
	th{
		backgroud-color: gray;
	}
</style>
</head>
<body>
	<h1>EL & JSTL</h1>
	<table>
		<tr>
			<th>Tên SP</th>
			<th>Giá cũ</th>
			<th>Giảm giá</th>
			<th>Giá mới</th>
		</tr>
		<c:forEach var="p" items="${prods}">
			<tr>
				<td>${p.name}</td>
				<td>${p.untiPrice}</td>
				<td><f:formatNumber value="${p.discount}" type="percent"/></td>
				<td><f:formatNumber value="${p.getNewPrice()}" type="currency"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>