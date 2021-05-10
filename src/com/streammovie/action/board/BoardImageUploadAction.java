package com.streammovie.action.board;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

import com.google.gson.JsonObject;
import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.FileManagement;
import com.streammovie.common.PathCollection;
import com.streammovie.common.ResponseScript;
import com.streammovie.dto.MemberDTO;

public class BoardImageUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		if (member == null || request.getHeader("referer") == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}
		
		String id = member.getId();
		UUID uuid = UUID.randomUUID();
		
		// 이미지 temp 저장
		Map<String, String> filenameMap = FileManagement.fileUploadToTempWithUUID(request, uuid);
		String filename = filenameMap.get("upload");
		
		String extension = filename.substring(filename.indexOf("."));
		String uuidFilename = uuid + extension;
		
		File tempImage = new File(PathCollection.TEMP_FOLDER_PATH + File.separator + uuidFilename);
		File memberDir = new File(PathCollection.TEMP_FOLDER_PATH + File.separator + id);
		
		FileUtils.moveFileToDirectory(tempImage, memberDir, true);
		
		// ckeditor 결과 전송
		JsonObject uploadResult = new JsonObject();
		uploadResult.addProperty("uploaded", 1);
		uploadResult.addProperty("filename", filename);
		uploadResult.addProperty("url", PathCollection.HOME_URL + "boardImageResponse?id=" + id + "&uuid=" + uuid + "&filename=" + filename);
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(uploadResult);
		
		return null;
	}

}
