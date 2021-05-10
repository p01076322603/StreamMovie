/* TODO
 * 게시판 삭제 기능 비동기 요청에 대한 응답 교체 (boardView.jsp)
 * 주소 삭제 기능 넣기 (memberModify.jsp, ...)
 * 게시글 작성 최소글자 유효성 검사 글자수로 바꾸기 (boardWrite.js)
 * 게시판 리스트 액션 페이징 클래스로 처리
 * 게시판 작성, 수정, 댓글 액션 정규식 검사 메서드 정리
 * 게시판, 회원 페이지 진입 유효성 검사 정확하게 변경
 * 여러 게시판 사용을 고려하지않은 기능들 교체
 */

package com.streammovie.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.action.HomeAction;
import com.streammovie.action.IdCheckAction;
import com.streammovie.action.LoginCheckAction;
import com.streammovie.action.LogoutAction;
import com.streammovie.action.RegisterAction;
import com.streammovie.action.RegisterFormAction;
import com.streammovie.action.SearchAction;
import com.streammovie.action.board.BoardDeleteAction;
import com.streammovie.action.board.BoardFreeListAction;
import com.streammovie.action.board.BoardImageResponseAction;
import com.streammovie.action.board.BoardImageUploadAction;
import com.streammovie.action.board.BoardModifyAction;
import com.streammovie.action.board.BoardModifyFormAction;
import com.streammovie.action.board.BoardReplyAction;
import com.streammovie.action.board.BoardReplyFormAction;
import com.streammovie.action.board.BoardShareListAction;
import com.streammovie.action.board.BoardViewFormAction;
import com.streammovie.action.board.BoardWriteAction;
import com.streammovie.action.board.BoardWriteFormAction;
import com.streammovie.action.error.PageNotFoundAction;
import com.streammovie.action.member.MemberInfoAction;
import com.streammovie.action.member.MemberLeaveAction;
import com.streammovie.action.member.MemberLeaveFormAction;
import com.streammovie.action.member.MemberModifyAction;
import com.streammovie.action.member.MemberModifyFormAction;
import com.streammovie.action.member.MemberModifyPasswordAction;
import com.streammovie.action.member.MemberModifyPasswordFormAction;
import com.streammovie.action.member.MemberVerifyAction;

public class StreamMovieFrontController extends HttpServlet {
	private static final long serialVersionUID = 6028649667836063814L;
	
	private String getCommandPath(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		int contextLength = ctx.length();

		return uri.substring(contextLength);
	}

	// 매핑 정보
	private static Map<String, Action> actionMap = new HashMap<>();
	public StreamMovieFrontController() {
		
		// 헤더 메뉴 매핑
		actionMap.put("/", new HomeAction());
		actionMap.put("/register", new RegisterFormAction());
		actionMap.put("/welcome", new RegisterAction());
		actionMap.put("/idCheck", new IdCheckAction());
		actionMap.put("/loginCheck", new LoginCheckAction());
		actionMap.put("/logout", new LogoutAction());
		actionMap.put("/search", new SearchAction());

		// 에러 매핑
		actionMap.put("/pageNotFound", new PageNotFoundAction());
		
		// 회원 페이지 매핑
		actionMap.put("/memberInfo", new MemberInfoAction());
		actionMap.put("/memberVerify", new MemberVerifyAction());
		actionMap.put("/memberModifyForm", new MemberModifyFormAction());
		actionMap.put("/memberModifyAction", new MemberModifyAction());
		actionMap.put("/memberModifyPasswordForm", new MemberModifyPasswordFormAction());
		actionMap.put("/memberModifyPasswordAction", new MemberModifyPasswordAction());
		actionMap.put("/memberLeaveForm", new MemberLeaveFormAction());
		actionMap.put("/memberLeaveAction", new MemberLeaveAction());
		
		// 게시판 페이지 매핑
		actionMap.put("/freeBoard", new BoardFreeListAction());		
		actionMap.put("/shareBoard", new BoardShareListAction());	

		actionMap.put("/boardWrite", new BoardWriteFormAction());		
		actionMap.put("/boardWriteAction", new BoardWriteAction());		
		actionMap.put("/boardImageUpload", new BoardImageUploadAction());		
		actionMap.put("/boardImageResponse", new BoardImageResponseAction());		
		actionMap.put("/board", new BoardViewFormAction());		
		actionMap.put("/boardDelete", new BoardDeleteAction());		
		actionMap.put("/boardModify", new BoardModifyFormAction());		
		actionMap.put("/boardModifyAction", new BoardModifyAction());		
		actionMap.put("/boardReply", new BoardReplyFormAction());		
		actionMap.put("/boardReplyAction", new BoardReplyAction());		

	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 공통 부분
		request.setCharacterEncoding("UTF-8");
		String command = getCommandPath(request);
		Action action = actionMap.get(command);
		
		// 404 에러
		if (action == null) {
			action = actionMap.get("/pageNotFound");
		}

		// redirect 분기
		ActionForward forward = action.execute(request, response);
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

}
