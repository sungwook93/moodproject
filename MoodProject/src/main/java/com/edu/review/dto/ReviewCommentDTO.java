package com.edu.review.dto;

public class ReviewCommentDTO {
	
	private int review_bno;
	private String reply_content;
	private String reply_regDate;
	private int reply_bno;
	private String userID;
	private int imsi_bno;
	
	public int getReview_bno() {
		return review_bno;
	}
	public void setReview_bno(int review_bno) {
		this.review_bno = review_bno;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_regDate() {
		return reply_regDate;
	}
	public void setReply_regDate(String reply_regDate) {
		this.reply_regDate = reply_regDate;
	}
	public int getReply_bno() {
		return reply_bno;
	}
	public void setReply_bno(int reply_bno) {
		this.reply_bno = reply_bno;
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
		return "ReviewCommentDTO [review_bno=" + review_bno + ", reply_content=" + reply_content + ", reply_regDate="
				+ reply_regDate + ", reply_bno=" + reply_bno + ", userID=" + userID + ", imsi_bno=" + imsi_bno + "]";
	}
	
	

	
}
