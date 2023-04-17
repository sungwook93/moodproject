package com.edu.review.dao;

import java.util.List;
import java.util.Map;

import com.edu.common.util.SearchCriteria;
import com.edu.product.dto.ProductDTO;
import com.edu.review.dto.ReviewCommentDTO;
import com.edu.review.dto.ReviewDTO;
import com.edu.review.dto.ReviewImagesDTO;

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
	public List<ProductDTO> productList(ProductDTO productDTO) throws Exception;
	
	// 타입에 따른 상품명 찾기
	public List<ProductDTO> searchname(String produt_type) throws Exception;
	
	// 타입에 따른 상품명 찾기2
	public List<ProductDTO> searchname2(String produt_type) throws Exception;
	
	// 리뷰 상세페이지 보여주기 
	public ReviewDTO reviewDetail(int review_bno) throws Exception;
	
	// 리뷰 수정	
	public int reviewUpdate(ReviewDTO reviewDTO) throws Exception;

	// 리뷰 삭제
	public int reviewDelete(int review_bno) throws Exception;

	// 리뷰에 해당하는 댓글 등록하기
	public int reviewCommentRegister(ReviewCommentDTO reviewCommentDTO) throws Exception;
	
	// 게시글 번호에 해당하는 댓글 수 구하기
	public int commentListCount(int review_bno) throws Exception;
	
	public List<ReviewCommentDTO> commentList(int review_bno) throws Exception;
	
	// 리뷰 리스트 제목에 댓글 수 표시
	public int updateReplyCount(int review_bno) throws Exception;
	
	// 댓글 번호에 해당하는 댓글 삭제하기
	public int replyDelete(int imsi_bno) throws Exception;	
	
	// 댓글 번호에 해당하는 댓글 수정하기
	public int replyUpdate(ReviewCommentDTO reviewCommentDTO) throws Exception;

	// 작성한 글의 번호를 가져온다.
	public int review_bno(ReviewDTO reviewDTO)throws Exception;

	// 리뷰사진등록
	public int imagesRegister(ReviewImagesDTO reviewImagesDTO)throws Exception;
	
	// 리뷰 사진리스트 가져오기
	public ReviewImagesDTO ImagesName(int review_bno)throws Exception; 	
	
	// 리뷰이미지 수정
	public int imagesUpdate(ReviewImagesDTO reviewImagesDTO) throws Exception;
	
	//리뷰번호 해당하는 리뷰번호 전체를 가져온다
	public ReviewImagesDTO getImagesName(int review_bno) throws Exception;

	//리뷰 등록여부
	public int reviewYN(Map<String,Object> param)throws Exception;
	
	//리뷰등록 상품 타입이름가져오기
	public ProductDTO productReviewdata(String product_code)throws Exception;

	//리뷰별점 합계
	public int star_sum(ReviewDTO reviewDTO)throws Exception;
	
	//리뷰별점 평균
	public int star_avg(ReviewDTO reviewDTO)throws Exception;
	
	// 리스트에 이미지 뿌려주기 
	public String ImagesName1(int review_bno) throws Exception;
	

	
	
	
	

}
