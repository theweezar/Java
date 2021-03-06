<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pl-container">
	<div class='title'>Playlist</div>
	<div class="playlist">
		<table id="playlist">
			<tr>
				<th>Name</th>
			</tr>
			<c:forEach var="pl" items="${playList }">
			<tr pl-id="${pl.getId()}">
				<td class="pl-name"><a href="./playlist.htm?plId=${pl.getId() }">${pl.getPlName() }</a></td>
				<c:if test="${pl.isLater() == 0}">
				<td id="update-pl" class="bg-success">Sửa tên</td>
				<td id="del-pl" role="del-pl" class="bg-danger">Xóa</td>
				</c:if>
			</tr>
			</c:forEach>
			<tr id="add-pl" class="text-white">
				<td class="bg-primary">+ Thêm playlist</td>
			</tr>
		</table>
	</div>
</div>
