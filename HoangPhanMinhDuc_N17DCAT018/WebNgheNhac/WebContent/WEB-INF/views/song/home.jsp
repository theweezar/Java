<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<div class="news">
	<div class="search-bar">
		<input type="text" placeholder="Search" id="searchBar" class="ip-search"/>
		<button id="searchBtn">Search</button>
	</div>
	<c:if test="${logged }">
		<c:if test="${!empPl }">
			<div id="boxPl" class="box-add-pl">
				<div class="pl">
					<label style="color: whitesmoke" for="boxPl">Chọn playlist: </label>
					<select>
						<!-- 
						<c:forEach var="pl" items="${playList }">
						<option plId="${pl.getId() }" value="${pl.getId() }">${pl.getPlName()}</option>
						</c:forEach>
						 -->
					</select>
				</div>  	
				<div class="add2plBtn">
					<button class="bg-danger" id="closePlBox">Close</button>
					<button class="bg-success" id="add2selectPl">Add</button>
				</div>
			</div>
		</c:if>
		<c:if test="${empPl }">
			<div id="boxPl" class="box-add-pl">
				<h3>Không có playlist cá nhân</h3>
			</div>
		</c:if>
	</c:if>
  <div class="song-list">
    <ul>
      <c:if test="${!emp}">
       <c:forEach var="s" items="${songList}">
       <li>
         <div class="item">
        	<div role="choose" song-id="${s.getSong().getId()}" name="${s.getSong().getSongName() }" singer="${s.getSong().getSingerName() }" link="${s.getSong().getLink() }" class="title">${s.getSong().getSongName()}</div>
        	<div class="singer">By ${s.getSong().getSingerName()}</div>
        	<c:if test="${logged }">
	          <c:if test="${s.isAdded()}">
	          <div style="filter:invert(13%) sepia(92%) saturate(7237%) hue-rotate(360deg) brightness(103%) contrast(106%)" class="loveBtn" role="love">
		        <img src="./public/img/heart-solid.svg"/>
        	  </div>
	          </c:if>
	          
	          <c:if test="${!s.isAdded() }">
	          <div style="filter:invert(99%) sepia(57%) saturate(2%) hue-rotate(25deg) brightness(108%) contrast(100%);" class="loveBtn" role="love">
		        <img src="./public/img/heart-solid.svg"/>
        	  </div>
	          </c:if>
	          
	          <div id="openPl">+ Add to playlist</div>
	         </c:if>
        </div>
       </li>
       </c:forEach>
      </c:if>
    </ul>
  </div>
</div>