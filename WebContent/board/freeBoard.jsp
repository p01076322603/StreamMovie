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
  <link rel="stylesheet" type="text/css" href="resources/css/freeboard.css">
  <link rel="stylesheet" type="text/css" href="resources/css/pagination.css">
  <title>Stream Movie</title>
</head>
<body>
  <%@ include file="../resources/template/header.jsp" %>
  <div id="container">
    <div id="nav_container">
      <div class="freeboard_header">
        <div class="freeboard_title">
          <form action="freeBoard" method="get" id="fbSearchForm" onsubmit="return fbSearchForm();">
            <a href="freeBoard"><p>자유 게시판</p></a>
            <input type="text" class="board_search" placeholder="검색하세요!" name="keyword"> 
            <i class="fas fa-search b_sicon2"></i> 
            <i class="fas fa-search b_sicon"></i>
          </form>
        </div>
        <c:if test="${not empty sessionScope.loginMember}">
          <a href="boardWrite?boardno=3"><i class="fas fa-pen-square"></i></a>
        </c:if>
      </div>
      <c:choose>
        <c:when test="${not empty boardList}">
          <table class="freeboard">
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>글쓴이</th>
              <th>날짜</th>
              <th>조회수</th>
            </tr>
            <c:forEach items="${boardList}" var="article">
              <tr>
                <td><c:out value="${article.boardseq}" /></td>
                <td>
                  <c:choose>
                    <c:when test="${article.relevel > 0}">
                      <c:forEach begin="1" end="${article.relevel}" step="1">
                        <span style="padding-left: 20px;"></span>
                      </c:forEach>
                      <i class="fas fa-reply"></i>
                      <a href="board?articleno=${article.articleno}"><c:out value="${article.subject}" /></a>
                    </c:when>
                    <c:otherwise>
                      <a href="board?articleno=${article.articleno}"><c:out value="${article.subject}" /></a>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td><c:out value="${article.id}" /></td>
                <td><fmt:formatDate value="${article.regdate}" pattern="yy.MM.dd." /></td>
                <td><i class="fas fa-eye"></i><c:out value=" ${article.readcount}" /></td>
              </tr>
            </c:forEach>
          </table>
        </c:when>
        <c:otherwise>    
          <div class="article_notfound">
            <c:choose>
              <c:when test="${not empty keyword}">
                <p>제목 검색 : ' <c:out value="${keyword}"/> '</p>
                <p>결과가 존재하지 않습니다.</p>
              </c:when>
              <c:otherwise>
                <p>게시글이 없습니다.</p>
              </c:otherwise>
            </c:choose>
          </div>
        </c:otherwise>
      </c:choose>
      <c:if test="${not empty boardList}">
        <div class="pagination">
          <c:set var="searchKeyword" value="${not empty keyword ? '&keyword=' : ''}${not empty keyword ? keyword : ''}"/>
          <div class="bt_prev">
            <a class="prev_bt" href="freeBoard?page=${blockNum * paginationCount}<c:out value="${searchKeyword}"/>">
            <i class="fas fa-chevron-left"></i></a>
          </div>
          <div class="bt_page">
            <c:forEach begin="1" end="${paginationCount}" varStatus="status">
              <c:set var="thisPage" value="${blockNum * paginationCount + status.index}"/>
              <c:if test="${thisPage <= pageCount}">
                <a <c:out value="${currentPage == thisPage ? 'class=paging-active' : ''}"/> 
                  href="freeBoard?page=${thisPage}<c:out value="${searchKeyword}"/>">
                  <span>${thisPage}</span>
                </a>
              </c:if>
            </c:forEach>
          </div>
          <div class="bt_next">
            <a class="next_bt" href="freeBoard?page=${(blockNum + 1) * paginationCount + 1}<c:out value="${searchKeyword}"/>">
            <i class="fas fa-chevron-right"></i></a>
          </div>
        </div>
      </c:if>
      <fmt:parseNumber var="lastBlockNum" 
        value="${(pageCount % paginationCount) == 0 ? (pageCount / paginationCount) - 1 : (pageCount / paginationCount)}" integerOnly="true"/>
      <script src="resources/js/boardSearchBox.js"></script>
      <script>
      	function fbSearchForm() {
    		if ($.trim($(".board_search").val()) == "") {
    			return false;
    		}
    	}
        
      	$(".b_sicon2").click(function () {
            if ($(".board_search").val().trim() == "") {
              defocused();
            } else {
        		location.href='freeBoard?keyword=' + $(".freeboard_search").val().trim();
            }
        });
    
      	$(function() {
      		const blockNum = '${blockNum}';
      		if (blockNum === '0') {
				$(".prev_bt").removeAttr('href');
				$(".fa-chevron-left").css("color", "lightgray");
				$(".fa-chevron-left").css("cursor", "not-allowed");
      		}
      		if (blockNum === '${lastBlockNum}' || '${lastBlockNum}' === '-1') {
      			$(".next_bt").removeAttr('href');
				$(".fa-chevron-right").css("color", "lightgray");
				$(".fa-chevron-right").css("cursor", "not-allowed");
      		}
      	});
      </script>
    </div>
    <%@ include file="../resources/template/nav.jsp" %>
  </div>
  <%@ include file="../resources/template/footer.jsp" %>
</body>
</html>