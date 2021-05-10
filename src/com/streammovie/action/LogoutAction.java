package com.streammovie.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.common.ResponseScript;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		if (request.getHeader("referer") == null) {
			ResponseScript.getAlertHrefScript(response, "잘못된 접근입니다");
			return null;
		}
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		String url = "/StreamMovie";
		return new ActionForward(url, true);
	}

}
