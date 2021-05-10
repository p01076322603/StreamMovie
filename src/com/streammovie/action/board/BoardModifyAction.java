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

public class BoardModifyAction implements Action {

	private BoardDAO bDao = BoardDAO.getInstance();
	String newContent;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		if (member == null || request.getHeader("referer") == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}
		
		// 수정 시 넘어오는 데이터
		String id = request.getParameter("id");
		int articleno = Integer.parseInt(request.getParameter("articleno"));
		String newSubject = request.getParameter("subject");
		newContent = request.getParameter("content");
		
		// 수정 시 이미지 파일 갱신
		List<String> newFilename = getFilename(newContent);

		File tempImageDir = new File(PathCollection.TEMP_FOLDER_PATH + File.separator + id);
		File boardImageDir = new File(PathCollection.IMAGE_BOARD_FOLDER_PATH + File.separator + articleno);
		if (tempImageDir.exists()) {
			for (File file : tempImageDir.listFiles()) {
				FileUtils.moveFileToDirectory(file, boardImageDir, true);
			}
			tempImageDir.delete();
		}
		
		if (boardImageDir.exists()) {
			for (File file : boardImageDir.listFiles()) {
				if (!newFilename.stream()
						.anyMatch(value -> value.equals(file.getName().substring(0, file.getName().lastIndexOf("."))))) {
					file.delete();
				}
			}
		}
		
		// 새로 등록할 content src 경로 변경
		List<String> newImgSrc = getNewImgSrc(newContent);

		if (boardImageDir.exists()) {
			for (File file : boardImageDir.listFiles()) {

				newImgSrc.forEach(value -> {
					
					if ((PathCollection.HOME_URL + value).contains(file.getName().substring(0, file.getName().lastIndexOf(".")))) {
						newContent = newContent.replace(PathCollection.HOME_URL + value, 
								"/boardImagePath" + File.separator + articleno + File.separator + file.getName());
					}
				});
			}
		}
		
		// 수정 사항 DB에 등록
		BoardDTO article = new BoardDTO();
		article.setArticleno(articleno);
		article.setSubject(newSubject);
		article.setContent(newContent);
		
		int result = bDao.updateArticle(article);
		newContent = "";
		
		// 결과 alert
		if (result > 0) {
			ResponseScript.getAlertHrefScript(response, "게시글을 수정하였습니다", "'board?articleno=" + articleno + "'");
			return null;
		} else {
			ResponseScript.getAlertHrefScript(response, "게시글 수정에 실패하였습니다", "history.go(-1)");
			return null;
		}

	}
	
	private List<String> getNewImgSrc(String content) {
		
		Pattern nonValidPattern = Pattern
				.compile("<img[^>]*src=[\"']?" + PathCollection.HOME_URL + "([^>\"']+)[\"']?[^>]*>");

		List<String> result = new ArrayList<>();
		Matcher matcher = nonValidPattern.matcher(content);

		while (matcher.find()) {
			result.add(matcher.group(1));
		}
		
		return result;
	}

	private List<String> getFilename(String content) {
		Pattern nonValidPattern = Pattern
				 .compile("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})");
		
		List<String> result = new ArrayList<>();
		Matcher matcher = nonValidPattern.matcher(content);
		
		while (matcher.find()) {
			result.add(matcher.group(1));
		}
		
		return result;
	}
	
}