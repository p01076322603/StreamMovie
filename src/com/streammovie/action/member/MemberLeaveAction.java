package com.streammovie.action.member;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.PathCollection;
import com.streammovie.common.ResponseScript;
import com.streammovie.dao.MemberDAO;
import com.streammovie.dto.MemberDTO;

public class MemberLeaveAction implements Action {

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

		String leavePassword = request.getParameter("leavePassword");

		if (!member.getPwd().equals(leavePassword)) {
			ResponseScript.getAlertHrefScript(response, "비밀번호가 일치하지 않습니다", "'memberLeaveForm'");
			return null;
		}

		int result = mDao.updateLeaveMember(member.getId());
		if (result > 0) {

			File memberDir = new File(PathCollection.IMAGE_FOLDER_PATH + "\\" + "member" + "\\" + member.getId());
			if (memberDir.exists()) {

				File[] deleteFileList = memberDir.listFiles();
				for (File file : deleteFileList) {
					file.delete();
				}
				memberDir.delete();
			}
			
			member.setProfimg("default_profile.jpg");
			session.invalidate();
			
			ResponseScript.getAlertHrefScript(response, "성공적으로 탈퇴하였습니다");
		} else {
			ResponseScript.getAlertHrefScript(response, "서버에 문제가 발생하였습니다");
		}

		return null;
	}

}
