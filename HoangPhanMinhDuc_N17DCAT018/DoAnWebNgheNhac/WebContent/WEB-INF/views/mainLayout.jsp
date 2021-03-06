<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${cssLink}.css">
<link rel="stylesheet" href="${bootstrap}.css">
<script src="${jsLink}.js"></script>
<title>Layout</title>
</head>
<body>
	<div class="conTainer">
    <div class="left-ct">
      <div class="title">.navigation</div>
      <ul class="navi">
        <li class="navi-item">
          <a href="./home.htm">Home</a>
        </li>
        <li class="navi-item">
          <a href="#">top 100</a>
        </li>
        <li class="navi-item">
          <a href="#">quốc gia</a>
        </li>
      </ul>
    </div>
    <div style="padding: 0; margin: 0;" class="right-ct">
      <jsp:include page="${render}.jsp"></jsp:include>
      <div class="infor">
        <div class="log-nav">
          <ul class="ul-parent">
          	<c:if test="${!logged}">
          	<li class="li-parent">
              <a href="./account.htm?m=register">Đăng ký</a>
            </li>
            <li class="li-parent">
              <a href="./account.htm?m=login">Đăng nhập</a>
            </li>
          	</c:if>
          	<c:if test="${logged }">
          	<li id="acc-nav" class="li-parent">
          	  <div style="width: 100%">
          	  	<div>Hi, ${currUsername }</div>
          	  	<div id="arrow" class="arrow-icon"></div>
          	  </div>
          	  <ul class="ul-c-1">
          	  	<li>
          	  		
          	  	</li>
          	  	<li>
          	  		<a href="#">Đổi mật khẩu</a>
          	  	</li>
          	  	<li>
          	  		<a href="./playlist.htm">Nhạc cá nhân</a>
          	  	</li>
          	  	<li>
          	  		<a href="./logout.htm">Thoát</a>
          	  	</li>
          	  </ul>
          	</li>
          	<li class="li-parent">
          	  <a href="./upload.htm">Tải lên</a>
          	</li>
          	</c:if>
          </ul>
        </div>

        <div class="pl-nav">
          <ul>
            <li style="text-transform: uppercase; color: whitesmoke;">My PLaylist</li>
            
          </ul>
        </div>
      </div>
    </div>
  </div>
  <audio controls style="display: none;">
    <source src=""  type="audio/mp3">
  </audio>
  <div class="audio-nav">
    <div class="song-infor">
      <div class="name">Test DRIVE</div>
      <div class="singer">JoJI</div>
    </div>
    <div class="control">
      <div class="play-ct">
        <div id="play" role="pausing">Play</div>
      </div>
      <div class="timeline">
        <div id="current-time" class="">0:00</div>
        <div id="length" class="line">
          <div id="current" class="current"></div>
        </div>
        <div id="duration" class="">0:00</div>
      </div>
    </div>
  </div>
  
  <script src="./public/js/script.js"></script>
</body>
</html>