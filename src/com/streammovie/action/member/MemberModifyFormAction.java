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

public class MemberModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		if (member == null || request.getHeader("referer") == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}
		
		String pwd = request.getParameter("pwd");
		if (!pwd.equals(member.getPwd())) {
			ResponseScript.getAlertHrefScript(response, "비밀번호가 일치하지 않습니다", "'memberVerify'");
			return null;
		}
		
		request.setAttribute("member", member);
		String address = member.getAddress();

		if (address != null) {
			String[] addressArray = address.split("，");
			
			request.setAttribute("addr1", addressArray[0]);
			request.setAttribute("addr2", addressArray[1]);
			request.setAttribute("addr3", addressArray[2]);
			if (addressArray.length > 3) {
				request.setAttribute("addr4", addressArray[3]);
			}
		}
		
		String url = "member/memberModify.jsp";
		return new ActionForward(url, false);
	}

}
