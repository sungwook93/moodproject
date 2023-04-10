package com.edu.review.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.common.util.SearchCriteria;
import com.edu.product.dto.ProductDTO;
import com.edu.review.dao.ReviewDAO;
import com.edu.review.dto.ReviewCommentDTO;
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

	// 타입에 따른 상품명 찾기
	@Override
	public List<ProductDTO> searchname(String produt_type) throws Exception {
		// TODO Auto-generated method stub
		return reviewDAO.searchname(produt_type);
	}

	// 리뷰 상세페이지 보여주기 
	@Override
	public ReviewDTO reviewDetail(int review_bno) throws Exception {
		
		return reviewDAO.reviewDetail(review_bno);
	}
	
	// 리뷰 수정	
	@Override
	public int reviewUpdate(ReviewDTO reviewDTO) throws Exception {
		
		return reviewDAO.reviewUpdate(reviewDTO);
	}

	// 리뷰 삭제
	@Override
	public int reviewDelete(int review_bno) throws Exception {
		
		return reviewDAO.reviewDelete(review_bno);
	}
	
	// 리뷰에 해당하는 댓글 등록하기
	@Override
	public int reviewCommentRegister(ReviewCommentDTO reviewCommentDTO) throws Exception {
		
		return reviewDAO.reviewCommentRegister(reviewCommentDTO);
	}

	// 게시글 번호에 해당하는 댓글 수 구하기
	@Override
	public int commentListCount(int review_bno) throws Exception {
		
		return reviewDAO.commentListCount(review_bno);
	}

	@Override
	public List<ReviewCommentDTO> commentList(int review_bno) throws Exception {
		
		return reviewDAO.commentList(review_bno);
	}

	// 리뷰 리스트 제목에 댓글 수 표시
	@Override
	public int updateReplyCount(int review_bno) throws Exception {
		
		return reviewDAO.updateReplyCount(review_bno);
	}

	// 댓글 번호에 해당하는 댓글 삭제하기
	@Override
	public int replyDelete(int reply_bno) throws Exception {
		
		return reviewDAO.replyDelete(reply_bno);
	}
	
	// 댓글 번호에 해당하는 댓글 수정하기
	@Override
	public int replyUpdate(ReviewCommentDTO reviewCommentDTO) throws Exception {
		
		return reviewDAO.replyUpdate(reviewCommentDTO);
	}

	
	
	
	
	
	
	
	
}
