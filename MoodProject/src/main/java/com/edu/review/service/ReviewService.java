package com.edu.review.service;

import java.util.List;

import com.edu.common.util.SearchCriteria;
import com.edu.order.dto.CartDTO;
import com.edu.product.dto.ProductDTO;
import com.edu.review.dto.ReviewDTO;

public interface ReviewService {
	
	// 리뷰 목록 보여주기
	public List<ReviewDTO> reviewList(SearchCriteria sCri) throws Exception;
	
	// 전체 페이지 가져오기
	public int reviewListTotalCount(SearchCriteria sCri) throws Exception;
	
	// 리뷰 등록하기
	public int reviewRegister(ReviewDTO reviewDTO) throws Exception;
	
	// productList 가져오기
	public List<ProductDTO> productList(ProductDTO productDTO) throws Exception;
	
	// 타입에 따른 상품명 찾기
	public List<ProductDTO> searchname(String produt_type)throws Exception;
	
	// 리뷰 상세페이지 보여주기 
	public ReviewDTO reviewDetail(int review_bno) throws Exception;
	
}
