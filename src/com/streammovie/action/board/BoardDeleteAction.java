package com.streammovie.action.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.PathCollection;
import com.streammovie.common.ResponseScript;
import com.streammovie.dao.BoardDAO;
import com.streammovie.dto.MemberDTO;

public class BoardDeleteAction implements Action {

	private BoardDAO bDao = BoardDAO.getInstance();
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");

		if (!member.getId().equals(request.getParameter("id")) || request.getHeader("referer") == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}
		
		int articleno = Integer.parseInt(request.getParameter("articleno")); 
		int result = bDao.deleteArticle(articleno);
	
		if (result > 0) {
			File articleImageDir = 
					new File(PathCollection.IMAGE_BOARD_FOLDER_PATH + File.separator + articleno);
			if (articleImageDir.exists()) FileUtils.deleteDirectory(articleImageDir);
		} 

		return null;
	}

}
