package com.streammovie.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.streammovie.Action;
import com.streammovie.ActionForward;
import com.streammovie.dao.MemberDAO;

public class IdCheckAction implements Action {

	private MemberDAO mDao = MemberDAO.getInstance();
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		int result = mDao.selectIdDupeCheck(id);

		JSONObject idCheckResult = new JSONObject();
		idCheckResult.put("result", result);
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(idCheckResult);
		
		return null;
	}
	
}