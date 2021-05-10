<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html lang="ko">

  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/template.css">
    <link rel="stylesheet" type="text/css" href="resources/css/memberTab.css">
    <link rel="stylesheet" type="text/css" href="resources/css/memberPwLeaveVerify.css">
    <title>Stream Movie</title>
  </head>

  <body>
    <%@ include file="../resources/template/header.jsp" %>
      <div id="container">
        <%@ include file="../resources/template/memberTab.jsp" %>
          <form action="memberModifyPasswordAction" method="post" onsubmit="return validatePassword();">  
            <div class="password_container">
              <i class="fas fa-lock"></i>
              <p class="password_constraint">비밀번호는 6자리 이상이어야 하며 영문과 숫자를 반드시 포함해야 합니다.</p>
              <input class="current_password" type="password" placeholder="현재 비밀번호" name="currentPassword">
              <input class="new_password" type="password" placeholder="새 비밀번호" name="newPassword">
              <input class="new_password_verify" type="password" placeholder="새 비밀번호 확인" name="newPasswordVerify">
              <p class="validation_pwLeave"></p>
              <div class="buttons">
                <button type="submit" class="submit">등록</button>
                <a href="memberInfo"><button type="button">취소</button></a>
              </div>
            </div>
         </form>
      </div>
      <%@ include file="../resources/template/footer.jsp" %>
      <script src="resources/js/validatePassword.js"></script>
  </body>
  </html>