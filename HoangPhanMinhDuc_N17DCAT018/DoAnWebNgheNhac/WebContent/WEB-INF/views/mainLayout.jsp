<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${cssLink}.css">
<script src="${jsLink}.js"></script>
<title>Layout</title>
</head>
<body>
	<h1>Layout is here ! (layout.jsp)</h1>
	<jsp:include page="${render}.jsp"/>
	
	<div class="a-player">
		<audio controls>
			<source id="musicSrc" src="" type="audio/mpeg">
		</audio>
	</div>
	
	<script src="public/js/script.js"></script>
	
</body>
</html>