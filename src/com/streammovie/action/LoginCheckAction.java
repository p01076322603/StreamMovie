package com.streammovie.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.dao.MemberDAO;
import com.streammovie.dto.MemberDTO;

public class LoginCheckAction implements Action {

	private MemberDAO mDao = MemberDAO.getInstance();
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String id = request.getParameter("loginId");
		String pwd = request.getParameter("loginPwd");
		
		int result = mDao.selectLoginCheck(id, pwd);
		
		JSONObject loginCheckResult = new JSONObject();
		loginCheckResult.put("result", result);
		
		if (result == 2) {
			mDao.updateLoginDate(id);
			MemberDTO member = mDao.selectMember(id);
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
		}
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(loginCheckResult);
		
		return null;
	}
	
}
