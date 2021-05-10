package com.streammovie.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.ResponseScript;
import com.streammovie.dto.MemberDTO;

public class MemberLeaveFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		if (member == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}
		
		request.setAttribute("member", member);
		
		String url = "member/memberLeave.jsp";
		return new ActionForward(url, false);
	}
	
	

}
