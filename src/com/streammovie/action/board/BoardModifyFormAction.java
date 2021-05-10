package com.streammovie.action.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.ResponseScript;
import com.streammovie.dto.BoardDTO;
import com.streammovie.dto.MemberDTO;

public class BoardModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");

		if (member == null || request.getHeader("referer") == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}

		BoardDTO article = new BoardDTO();
		article.setId(request.getParameter("id")); 
		article.setArticleno(Integer.parseInt(request.getParameter("articleno"))); 
		article.setBoardno(Integer.parseInt(request.getParameter("boardno"))); 
		article.setSubject(request.getParameter("subject")); 
		article.setContent(request.getParameter("content")); 
		
		request.setAttribute("article", article);
		
		
		String url = "board/boardModify.jsp";
		return new ActionForward(url, false);
	}
	
}