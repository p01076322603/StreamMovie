package com.streammovie.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.ResponseScript;
import com.streammovie.dto.BoardDTO;
import com.streammovie.dto.MemberDTO;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		if (member == null || request.getHeader("referer") == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}
		
		String boardno = request.getParameter("boardno");
		request.setAttribute("boardno", boardno);
		
		BoardDTO article = new BoardDTO();
		article.setArticleno(Integer.parseInt(request.getParameter("articleno")));
		article.setRef      (Integer.parseInt(request.getParameter("ref")));
		article.setRestep   (Integer.parseInt(request.getParameter("restep")));
		article.setRelevel  (Integer.parseInt(request.getParameter("relevel")));
		request.setAttribute("parentArticle", article);
		
		String url = "board/boardReply.jsp";
		return new ActionForward(url, false);
	}
	
	

}
