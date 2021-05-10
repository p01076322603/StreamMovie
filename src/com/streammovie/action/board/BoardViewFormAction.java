package com.streammovie.action.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.dao.BoardDAO;
import com.streammovie.dto.BoardDTO;

public class BoardViewFormAction implements Action {

	private BoardDAO bDao = BoardDAO.getInstance();
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int articleno = 0;
		int result = 0;
		if (request.getParameter("articleno") != null) {
			try {
				articleno = Integer.parseInt(request.getParameter("articleno"));
			} catch (NumberFormatException e) {
				response.sendRedirect("/StreamMovie/pageNotFound");
				return null;
			}
			result = bDao.updateReadcount(articleno);
		}
		
		if (result == 0) {
			response.sendRedirect("/StreamMovie/pageNotFound");
			return null;
		}

		BoardDTO article = bDao.selectBoard(articleno);

		request.setAttribute("article", article);
		String url = "board/boardView.jsp";
		return new ActionForward(url, false);
	}
	
}
