package com.edu.common.util;

public class ProductCriteria extends Criteria {
	/*
	private int page; //현재 페이지 번호
	private int perPageNum;	//한 페이지당 보여줄 게시글의 갯순
	*/
	
	private String product_type;	//상품 타입
	private String product_color;  //상품 색상
	private String array_type; //정렬 타입
	private String searchType; //검색 조건
	private String keyword; // 검색 키워드
	
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getProduct_color() {
		return product_color;
	}
	public void setProduct_color(String product_color) {
		this.product_color = product_color;
	}
	public String getArray_type() {
		return array_type;
	}
	public void setArray_type(String array_type) {
		this.array_type = array_type;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "ProductCriteria [product_type=" + product_type + ", product_color=" + product_color + ", searchType="
				+ searchType + ", keyword=" + keyword + "]";
	}
	
	
}
