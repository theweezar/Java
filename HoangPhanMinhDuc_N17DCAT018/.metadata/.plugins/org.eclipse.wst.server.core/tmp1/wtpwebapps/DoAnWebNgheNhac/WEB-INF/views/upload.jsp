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
	</form>
</div>