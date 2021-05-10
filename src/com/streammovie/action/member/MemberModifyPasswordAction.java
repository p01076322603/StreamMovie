package com.streammovie.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.ResponseScript;
import com.streammovie.dao.MemberDAO;
import com.streammovie.dto.MemberDTO;

public class MemberModifyPasswordAction implements Action {

	private MemberDAO mDao = MemberDAO.getInstance();
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");

		if (request.getHeader("referer") == null || member == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}
		
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		
		if (!member.getPwd().equals(currentPassword)) {
			ResponseScript.getAlertHrefScript(response, "현재 비밀번호와 일치하지 않습니다", "'memberModifyPasswordForm'");
			return null;
		}
		
		int result = mDao.updatePassword(member.getId(), newPassword);
		if (result > 0) {
			session.invalidate();
			ResponseScript.getAlertHrefScript(response, "비밀번호가 변경되었습니다. 다시 로그인 해주세요");
			return null;
		} else {
			ResponseScript.getAlertHrefScript(response, "서버에 문제가 발생하였습니다");
		}

		return null;
	}
	
}
