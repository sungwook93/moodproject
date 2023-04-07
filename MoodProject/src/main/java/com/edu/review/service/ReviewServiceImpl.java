package com.edu.review.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.common.util.SearchCriteria;
import com.edu.product.dto.ProductDTO;
import com.edu.review.dao.ReviewDAO;
import com.edu.review.dto.ReviewDTO;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Inject
	private ReviewDAO reviewDAO;
	
	
	// 리뷰 목록 보여주기
	@Override
	public List<ReviewDTO> reviewList(SearchCriteria sCri) throws Exception {
		
		return reviewDAO.reviewList(sCri);
	}
	
	// 전체 페이지 가져오기
	@Override
	public int reviewListTotalCount(SearchCriteria sCri) throws Exception {
		
		return reviewDAO.reviewListTotalCount(sCri);
	}
	
	// 리뷰 등록하기
	@Override
	public int reviewRegister(ReviewDTO reviewDTO) throws Exception {
		
		System.out.println("ReviewServiceImpl 리뷰 등록하기");
		
		return reviewDAO.reviewRegister(reviewDTO);
	}
	
	// productList 가져오기
	@Override
	public List<ProductDTO> productList(ProductDTO productDTO) throws Exception {
		
		return reviewDAO.productList(productDTO);
	}

	@Override
	public List<ProductDTO> searchname(String produt_type) throws Exception {
		// TODO Auto-generated method stub
		return reviewDAO.searchname(produt_type);
	}

	
	

	


	
	
	
	
	
	
	
	
}
