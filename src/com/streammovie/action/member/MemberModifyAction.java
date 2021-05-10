package com.streammovie.action.member;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.FileManagement;
import com.streammovie.common.PathCollection;
import com.streammovie.common.ResponseScript;
import com.streammovie.dao.MemberDAO;
import com.streammovie.dto.MemberDTO;

public class MemberModifyAction implements Action {

	private MemberDAO mDao = MemberDAO.getInstance();
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		if (member == null || request.getHeader("referer") == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}

		Map<String, String> paramMap = new HashMap<>();
		Map<String, String> filenameMap = FileManagement.fileUploadToTemp(request, paramMap);

		String address = paramMap.get("addr1") + "，" 
					   + paramMap.get("addr2") + "，" 
					   + paramMap.get("addr3") + "，" 
					   + paramMap.get("addr4") ;
		
		member.setAddress  (paramMap.get("addr1").equals("") ? "" : address);
		member.setPhone    (paramMap.get("phone"));
		member.setSignature(paramMap.get("signature"));
		member.setMailing  (paramMap.get("mailing"));
		member.setEmail    (paramMap.get("email"));
		member.setNickname (paramMap.get("nickname"));
		
		String new_profimg = filenameMap.get("new_profimg");
		String old_profimg = paramMap.get("old_profimg");
	
		if (new_profimg != null) {
			String tempFolderPath = PathCollection.TEMP_FOLDER_PATH;
			String memberFolderPath = PathCollection.IMAGE_FOLDER_PATH + File.separator + "member";
			
			File memberDir = new File(memberFolderPath + File.separator + member.getId());
			File tempFile = new File(tempFolderPath + File.separator + new_profimg);
			File oldFile = new File(memberDir + File.separator + old_profimg);
			
			if (oldFile.exists()) oldFile.delete();
			try {
				FileUtils.moveFileToDirectory(tempFile, memberDir, true);
			} catch (IOException e) {
				e.printStackTrace();
			}

			member.setProfimg(new_profimg);
		} else {
			member.setProfimg(old_profimg);
		}
		
		MemberDTO modifiedMember = mDao.modifyMember(member);
		if(modifiedMember != null) {
			
			session.setAttribute("loginMember", modifiedMember);
			request.setAttribute("member", modifiedMember);
			
			ResponseScript.getAlertHrefScript(response, "회원 정보를 수정하였습니다", "'memberInfo'");
			return null;
	
		} else {
			ResponseScript.getAlertHrefScript(response, "서버에 문제가 발생하였습니다");
			return null;
		}
		
	}
	
}
