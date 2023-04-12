package com.edu.common.util;

public class AdminCriteria extends Criteria {
	/*
	private int page; //현재 페이지 번호
	private int perPageNum;	//한 페이지당 보여줄 게시글의 갯순
	*/
	
	private String product_type;	//상품 타입

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	@Override
	public String toString() {
		return "AdminCriteria [product_type=" + product_type + "]";
	}
	
	
	
	
}
