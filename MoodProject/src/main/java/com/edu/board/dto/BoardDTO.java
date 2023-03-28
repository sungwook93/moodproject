package com.edu.board.dto;

import java.sql.Timestamp;

public class BoardDTO {
	
	private int qna_bno;
	private String qna_subject;
	private String userID;
	private String qna_content;
	private Timestamp qna_regDate;
	private int qna_readCount;
	private String qna_yn;
	
	public int getQan_bno() {
		return qna_bno;
	}
	public void setQan_bno(int qan_bno) {
		this.qna_bno = qan_bno;
	}
	public String getQna_subject() {
		return qna_subject;
	}
	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public Timestamp getQna_regDate() {
		return qna_regDate;
	}
	public void setQna_regDate(Timestamp qna_regDate) {
		this.qna_regDate = qna_regDate;
	}
	public int getQna_readCount() {
		return qna_readCount;
	}
	public void setQna_readCount(int qna_readCount) {
		this.qna_readCount = qna_readCount;
	}
	public String getQna_yn() {
		return qna_yn;
	}
	public void setQna_yn(String qna_yn) {
		this.qna_yn = qna_yn;
	}
	
	@Override
	public String toString() {
		return "boardDTO [qan_bno=" + qna_bno + ", qna_subject=" + qna_subject + ", userID=" + userID + ", qna_content="
				+ qna_content + ", qna_regDate=" + qna_regDate + ", qna_readCount=" + qna_readCount + ", qna_yn="
				+ qna_yn + "]";
	}
	
	
	
}
