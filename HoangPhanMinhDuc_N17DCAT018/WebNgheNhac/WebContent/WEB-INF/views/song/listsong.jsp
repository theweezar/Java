<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<div class="news">
  <div plId="${plId }" isLater="${plIsLater }" class="song-list">
  	<h3 style="margin: 15px 0 0 35px; color: whitesmoke;">${plName}</h3>
    <ul>
      <c:if test="${!emp}">
       <c:forEach var="s" items="${songList}">
       <li>
         <div class="item">
        	<div role="choose" song-id="${s.getSong().getId()}" name="${s.getSong().getSongName() }" singer="${s.getSong().getSingerName() }" link="${s.getSong().getLink() }" class="title">${s.getSong().getSongName()}</div>
        	<div class="singer">By ${s.getSong().getSingerName()}</div>
        	<div class="rmBtn" role="remove-s">
	        	<img src="./public/img/remove-icon.png"/>
       	    </div>
        </div>
       </li>
       </c:forEach>
      </c:if>
      <c:if test="${emp }">
      	<h2>Không có nhạc trong playlist</h2>
      </c:if>
    </ul>
  </div>
</div>