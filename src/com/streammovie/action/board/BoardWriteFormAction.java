package com.streammovie.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.ResponseScript;
import com.streammovie.dto.MemberDTO;

public class BoardWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		if (member == null) {
			ResponseScript.getAlertHrefScript(response, "로그인이 필요한 항목입니다");
			return null;
		}
	
		String boardno = request.getParameter("boardno");
		request.setAttribute("boardno", boardno);
		
		String url = "board/boardWrite.jsp";
		return new ActionForward(url, false);
	}

}