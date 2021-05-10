<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="resources/css/template.css">
  <link rel="stylesheet" type="text/css" href="resources/css/index.css">

  <title>Stream Movie</title>
</head>
<body>
  <%@ include file="resources/template/header.jsp" %>
  <div id="container">
    <div class="board">
      <section class="board_free">
        <div class="board_title">
          <a href="freeBoard"><p>자유 게시판</p></a>
        </div>
        <div class="board_content">
          <c:forEach items="${freeBoardList}" var="article">
            <a href="board?articleno=${article.articleno}"><p><c:out value="${article.subject}"/></p></a>
          </c:forEach>
        </div>
      </section>
      <section class="board_share">
        <div class="board_title">
          <a href="shareBoard"><p>나눔 게시판</p></a>
        </div>
        <div class="board_content">
          <c:forEach items="${shareBoardList}" var="article">
            <a href="board?articleno=${article.articleno}"><p><c:out value="${article.subject}"/></p></a>
          </c:forEach>
        </div>
      </section>
    </div>
    <section class="movie">
      <div class="board_title"></div>
      <div class="board_content">

      </div>
    </section>
    <section class="movie_board">
      <div class="board_title"></div>
      <div class="board_content">

      </div>
    </section>
  </div>
  <%@ include file="resources/template/footer.jsp" %>
</body>
</html>