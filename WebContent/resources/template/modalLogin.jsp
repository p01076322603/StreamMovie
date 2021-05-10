<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="resources/css/modalLogin.css">
  <div id="modal" class="modal_hide">
    <div id="modal_dimmed"></div>
    <div id="modal_container">
      <div class="container_title">
        <h3>로그인</h3>
      </div>
      <div>
      <form id="modal_form">
        <input type="text" placeholder="아이디" name="id" class="login_id"> 
        <input type="password" placeholder="비밀번호" name="pwd" class="login_pwd">
        <div class="modal_find">
          <label><input type="checkbox"> 로그인 유지</label>
          <p><a href="#">ID/PW 찾기</a></p>
        </div>
        <p class="log_ajax_result"></p>
        <div id="modal_buttons">
          <a href="register"><button type="button" class="modal_register">회원가입</button></a>
          <button type="button" class="modal_submit">로그인</button>
        </div>
      </form>
      </div>
    </div>
  </div>
  <script src="resources/js/modalLogin.js"></script>
  <c:if test="${empty sessionScope.loginMember}"><script>init();</script></c:if>