package com.edu.board.dto;

import java.sql.Date;

public class BoardDTO {
	
	private int qna_bno;
	private String qna_subject;
	private String userID;
	private String qna_content;
	private Date qna_regDate;
	private int qna_readCount;
	private int reply_count;
	
	public int getQna_bno() {
		return qna_bno;
	}
	public void setQna_bno(int qna_bno) {
		this.qna_bno = qna_bno;
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
	public Date getQna_regDate() {
		return qna_regDate;
	}
	public void setQna_regDate(Date qna_regDate) {
		this.qna_regDate = qna_regDate;
	}
	public int getQna_readCount() {
		return qna_readCount;
	}
	public void setQna_readCount(int qna_readCount) {
		this.qna_readCount = qna_readCount;
	}
	
	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [qna_bno=" + qna_bno + ", qna_subject=" + qna_subject + ", userID=" + userID + ", qna_content="
				+ qna_content + ", qna_regDate=" + qna_regDate + ", qna_readCount=" + qna_readCount + ", reply_count="
				+ reply_count + "]";
	}

	
	


	


	
	
	
	
	
	
	
}
