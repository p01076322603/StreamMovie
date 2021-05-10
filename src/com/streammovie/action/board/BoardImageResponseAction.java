package com.streammovie.action.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.PathCollection;
import com.streammovie.common.ResponseScript;
import com.streammovie.dto.MemberDTO;

public class BoardImageResponseAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		if (member == null || request.getHeader("referer") == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}
		
		String uuid = request.getParameter("uuid");
		String filename = request.getParameter("filename");
		String extension = filename.substring(filename.lastIndexOf("."));
		
		String uuidFilename = uuid + extension;
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + uuidFilename);

		String path = PathCollection.TEMP_FOLDER_PATH + File.separator + member.getId() + File.separator + uuidFilename;
		File imageFile = new File(path);
		
		OutputStream outputStream = response.getOutputStream();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(imageFile);
		} catch (Exception e) {
			return null;
		}
		
		byte[] buffer = new byte[8 * 1024];
		while (true) {
			int count = fileInputStream.read(buffer);
			if (count == -1) {
				break;
			}
			outputStream.write(buffer, 0, count);
		}
		
		fileInputStream.close();
		outputStream.close();
		
		return null;
	}

}
