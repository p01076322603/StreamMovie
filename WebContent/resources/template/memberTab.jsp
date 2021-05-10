<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <select class="member_select" onchange="location = this.value;">
    <option value="memberInfo.jsp">회원 정보</option>
    <option>작성 게시글</option>
    <option>작성 댓글</option>
  </select>
  <div class="member_tabs">
    <div class="member_profile">
      <c:choose>
        <c:when test="${member.profimg eq 'default_profile.jpg'}">
          <img class="profile_image" src="/memberImagePath/default_profile.jpg" alt="기본 프로필 사진" width="80px" height="80px">
        </c:when>
        <c:otherwise>
          <img class="profile_image" src="/memberImagePath/${member.id}/${member.profimg}" alt="기본 프로필 사진" width="80px" height="80px" onerror="tryAgain(this)">
        </c:otherwise>
      </c:choose>
      <div class="profile_info">
        <div><c:out value="${member.id}"/></div>
        <div class="profile_info_level">Lv. 0</div>
      </div>
    </div>
    <div class="member_points">
      <span><c:out value="${member.point}"/></span>
      <p>points</p>
    </div>
    <div class="member_modify">
      <p><a href="memberModifyPasswordForm">비밀번호 변경</a></p>
      <p><a href="memberVerify">회원정보 변경</a></p>
      <p><a href="memberLeaveForm">탈퇴</a></p>
    </div>
  </div>