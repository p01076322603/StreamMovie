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

public class BoardFreeListAction implements Action {

	private BoardDAO bDao = BoardDAO.getInstance();
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 현재 게시판의 번호
		int boardno = 3;
		
		// 보여질 게시글의 개수
		int listCount = 15;
		
		// 페이징 숫자 범위
		int paginationCount = 10;
		
		// 검색 키워드
		String keyword = request.getParameter("keyword");
		
		// 현재 페이지
		String strCurrentPage = request.getParameter("page");
		int currentPage;
		try {
			currentPage = Integer.parseInt((strCurrentPage == null) ? "1" : strCurrentPage);
		} catch (NumberFormatException e) {
			response.sendRedirect("/StreamMovie/pageNotFound");
			return null;
		}
		
		// 전체 게시글 개수
		Map<String, Object> boardCountMap = new HashMap<>();
		boardCountMap.put("keyword", keyword);
		boardCountMap.put("boardno", boardno);
		int boardCount = bDao.selectAllBoardCount(boardCountMap);

		// 전체 페이지
		int pageCount = (boardCount % listCount != 0) ? boardCount / listCount + 1 
													  : boardCount / listCount;

		// 페이지 직접 입력 범위 제한
		if (strCurrentPage != null && Integer.parseInt(strCurrentPage) > pageCount) {
			currentPage = pageCount;
		} else if (strCurrentPage != null && Integer.parseInt(strCurrentPage) < 1) {
			currentPage = 1;
		}
		
		// 현재 페이지의 시작 게시글 ROWNUM
		int listStart = (currentPage - 1) * listCount + 1;
		
		// 현재 페이지의 끝 게시글 ROWNUM
		int listEnd = currentPage * listCount;

		// 페이징 숫자 블럭 구간을 나타내는 수치
		int blockNum = (currentPage % paginationCount == 0) ? (currentPage / paginationCount) - 1
															: currentPage / paginationCount;
														
		Map<String, Object> boardMap = new HashMap<>();
		boardMap.put("listStart", listStart);
		boardMap.put("listEnd", listEnd);
		boardMap.put("keyword", keyword);
		boardMap.put("boardno", boardno);
		
		List<BoardDTO> boardList = bDao.selectBoardList(boardMap);
		request.setAttribute("boardList", boardList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("paginationCount", paginationCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("blockNum", blockNum);
		request.setAttribute("keyword", keyword);
		
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		
		String url = "board/freeBoard.jsp";
		return new ActionForward(url, false);
	}
	
}