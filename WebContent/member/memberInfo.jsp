<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <!DOCTYPE html>
  <html lang="ko">

  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/template.css">
    <link rel="stylesheet" type="text/css" href="resources/css/navTemplate.css">
    <link rel="stylesheet" type="text/css" href="resources/css/memberTab.css">
    <link rel="stylesheet" type="text/css" href="resources/css/memberInfo.css">
    <title>Stream Movie</title>
    <script>
      function tryAgain(e) {
        setTimeout(reloadImg, 1000, e);
      }
      function reloadImg(e) {
        var source = e.src;
        e.src = source;
      }
     </script>
  </head>

  <body>
    <%@ include file="../resources/template/header.jsp" %>
      <div id="container">
        <div id="nav_container">
          <%@ include file="../resources/template/memberTab.jsp" %>
          <div class="member_menu">
            <span class="member_current_page"><a href="">회원 정보</a></span>
            <span><a href="">작성 게시글</a></span>
            <span><a href="">작성 댓글</a></span>
          </div>
          <table class="member_info">
            <tr>
              <th>아이디</th>
              <td><c:out value="${member.id}"/></td>
            </tr>
            <tr>
              <th>닉네임</th>
              <td><c:out value="${member.nickname}"/></td>
            </tr>
            <tr>
              <th>이메일 주소</th>
              <td><c:out value="${member.email}"/></td>
            </tr>
            <tr>
              <th>서명</th>
              <td class="info_signature">
                <c:choose>
                  <c:when test="${not empty member.signature}">
                    ${member.signature}
                  </c:when>
                  <c:otherwise>
                    ---
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
            <tr>
              <th>프로필 사진</th>
              <td>
                <c:choose>
                  <c:when test="${member.profimg eq 'default_profile.jpg'}">
                    <img src="/memberImagePath/default_profile.jpg" alt="기본 프로필 사진" width="80px" height="80px">
                  </c:when>
                  <c:otherwise>
                    <img src="/memberImagePath/${member.id}/${member.profimg}" alt="프로필 사진" width="80px" height="80px" onerror="tryAgain(this)">
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
            <tr>
              <th>회원 그룹</th>
              <td>
                <c:choose>
                  <c:when test="${member.grade eq 0}">
                    정회원
                  </c:when>
                  <c:otherwise>
                    관리자
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
            <tr>
              <th>가입일</th>
              <td>${member.regdate}</td>
            </tr>
            <tr>
              <th>최근 로그인</th>
              <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${member.logdate}"/></td>
            </tr>
          </table>
          <div class="member_menu m_menu2">
            <span>개인 정보</span>
          </div>
          <table class="member_info">
            <tr>
              <th>주소</th>
              <td>
                <c:choose>
                  <c:when test="${not empty member.address}">
                    <c:out value="${member.address}"/>
                  </c:when>
                  <c:otherwise>
                    ---
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
            <tr>
              <th>번호</th>
              <td>
                 <c:choose>
                  <c:when test="${not empty member.phone}">
                    <c:out value="${member.phone}"/>
                  </c:when>
                  <c:otherwise>
                    ---
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
          </table>
        </div>
        <%@ include file="../resources/template/nav.jsp" %>
      </div>
      <%@ include file="../resources/template/footer.jsp" %>
  </body>

  </html>