<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="b-f-upl">
	<form action="./upload.htm" method="post" enctype="multipart/form-data">
		<div>
			<input type="file" name="uplSong"/>
		</div>
		<div>
			<input placeholder="Tên bài hát" type="text" name="songName"/>
		</div>
		<div>
			<input placeholder="Tên ca sĩ" type="text" name="singerName"/>
		</div>
		<div>
			<input placeholder="Tên nhạc sĩ" type="text" name="musicianName"/>
		</div>
		<div>
			<form:select path="kind" items="${kind}" /> 
		</div>
	</form>
</div>