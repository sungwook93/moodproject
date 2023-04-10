package com.edu.review.dto;

import java.util.Date;

public class ReviewDTO {

	private int review_bno;
	private String userID;
	private String product_type;
	private String product_name;
	private String review_subject;
	private String review_content;
	private Date review_date;
	private int review_star;
	private int reply_count;
	
	public int getReview_bno() {
		return review_bno;
	}
	public void setReview_bno(int review_bno) {
		this.review_bno = review_bno;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getReview_subject() {
		return review_subject;
	}
	public void setReview_subject(String review_subject) {
		this.review_subject = review_subject;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public int getReview_star() {
		return review_star;
	}
	public void setReview_star(int review_star) {
		this.review_star = review_star;
	}
	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	
	@Override
	public String toString() {
		return "ReviewDTO [review_bno=" + review_bno + ", userID=" + userID + ", product_type=" + product_type
				+ ", product_name=" + product_name + ", review_subject=" + review_subject + ", review_content="
				+ review_content + ", review_date=" + review_date + ", review_star=" + review_star + ", reply_count="
				+ reply_count + "]";
	}
	
	
	

	
	
}
