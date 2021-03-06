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
<title>Music</title>
</head>
<body>
	<div class="conTainer">
    <div class="left-ct">
      <div class="title">.navigation</div>
      <ul class="navi">
        <li class="navi-item">
          <a href="./home.htm">
          	Home
          	<c:if test="${logged }">(${userName })</c:if>
          </a>
        </li>
        <c:if test="${logged }">
        <li class="navi-item">
          <a href="./playlist.htm">playlist</a>
        </li>
       	<li class="navi-item">
          <a href="./upload.htm">upload</a>
        </li>
        <li class="navi-item">
          <a href="./changepw.htm">Change password</a>
        </li>
        <li class="navi-item">
          <a href="./logout.htm">Logout</a>
        </li>
        </c:if>
        <c:if test="${!logged }">
        <li class="navi-item">
          <a href="./account.htm?m=login">Login</a>
        </li>
        <li class="navi-item">
          <a href="./account.htm?m=register">Register</a>
        </li>
        </c:if>
      </ul>
    </div>
    <div style="padding: 0; margin: 0;" class="right-ct">
      <jsp:include page="${render}.jsp"></jsp:include>
      <div class="infor">

        <div class="pl-nav">
        <c:if test="${logged }">
        <div style="color: whitesmoke; width: 90px; font-size:110%;">Like</div>
        </c:if>
          
          <ul id="lovePlaylist">
          	<!-- 
            <li>
            	<div class="item">
            		<div class="play-icon">
            			<img src="./public/img/play.png"/>
            		</div>
            		<div class="infor">
            			<div class="name">Song name</div>
            			<div class="singer">By who</div>
            		</div>
            	</div>
            </li>
             -->
            <c:if test="${logged }">
            	<c:forEach var="s" items="${lovePl}">
            	<li>
	            	<div name="${s.getSong().getSongName() }" singer="${s.getSong().getSingerName() }" role="choose" songId="${s.getSong().getId()}" class="item" link="${s.getSong().getLink() }">
	            		<div class="play-icon">
	            			<img src="./public/img/play.png"/>
	            		</div>
	            		<div class="infor">
	            			<div class="name">${s.getSong().getSongName() }</div>
	            			<div class="singer">${s.getSong().getSingerName() }</div>
	            		</div>
	            	</div>
	            </li>
            	</c:forEach>
            </c:if>
             
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
      <div id="audioName" class="name"></div>
      <div id="audioSinger" class="singer"></div>
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