package com.edu.review.dao;

import java.util.List;

import com.edu.common.util.SearchCriteria;
import com.edu.product.dto.ProductDTO;
import com.edu.review.dto.ReviewDTO;

public interface ReviewDAO {

	// 리뷰 목록 보여주기
	public List<ReviewDTO> reviewList(SearchCriteria sCri) throws Exception;
	
	// 전체 페이지 가져오기
	public int reviewListTotalCount(SearchCriteria sCri) throws Exception;

	// 리뷰 전체 목록 가져오기 
	public List<ReviewDTO> reviewTotalList() throws Exception;
	
	// 리뷰 등록하기
	public int reviewRegister(ReviewDTO reviewDTO) throws Exception;
	
	// productList 가져오기
	public List<ProductDTO> productList(String userID) throws Exception;







}
