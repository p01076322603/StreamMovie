package com.streammovie.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseScript {

	public static void getHrefScript(HttpServletResponse response, String location) 
			throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print("<script> "
				   + "location.href='" + location + "'; "
				   + "</script>");
	}
	
	public static void getAlertHrefScript(HttpServletResponse response, String alertParam) 
			throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print("<script> "
				   + "alert('" + alertParam + "'); "
				   + "location.href='/StreamMovie'; "
				   + "</script>");
	}
	
	public static void getAlertHrefScript(HttpServletResponse response, String alertParam, String location) 
			throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print("<script> "
				+ "alert('" + alertParam + "'); "
				+ "location.href=" + location + "; "
				+ "</script>");
	}

	
}
