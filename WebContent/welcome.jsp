<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="resources/css/template.css">
  <title>Stream Movie</title>
</head>
<body>
  <%@ include file="resources/template/header.jsp" %>
  <div id="container">
    <%@ include file="resources/template/modalWelcome.jsp" %>
  </div>
  <%@ include file="resources/template/footer.jsp" %>
  <script>
    document.querySelector(".modal_hide").classList.remove("modal_hide");
    document.querySelector("#modal_dimmed").addEventListener("click", function() {
      window.location.href='/StreamMovie';
    });
  </script>
</body>
</html>