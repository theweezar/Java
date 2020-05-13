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
            <c:forEach var="s" items="${songList}">
            <li>
              <div role="choose" song-id="${s.getSong().getId()}" link="${s.getSong().getLink() }" class="title">${s.getSong().getSongName()}</div>
              <div class="singer">${s.getSong().getSingerName()}</div>
              <div class="duration">4:10</div>
              <c:if test="${logged }">
              <div class="love" id="loveBtn" role="love">
              	<img src="./public/img/heart-solid.svg"/>
              </div>
              </c:if>
            </li>
            </c:forEach>
          </ul>
        </div>
      </div>