<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="resources/css/template.css">
  <link rel="stylesheet" type="text/css" href="resources/css/navTemplate.css">
  <link rel="stylesheet" type="text/css" href="resources/css/boardView.css">
  <link rel="stylesheet" type="text/css" href="resources/css/freeboard.css">
  <link rel="stylesheet" type="text/css" href="resources/css/pagination.css">
  <title>Stream Movie</title>
  <script>
	function articleDelete() {
  		if(confirm("게시글을 삭제하시겠습니까?")) {
  			const xhr = new XMLHttpRequest();
  			xhr.open("POST", 'boardDelete', true);
  			xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
  			xhr.send('articleno=${article.articleno}&id=${article.id}');
  			alert("게시글을 삭제하였습니다.");
  			location.href="freeBoard";
  		}
  	}

   	function articleModify() {
		const articleForm = document.querySelector("#boardModify");
		articleForm.method = 'post';
		articleForm.action = 'boardModify';
		
		const content = $(".boardView_content").html();
		const input = document.createElement('input');
		input.setAttribute('type', 'hidden');
		input.setAttribute('name', 'content');
		input.setAttribute('value', content);
		
		articleForm.appendChild(input);
		articleForm.submit();
	}
   	
   	function writeReply() {
		const articleForm = document.querySelector("#boardModify");
		articleForm.method = 'post';
		articleForm.action = 'boardReply';	
		articleForm.submit();
   	}
  </script>
</head>
<body>
  <%@ include file="../resources/template/header.jsp" %>
  <div id="container">
    <div id="nav_container">
    <form action="" id="boardModify">
      <input type="hidden" value="${article.articleno}" name="articleno">
      <input type="hidden" value="${article.id}" name="id">
      <input type="hidden" value="${article.subject}" name="subject">
      <input type="hidden" value="${article.boardno}" name="boardno">
      <input type="hidden" value="${article.ref}" name="ref">
      <input type="hidden" value="${article.restep}" name="restep">
      <input type="hidden" value="${article.relevel}" name="relevel">
    </form>
      <div class="boardView_header">
        <a href="freeBoard"><p>자유 게시판</p></a>
      </div>
      <div class="boardView_container">
        <div class="boardView_title">
          <p><c:out value="${article.subject}"/></p>
        </div>
        <div class="boardView_info">
          <div class="info_left">
            <span class="info_id"><c:out value="${article.id}"/></span>
            <span class="info_regdate"><fmt:formatDate value="${article.regdate}" pattern="yy.MM.dd."/></span>
          </div>
          <div class="info_right">
            <span class="info_readcount"><i class="fas fa-eye" style="display: inline;"></i><c:out value=" ${article.readcount}"/></span>
              <c:if test="${sessionScope.loginMember.id == article.id}">
                <span class="info_modify" onclick="articleModify();">수정</span>
                <span class="info_delete" onclick="articleDelete();">삭제</span>
              </c:if>
          </div>
        </div>
        <div class="boardView_content">
          ${article.content}
        </div>
        <div class="boardView_footer">
          <span class="boardList" onclick="location.href='freeBoard'">목록으로</span>
          <c:if test="${not empty sessionScope.loginMember}">
            <span class="write_reply" onclick="writeReply();">댓글 작성</span>
          </c:if>
        </div>
      </div>
    </div>
    <%@ include file="../resources/template/nav.jsp" %>
  </div>
  <%@ include file="../resources/template/footer.jsp" %>
</body>
</html>