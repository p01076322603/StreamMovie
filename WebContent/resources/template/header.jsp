<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <header>
    <div id="mobile_menu_icon">
      <a href="#"><i class="fas fa-bars"></i></a>
    </div>
    <div id="mobile_menu">
      <div class="mobile_dimmed off"></div>
      <div class="mobile_slide">
        <c:if test="${!empty sessionScope.loginMember}">
          <span>${sessionScope.loginMember.nickname}님 안녕하세요!</span>
        </c:if>  
        <div class="mobile_buttons">
          <c:choose>
             <c:when test="${empty sessionScope.loginMember}">
              <div class="mobile_buttons_logout">
                <button id="bg_red" class="mobile_account_logout1">로그인</button>
                <a href="register"><button>회원 가입</button></a>
              </div>
             </c:when>
             <c:otherwise>
              <div class="mobile_buttons_login">
                <a href="memberInfo"><button id="bg_red">내 정보</button></a>
                <a href="logout"><button>로그 아웃</button></a>
              </div>
             </c:otherwise>
           </c:choose>
        </div>
        <div class="mobile_search">
          <form action="search" method="get" onsubmit="return mobileSearchForm();">
            <input type="text" class="mobile_search_box" placeholder="검색어를 입력하세요">
          </form>
          <ul>
            <li class="mobile_menu_list"><a href="#">영화 정보</a></li>
            <li class="mobile_menu_list"><a href="#">시사회 정보</a></li>
            <li class="mobile_menu_list"><a href="freeBoard">자유 게시판</a></li>
            <li class="mobile_menu_list"><a href="shareBoard">나눔 게시판</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="logo">
      <a href="/StreamMovie"><img src="resources/image/Logo.jpg" alt="Logo" width="130px"></a>
    </div>
    <div id="menu">
      <ul>
        <li class="menu_list"><a href="#">영화 정보</a></li>
        <li class="menu_list"><a href="#">시사회 정보</a></li>
        <li class="menu_list menu_hide"><a href="freeBoard">자유 게시판</a></li>
        <li class="menu_list menu_hide"><a href="shareBoard">나눔 게시판</a></li>
      </ul>
      <form action="search" method="get" onsubmit="return searchForm();">
        <div class="search search_hide">
          <label>
            <i class="fas fa-search"></i>
            <input class="search_input" type="text">
          </label>
        </div>
      </form>
    </div>
    <div id="account">
      <c:choose>
        <c:when test="${empty sessionScope.loginMember}">
          <div class="account_logout">
            <ul>
              <li class="account_list"><button class="js-login">로그인</button></li>
              <li class="account_list"><a href="register"><button class="register">회원가입</button></a></li>
            </ul>
          </div>
        </c:when>
        <c:otherwise>
          <div class="account_login">
            <ul>
              <li class="account_list"><a href="memberInfo"><button>내 정보</button></a></li>
              <li class="account_list"><a href="logout"><button>로그아웃</button></a></li>
            </ul>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
    <div id="mobile_account">
      <c:choose>
        <c:when test="${empty sessionScope.loginMember}">
          <div class="mobile_account_logout2">
            <label>
              <a href="#"><i class="fas fa-power-off"></i></a>
              <p>로그인</p>
            </label>
          </div>
        </c:when>
        <c:otherwise>
          <div class="mobile_account_login">
            <label>
              <a href="memberInfo">
                <i class="fas fa-user"></i>
                <p>내 정보</p>
              </a>
            </label>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
    <%@ include file="../template/modalLogin.jsp" %>
    <script src="resources/js/mobileMenu.js"></script>
    <script>
    	function searchForm() {
    		if ($.trim($(".search_input").val()) == "") {
    			return false;
    		}
    	}
		function mobileSearchForm() {
    		if ($.trim($(".mobile_search_box").val()) == "") {
    			return false;
    		}
		}    	
    </script>
  </header>