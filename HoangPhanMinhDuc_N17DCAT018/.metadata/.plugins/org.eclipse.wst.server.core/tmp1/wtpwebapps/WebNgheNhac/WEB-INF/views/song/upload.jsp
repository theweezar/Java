<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="b-f-upl">
	<form action="./upload.htm" method="post" enctype="multipart/form-data">
		<div>
			<input style="color: white" type="file" name="uplSong"/>
		</div>
		<div>
			<input class="ip-t" placeholder="Tên bài hát" type="text" name="songName"/>
		</div>
		<div>
			<input class="ip-t" placeholder="Tên ca sĩ" type="text" name="singerName"/>
		</div>
		<div>
			<input class="ip-t" placeholder="Tên nhạc sĩ" type="text" name="musicianName"/>
		</div>
		<div class="selection"> 
			<select id="kindSelect" name="kind">
				<c:forEach var="k" items="${kind}">
					<option value="${k.getId()}">${k.getKindName()}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<button class="btn-success" type="submit">Tải lên</button>
		</div>
		<c:if test="${success }">
			<script>alert("Upload successfully")</script>
		</c:if>
		<c:if test="${error }">
			<script>alert("Upload failed")</script>
		</c:if>
	</form>
</div>