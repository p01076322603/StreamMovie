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
      min-height: 275px;
    }   
  </style>
</head>
<body>
  <%@ include file="../resources/template/header.jsp" %>
  <div id="container">
    <%@ include file="../resources/template/memberTab.jsp" %>
    <div class="password_container">
      <i class="fas fa-shield-alt"></i>
      <p class="password_constraint">회원의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인 합니다.</p>
      <form action="memberModifyForm" method="post">
        <input type="password" placeholder="비밀번호" name="pwd">
        <div class="buttons">
          <button type="submit" class="submit">확인</button>
          <a href="memberInfo"><button type="button">취소</button></a>
        </div>
      </form>
    </div>
  </div>
  <%@ include file="../resources/template/footer.jsp" %>
</body>
</html>