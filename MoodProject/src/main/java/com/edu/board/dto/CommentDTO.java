package com.edu.board.dto;

import java.sql.Timestamp;

public class CommentDTO {
	
	private int reply_bno;
	private int qna_bno;
	private String reply_content;
	private Timestamp reply_regDate;
	private String userID;
	private int imsi_bno;
	
	public int getReply_bno() {
		return reply_bno;
	}
	public void setReply_bno(int reply_bno) {
		this.reply_bno = reply_bno;
	}
	public int getQna_bno() {
		return qna_bno;
	}
	public void setQna_bno(int qna_bno) {
		this.qna_bno = qna_bno;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Timestamp getReply_regDate() {
		return reply_regDate;
	}
	public void setReply_regDate(Timestamp reply_regDate) {
		this.reply_regDate = reply_regDate;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getImsi_bno() {
		return imsi_bno;
	}
	public void setImsi_bno(int imsi_bno) {
		this.imsi_bno = imsi_bno;
	}
	
	@Override
	public String toString() {
		return "CommentDTO [reply_bno=" + reply_bno + ", qna_bno=" + qna_bno + ", reply_content=" + reply_content
				+ ", reply_regDate=" + reply_regDate + ", userID=" + userID + ", imsi_bno=" + imsi_bno + "]";
	}
	
	
	
	
}
