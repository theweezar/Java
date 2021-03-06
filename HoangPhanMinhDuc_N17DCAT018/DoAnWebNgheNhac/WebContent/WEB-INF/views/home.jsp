<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<div class="news">
  <div class="song-list">
    <ul>
      <li>
        <div class="title">Title</div>
        <div class="singer">Singer</div>
        <div class="duration">Duration</div>
      </li>
      <li>
        <div class="title">Test DRIVE</div>
        <div class="singer">JoJI</div>
        <div class="duration">3:47</div>
        <div class="love">Love</div>
      </li>
      <li>
        <div class="title">Circle</div>
        <div class="singer">Post Malone</div>
        <div class="duration">4:10</div>
        <div class="love">Love</div>
      </li>
      <c:if test="${!emp}">
       <c:forEach var="s" items="${songList}">
       <li>
         <div role="choose" song-id="${s.getSong().getId()}" link="${s.getSong().getLink() }" class="title">${s.getSong().getSongName()}</div>
         <div class="singer">${s.getSong().getSingerName()}</div>
         <div class="duration">4:10</div>
         <c:if test="${logged }">
          <c:if test="${s.isAdded()}">
          <div style="filter:invert(13%) sepia(92%) saturate(7237%) hue-rotate(360deg) brightness(103%) contrast(106%)" class="love" id="loveBtn" role="love">
          	<img src="./public/img/heart-solid.svg"/>
          </div>
          </c:if>
          
          <c:if test="${!s.isAdded() }">
          <div style="filter:invert(99%) sepia(57%) saturate(2%) hue-rotate(25deg) brightness(108%) contrast(100%);" class="love" id="loveBtn" role="love">
          	<img src="./public/img/heart-solid.svg"/>
          </div>
          </c:if>
          <div role="add-to-pl" class="text-primary">
          	<select id="">
          		<option>pl1</option>
          		<option>pl2</option>
          	</select>
          </div>
         </c:if>
       </li>
       </c:forEach>
      </c:if>
    </ul>
  </div>
</div>