<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script type="text/javascript" src="resources/ckeditorLite/ckeditor.js"></script>
  <link rel="stylesheet" type="text/css" href="resources/css/template.css">
  <link rel="stylesheet" type="text/css" href="resources/css/navTemplate.css">
  <link rel="stylesheet" type="text/css" href="resources/css/memberTab.css">
  <link rel="stylesheet" type="text/css" href="resources/css/memberModify.css">
  <title>Stream Movie</title>
</head>
<body>
  <%@ include file="../resources/template/header.jsp" %>
  <div id="container">
    <div id="nav_container">
      <%@ include file="../resources/template/memberTab.jsp" %>
      <div class="modify_title"><p>회원정보 조회/수정</p></div>
      <div id="modify_form">
        <form action="memberModifyAction" method="post" enctype="multipart/form-data" onsubmit="return memberModifyValidate();">
          <div class="loop_data">
            <ul>
              <li class="modify_list">
                <p>아이디</p>
                <div class="modify_inputbox"><p class="member_id"><c:out value="${member.id}"/></p><br>
                <input type="hidden" value="${member.id}" name="id">
                </div>
              </li>
              <li class="modify_list">
                <p>닉네임</p>
                <div class="modify_inputbox">
                  <input type="text" value="${member.nickname}" name="nickname" class="mdf_nickname" maxlength="15">
                  <p class="validation v_nickname"></p>
                </div>
              </li>
              <li class="modify_list">
                <p>이메일 주소</p>
                <div class="modify_inputbox">
                  <input type="text" value="${member.email}" name="email" class="mdf_email">
                  <p class="validation v_email"></p>
                </div>
              </li>
              <li class="modify_list">
                <p>서명</p>
                <div class="ckeditor_signature">
                  <textarea class="form-control" id="p_content" name="signature">${member.signature}</textarea>
                  <script type="text/javascript">
                    CKEDITOR.replace('p_content');
                  </script>
                </div>
              </li>
              <li class="modify_list modify_other">
                <p>프로필 사진</p>
                  <input type="file" id="new_profimg_input" name="new_profimg" onchange="readURL(this);">
                  <input type="hidden" value="${member.profimg}" name="old_profimg">
                  <span class="prof_img">
                    <c:choose>
                      <c:when test="${member.profimg eq 'default_profile.jpg'}">
                        <img class="old_profimg" src="/memberImagePath/default_profile.jpg" alt="기본 프로필 사진" width="80px" height="80px">
                        <img class="new_profimg" src="" alt="새로운 프로필 사진" width="80px" height="80px">
                        <button type="button" class="profimg_reset">취소</button>
                      </c:when>
                      <c:otherwise>
                        <img class="old_profimg" src="/memberImagePath/${member.id}/${member.profimg}" alt="프로필 사진" width="80px" height="80px">
                        <img class="new_profimg" src="" alt="새로운 프로필 사진" width="80px" height="80px">
                        <button type="button" class="profimg_reset">취소</button>
                      </c:otherwise>
                    </c:choose>
                  </span>
              </li>
              <li class="modify_list modify_other">
                <p>메일링 가입</p>
                <div class="modify_mailing">
                  <label><p>예</p> <input type="radio" name="mailing" class="mailing_y" value="y"></label>
                  <label><p>아니오</p> <input type="radio" name="mailing" class="mailing_n" value="n"></label>
                  <script>
                  	const mailing = "${member.mailing}";
                  	if (mailing === "y") $(".mailing_y").attr("checked", "checked");
                  	else			 	 $(".mailing_n").attr("checked", "checked");          		
                  </script>
                </div>
              </li>
              <li class="modify_list">
                <p>주소</p>
                <div class="modify_address">
                  <div class="address_inputbox">
                    <input type="text" id="sample3_postcode" placeholder="우편번호"      name="addr1" value="${addr1}" readonly>
                    <input type="button" onclick="sample3_execDaumPostcode()" value="우편번호 찾기">
                    <input type="text" id="sample3_address" placeholder="주소"           name="addr2" value="${addr2}" readonly><br>
                    <input type="text" id="sample3_detailAddress" placeholder="상세주소" name="addr3" value="${addr3}">
                    <input type="text" id="sample3_extraAddress" placeholder="참고항목"  name="addr4" value="${addr4}"><br>
                    <p class="validation v_address"></p>
                  </div>
                  <div id="wrap">
                    <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor: pointer; position: absolute; right: 0px; top: -1px; z-index: 1;" onclick="foldDaumPostcode()" alt="접기 버튼">
                  </div>
                </div>
              </li>
              <li class="modify_list">
                <p>전화번호</p>
                <div class="modify_inputbox">
                  <input type="text" placeholder="전화번호" value="${member.phone}" name="phone" class="mdf_phone phoneNumber" maxlength="13">
                  <p class="validation v_phone"></p>
                </div>
              </li>
              <li>
                <div class="buttons">
                  <button type="submit" class="submit">등록</button>
                  <button type="reset" class="reset">초기화</button>
                </div>
              </li>
            </ul>
          </div>
        </form>
      </div>
    </div>
    <%@ include file="../resources/template/nav.jsp" %>
  </div>
  <%@ include file="../resources/template/footer.jsp" %>
  <script src="resources/js/memberModify.js"></script>
</body>
</html>

