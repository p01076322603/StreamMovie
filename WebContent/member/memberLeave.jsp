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
  <style>
    input[type=password] {
      margin: 20px;
    }
  
    .password_container {
      min-height: 250px;
    }   
  </style>
</head>
<body>
  <%@ include file="../resources/template/header.jsp" %>
  <div id="container">
    <%@ include file="../resources/template/memberTab.jsp" %>
    <form action="memberLeaveAction" method="post">
      <div class="password_container">
        <i class="fas fa-running"></i>
        <p class="password_constraint">사이트에서 탈퇴합니다..</p>
        <input type="password" placeholder="비밀번호" name="leavePassword">
        <div class="buttons">
          <a href="memberInfo"><button type="button">취소</button></a>
          <button type="submit" class="submit">탈퇴</button>
        </div>
      </div>
    </form>
  </div>
  <%@ include file="../resources/template/footer.jsp" %>
</body>
</html>