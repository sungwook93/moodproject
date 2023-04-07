package com.edu.review.dto;

import java.util.Date;

public class ReviewDTO {

	private int review_bno;
	private String userID;
	private String product_code;
	private String review_subject;
	private String review_content;
	private Date review_date;
	private int review_star;
	
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
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
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
	
	@Override
	public String toString() {
		return "ReviewDTO [review_bno=" + review_bno + ", userID=" + userID + ", product_code=" + product_code
				+ ", review_subject=" + review_subject + ", review_content=" + review_content + ", review_date="
				+ review_date + ", review_star=" + review_star + "]";
	}


	
	
}
