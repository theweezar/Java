<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pl-container">
	<h1 style="color: white">Playlist</h1>
	<div class="playlist">
		<table id="playlist">
			<tr>
				<th>Tên playlist</th>
			</tr>
			<c:forEach var="pl" items="${playList }">
			<tr pl-id="${pl.getId()}">
				<td class="pl-name">${pl.getPlName() }</td>
				<td class="bg-success">Sửa tên</td>
				<td id="del-pl" role="del-pl" class="bg-danger">Xóa</td>
			</tr>
			</c:forEach>
			<tr id="add-pl" class="text-primary">
				<td>+ Thêm playlist</td>
			</tr>
		</table>
	</div>
</div>
