package com.streammovie;

public class ActionForward {
	
	// true → sendRedirect, false → forward
	private String path;
	private boolean isRedirect;
	
	public ActionForward() {
	}
	
	public ActionForward(String path, boolean isRedirect) {
		this.path = path;
		this.isRedirect = isRedirect;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

}
