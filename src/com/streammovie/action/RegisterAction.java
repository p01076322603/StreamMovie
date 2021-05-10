package com.streammovie.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.ResponseScript;
import com.streammovie.dao.MemberDAO;
import com.streammovie.dto.MemberDTO;

public class RegisterAction implements Action {

	private MemberDAO mDao = MemberDAO.getInstance();
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getHeader("referer") == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}
		
		MemberDTO member = new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));
		member.setNickname(request.getParameter("nickname"));
		member.setEmail(request.getParameter("email"));
		
		int result = mDao.insertRegisterMember(member);
		if (result == 0) {
			ResponseScript.getAlertHrefScript(response, "서버에 문제가 발생하였습니다");
			return null;
		}
		
	 	String url = "welcome.jsp";
		return new ActionForward(url, false);
	}

}
