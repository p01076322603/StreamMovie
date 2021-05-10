package com.streammovie.action.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.PathCollection;
import com.streammovie.common.ResponseScript;
import com.streammovie.dao.BoardDAO;
import com.streammovie.dto.BoardDTO;
import com.streammovie.dto.MemberDTO;

public class BoardWriteAction implements Action {

	private BoardDAO bDao = BoardDAO.getInstance();
	int imgCount;
	
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
		article.setId	  (request.getParameter("id"));
		article.setBoardno(Integer.parseInt(request.getParameter("boardno"))); 
		article.setSubject(request.getParameter("subject"));
		article.setContent("　");

		int articleno = bDao.createNewArticle(article);
		String content = request.getParameter("content");
		
		List<String> imgSrc = getImgSrc(content);
		List<String> uuidFilenameList = createUuidFilenameList(content);
		
		File tempUserDir = new File(PathCollection.TEMP_FOLDER_PATH + File.separator + member.getId());
		File boardImageDir = new File(PathCollection.IMAGE_BOARD_FOLDER_PATH);
		if (tempUserDir.exists()) FileUtils.moveDirectoryToDirectory(tempUserDir, boardImageDir, true);

		File usernameDir = new File(PathCollection.IMAGE_BOARD_FOLDER_PATH + File.separator + member.getId());
		File articleNumberDir = new File(PathCollection.IMAGE_BOARD_FOLDER_PATH + File.separator + articleno);
		if (usernameDir.exists()) usernameDir.renameTo(articleNumberDir);
		
		for (int i = 0; i < imgCount; i++) {
			content = content.replace(imgSrc.get(i), 
					"/boardImagePath" + File.separator + articleno + File.separator + uuidFilenameList.get(i));
		}
		
		int result = bDao.updateNewArticleContent(articleno, content);
		imgCount = 0;
		
		if (result > 0) {
			ResponseScript.getAlertHrefScript(response, "게시글을 등록하였습니다",
					"'board?articleno=" + articleno + "'");
			return null;
		} else {
			ResponseScript.getAlertHrefScript(response, "게시글 등록에 실패하였습니다", "history.go(-1)");
			return null;
		}
	}
	
	private List<String> getImgSrc(String content) {
		Pattern nonValidPattern = Pattern
				.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");

		List<String> result = new ArrayList<>();
		Matcher matcher = nonValidPattern.matcher(content);

		while (matcher.find()) {
			result.add(matcher.group(1));
			imgCount++;
		}
		
		return result;
	}
	
	private List<String> createUuidFilenameList(String content) {
		List<String> uuidFilenameList = new ArrayList<>();
		
		for (int i = 0; i < imgCount; i++) {
			int uuidIndex = content.indexOf("uuid");
			int filenameIndex = content.indexOf("filename");
			
			String uuid = content.substring(uuidIndex + 5, content.indexOf("&", uuidIndex));
			String filename = content.substring(filenameIndex + 9, content.indexOf("\"", filenameIndex));
			
			String uuidFilename = uuid + filename.substring(filename.lastIndexOf("."));
			uuidFilenameList.add(uuidFilename);
			
			content = content.substring(content.indexOf("\"", filenameIndex));
		}
		return uuidFilenameList;
	}

}
