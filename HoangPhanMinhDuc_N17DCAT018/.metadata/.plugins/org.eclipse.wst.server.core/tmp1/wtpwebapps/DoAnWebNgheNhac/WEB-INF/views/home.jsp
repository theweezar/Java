<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<h2>Đây là file home</h2>

<ul>
<c:forEach var="s" items="${songList}">
	<li>
		<div class="song-infor">
			<ul song-id="${s.getId()}" role="play" link="${s.getLink()}">
				<li>${s.getSongName()}</li>
			</ul>
		</div>
	</li>
</c:forEach>
</ul>
