package com.streammovie.action;

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

public class HomeAction implements Action {

	private BoardDAO bDao = BoardDAO.getInstance();
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Map<String, Object> boardMap = new HashMap<>();
		boardMap.put("listStart", 1);
		boardMap.put("listEnd", 7);

		// 자유 게시판
		boardMap.put("boardno", 3);
		List<BoardDTO> freeBoardList = bDao.selectBoardList(boardMap);
		
		// 나눔 게시판
		boardMap.put("boardno", 4);
		List<BoardDTO> shareBoardList = bDao.selectBoardList(boardMap);
		
		request.setAttribute("freeBoardList", freeBoardList);
		request.setAttribute("shareBoardList", shareBoardList);
		
		String url = "home.jsp";
		return new ActionForward(url, false);
	}

	
}
