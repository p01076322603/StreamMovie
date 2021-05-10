<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html lang="ko">

  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/template.css">
    <link rel="stylesheet" type="text/css" href="resources/css/navTemplate.css">
    <link rel="stylesheet" type="text/css" href="resources/css/search.css">
    <title>Stream Movie</title>
  </head>

  <body>
    <%@ include file="resources/template/header.jsp" %>
      <div id="container">
        <div id="nav_container">
          <h1 class="search_title">통합 검색</h1>
          <input type="text" class="search_box"><button type="button" class="search_button">검색</button>
          <ul class="search_tabs">
            <li>통합 검색</li>
            <li>게시글</li>
            <li>댓글</li>
            <li>영화</li>
          </ul>
          <div class="search_results">
            <div class="search_result_board">
              <p class="search_div_title">게시글</p>
              <ul>
                <li class="search_board_title"><a href="#"><p>(속보) 손흥민 골!!</p></a></li>
                <li class="search_author"><a href="#">자유 게시판</a></li>
                <li class="search_author">Supervicon</li>
                <li class="search_author">2018-06-24</li>
                <li class="search_author search_author_view">조회 140</li>
                <li class="search_board_content"><br>
                  <p>
                  ㅁㄴㅇㅁㄴㅇㅁㅇ
                  </p>
                </li>
              </ul>
              <ul>
                <li class="search_board_title"><a href="#"><p>노매드랜드 2회차 후기</p></a></li>
                <li class="search_author"><a href="#">영화 정보</a></li>
                <li class="search_author">비단장수</li>
                <li class="search_author">2021-04-22</li>
                <li class="search_author search_author_view">조회 43</li>
                <li class="search_board_content"><br>
                  <p>
                    노매드랜드를 라이브톡으로 처음 보고난 후 다시 한번 보고싶었는데 2주차 엽서도 너무 예뻐서 받을겸 관람했습니다 확실히 라톡이후에 들었던 것을 곱씹으면서 보니까 좀 더 영화가 이해됬고 눈에 들어오는 것도 많아서 너무 재밌게봤습니다! (근데 잔잔해서 졸음이 밀려오는건 어쩔수 없나봐요.....ㅠㅠ) + 아트패키지도 압구정에서 뽑아봤는데 너무 만족스럽게 굿즈 구하고있던 작은아씨들 오디언스킷이 딱!!! 나와줘서 너무 좋았네요 ㅠㅠ 오늘도 기분좋게 잠들수 있을듯 합니다^^
                  </p>
                </li>
              </ul>
            </div>
            <hr>
            <div class="search_result_comment">
              <p class="search_div_title">댓글</p>
              <ul>
                <li class="search_comment_title">
                  <a href="#"><p>
                      둘중 하나가 텀을 더 둘수도 있겠네요
                    </p></a>
                </li>
                <li class="search_author"><a href="#">자유 게시판</a></li>
                <li class="search_author">ipanema</li>
                <li class="search_author">2021-04-22</li>
              </ul>
              <ul>
                <li class="search_comment_title">
                  <a href="#"><p>
                      서복은 배우들 팬이라면 볼만 하겠지만.. 뭐 딱히 이점이 없는 영화 같았어요 게다가 뭐 티빙으로도 볼 수 있으니... 김강우배우 그러고보니 실종느와르M이라는 드라마 뒤늦게 봤었는데.. 몸도 잘 쓰는 배우라 이런쪽으로 더 흥했으면 좋겠어요
                    </p></a>
                </li>
                <li class="search_author"><a href="#">영화 정보</a></li>
                <li class="search_author">나름</li>
                <li class="search_author">2021-04-22</li>
              </ul>
            </div>
            <hr>
            <div class="search_result_movie">
              <p class="search_div_title">영화</p>
            </div>
          </div>
        </div>
        <%@ include file="resources/template/nav.jsp" %>
      </div>
      <%@ include file="resources/template/footer.jsp" %>
  </body>

  </html>