package com.streammovie.dto;

import java.sql.Timestamp;

public class BoardDTO {
	
	private int boardno;
	private int boardseq;
	private int articleno;
	private String subject;
	private String content;
	private Timestamp regdate;
	private int readcount;
	private int ref;
	private int restep;
	private int relevel;
	private String id;

	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public int getBoardseq() {
		return boardseq;
	}
	public void setBoardseq(int boardseq) {
		this.boardseq = boardseq;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public int getRelevel() {
		return relevel;
	}
	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BoardDTO [boardno=" + boardno + ", boardseq=" + boardseq + ", articleno=" + articleno + ", subject="
				+ subject + ", content=" + content + ", regdate=" + regdate + ", readcount=" + readcount + ", ref="
				+ ref + ", restep=" + restep + ", relevel=" + relevel + ", id=" + id + "]";
	}
	
}
