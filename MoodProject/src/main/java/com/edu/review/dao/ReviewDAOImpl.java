package com.edu.review.dao;

import java.util.List; 

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.common.util.SearchCriteria;
import com.edu.product.dto.ProductDTO;
import com.edu.review.dto.ReviewCommentDTO;
import com.edu.review.dto.ReviewDTO;
import com.edu.review.dto.ReviewImagesDTO;

@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	@Inject 
	private static String Namespace = "com.edu.review";
	
	// 리뷰 목록 보여주기
	@Override
	public List<ReviewDTO> reviewList(SearchCriteria sCri) throws Exception {
		
		return sqlSession.selectList(Namespace +  ".reviewList", sCri);
	}
	
	// 전체 페이지 가져오기
	@Override
	public int reviewListTotalCount(SearchCriteria sCri) throws Exception {
	
		return sqlSession.selectOne(Namespace + ".reviewListTotalCount", sCri);
	}
	
	// 리뷰 전체 목록 가져오기 
	@Override
	public List<ReviewDTO> reviewTotalList() throws Exception {
		List<ReviewDTO> reviewList = sqlSession.selectList(Namespace + ".totalList");
		
		return reviewList;
	}
	
	// 리뷰 등록하기
	@Override
	public int reviewRegister(ReviewDTO reviewDTO) throws Exception {
		
		System.out.println("ReviewDAOImpl 리뷰 등록하기");
		
		return sqlSession.insert(Namespace + ".reviewRegister", reviewDTO);
	}
	
	// productList 가져오기
	@Override
	public List<ProductDTO> productList(ProductDTO productDTO) throws Exception {
		
		return sqlSession.selectList(Namespace + ".productList", productDTO);
	}
	
	// 타입에 따른 상품명 찾기
	@Override
	public List<ProductDTO> searchname(String produt_type) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".searchname", produt_type);
	}
	
	// 타입에 따른 상품명 찾기2
	@Override
	public List<ProductDTO> searchname2(String produt_type) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".searchname2", produt_type);
	}
	
	// 리뷰 상세페이지 보여주기 
	@Override
	public ReviewDTO reviewDetail(int review_bno) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".detail", review_bno);
	}

	// 리뷰 수정	
	@Override
	public int reviewUpdate(ReviewDTO reviewDTO) throws Exception {
		
		return sqlSession.update(Namespace + ".updateReview", reviewDTO);
	}

	// 리뷰 삭제
	@Override
	public int reviewDelete(int review_bno) throws Exception {
		
		return sqlSession.delete(Namespace + ".deleteReview", review_bno);
	}

	// 리뷰에 해당하는 댓글 등록하기
	@Override
	public int reviewCommentRegister(ReviewCommentDTO reviewCommentDTO) throws Exception {
		
		return sqlSession.insert(Namespace + ".reviewCommentRegister", reviewCommentDTO);
	}

	// 게시글 번호에 해당하는 댓글 수 구하기
	@Override
	public int commentListCount(int review_bno) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".commentListCount", review_bno);
	}

	@Override
	public List<ReviewCommentDTO> commentList(int review_bno) throws Exception {
		
		return sqlSession.selectList(Namespace + ".commentList", review_bno);
	}
	
	// 리뷰 리스트 제목에 댓글 수 표시
	@Override
	public int updateReplyCount(int review_bno) throws Exception {
		
		return sqlSession.update(Namespace + ".updateReplyCount", review_bno);
	}
	
	// 댓글 번호에 해당하는 댓글 삭제하기
	@Override
	public int replyDelete(int imsi_bno) throws Exception {
		
		return sqlSession.delete(Namespace + ".replyDelete", imsi_bno);
	}
	
	// 댓글 번호에 해당하는 댓글 수정하기
	@Override
	public int replyUpdate(ReviewCommentDTO reviewCommentDTO) throws Exception {
		
		return sqlSession.update(Namespace + ".replyUpdate", reviewCommentDTO);
	}

	// 작성한 글의 번호를 가져온다.
	@Override
	public int review_bno(ReviewDTO reviewDTO) throws Exception {
		System.out.println("ReviewDAOImpl 글번호" + reviewDTO);
		return sqlSession.selectOne(Namespace + ".review_bno",reviewDTO);
	}

	// 리뷰사진등록
	@Override
	public int imagesRegister(ReviewImagesDTO reviewImagesDTO) throws Exception {
		System.out.println("ReviewDAOImpl 리뷰사진등록" + reviewImagesDTO);
		return sqlSession.insert(Namespace + ".imagesRegister",reviewImagesDTO);
	}

	// 리뷰 사진리스트 가져오기
	@Override
	public ReviewImagesDTO ImagesName(int review_bno) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".ImagesName", review_bno);
	}

	// 리뷰이미지 수정
	@Override
	public int imagesUpdate(ReviewImagesDTO reviewImagesDTO) throws Exception {
		
		System.out.println("ReviewDAOImpl 리뷰이미지 수정" + reviewImagesDTO);
		
		return sqlSession.update(Namespace +".imagesUpdate", reviewImagesDTO );
	}
	
	//리뷰번호 해당하는 리뷰번호 전체를 가져온다
	@Override
	public ReviewImagesDTO getImagesName(int review_bno) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".images", review_bno);
	}
	
	
	
	

}
