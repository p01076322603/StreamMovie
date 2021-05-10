<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html lang="ko">

  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/template.css">
    <link rel="stylesheet" type="text/css" href="resources/css/navTemplate.css">
    <link rel="stylesheet" type="text/css" href="resources/css/boardWrite.css">
    <script src="ckeditor/ckeditor.js"></script>
    <script src="ckeditor/sample.js"></script>
    <title>Stream Movie</title>
    <script>
    	function confirmAbort() {
    		if(confirm('게시글 수정을 취소하시겠습니까? 진행된 내용이 사라집니다.')) {
    			history.go(-1);
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
    </script>
  </head>

  <body>
    <%@ include file="../resources/template/header.jsp" %>
      <div id="container">
        <div id="nav_container">
          <div class="boardWrite_header">
            <div class="boardWrite_title">
              <p>게시글 수정</p>
            </div>
          </div>
          <form action="boardModifyAction" method="post" onsubmit="return boardWriteValidate();">
            <input type="hidden" value="${sessionScope.loginMember.id}" name="id">
            <input type="hidden" value="${article.articleno}" name="articleno">
            <div class="writeContent">
              <div class="writeContent_header">
                <p>쓰기</p>
                <div class="buttons">
                  <button type="submit" class="submit">등록</button>
                  <button type="button" class="button" onclick="confirmAbort()">취소</button>
                </div>
              </div>
              <div class="writeContent_title">
                <select class="board_select" name="boardno" onFocus='this.initialSelect = this.selectedIndex;'
                                                            onChange='this.selectedIndex = this.initialSelect;'>
                  <option value="0">게시판 선택</option>
                  <option value="1">영화 정보</option>
                  <option value="2">시사회 정보</option>
                  <option value="3">자유 게시판</option>
                  <option value="4">나눔 게시판</option>
                </select>
                <input class="board_title" type="text" value="${article.subject}" name="subject">
              </div>
              <div class="editor_container">
                <textarea id="editor" class="editor" name="content">${article.content}</textarea>
                <script>
                	initSample();
                </script>
              </div>
              <div class="buttons bottom_btn">
                <button type="submit" class="submit">등록</button>
                <button type="button" class="button" onclick="confirmAbort()">취소</button>
              </div>
            </div>
          </form>
        </div>
        <%@ include file="../resources/template/nav.jsp" %>
      </div>
      <%@ include file="../resources/template/footer.jsp" %>
      <script>var boardno = '${article.boardno}'</script>
      <script src="resources/js/boardWrite.js"></script>
  </body>
  </html>