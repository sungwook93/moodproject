package com.edu.review.dto;

public class ReviewImagesDTO {
	
	private int review_bno;
	private String images01;
	private String images02;
	
	public int getReview_bno() {
		return review_bno;
	}
	public void setReview_bno(int review_bno) {
		this.review_bno = review_bno;
	}
	public String getImages01() {
		return images01;
	}
	public void setImages01(String images01) {
		this.images01 = images01;
	}
	public String getImages02() {
		return images02;
	}
	public void setImages02(String images02) {
		this.images02 = images02;
	}
	
	@Override
	public String toString() {
		return "ReviewImageDTO [review_bno=" + review_bno + ", images01=" + images01 + ", images02=" + images02 + "]";
	}
	
	
}
