package com.edu.review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.board.dto.CommentDTO;
import com.edu.common.util.PageMaker;
import com.edu.common.util.SearchCriteria;
import com.edu.order.dto.CartDTO;
import com.edu.order.dto.OrderDTO;
import com.edu.order.service.OrderService;
import com.edu.product.dto.ImagesDTO;
import com.edu.product.dto.ProductDTO;
import com.edu.review.dao.ReviewDAO;
import com.edu.review.dto.ReviewCommentDTO;
import com.edu.review.dto.ReviewDTO;
import com.edu.review.dto.ReviewImagesDTO;
import com.edu.review.service.ReviewService;


@Controller
@RequestMapping(value = "/review/*")
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Inject
	private ReviewService reviewService;
	
	@Inject
	private	ReviewDAO reviewDAO;
	
	@Inject
	private OrderService orderService;

	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 목록 보여주기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/reviewList", method = RequestMethod.GET)
	public ModelAndView reviewList(SearchCriteria sCri) throws Exception {
		
		ModelAndView mav = new ModelAndView("/review/reviewList");
		
		// 리뷰 목록 보여주기
		List<ReviewDTO> reviewList = reviewService.reviewList(sCri);
		mav.addObject("reviewList", reviewList);
		
		mav.addObject("searchType",	sCri.getSearchType());
		mav.addObject("keyword",	sCri.getKeyword());
		
		// 전체 페이지 가져오기
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(sCri);
		
		pageMaker.setTotalCount(reviewService.reviewListTotalCount(sCri));
		
		mav.addObject("pageMaker", pageMaker);

		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 등록화면 불러오기
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value="/reviewRegisterForm", method = RequestMethod.GET)
	public ModelAndView reviewRegisterForm(long order_num,String product_code,SearchCriteria sCri) throws Exception {
		
		logger.info("ReviewController 리뷰 등록화면 불러오기");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("order_num",order_num);
		mav.addObject("product_code",product_code);
		
		//리뷰등록 상품 타입이름가져오기
		ProductDTO productDTO=reviewDAO.productReviewdata(product_code);
		
		mav.addObject("productDTO",productDTO);
		mav.addObject("reviewList", reviewService.reviewList(sCri));
		mav.setViewName("/review/reviewRegisterForm");
		return mav;	
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 등록하기
	//-----------------------------------------------------------------------------------------------------------	
	@ResponseBody
	@RequestMapping(value = "/reviewRegister", method = RequestMethod.POST)
	public int reviewRegister(long order_num,String product_code,ReviewDTO reviewDTO) throws Exception {
		
		System.out.println("ReviewController 리뷰 등록하기");
		
		System.out.println("order_num:"+order_num+",product_code:"+product_code);
		
		Map<String,Object> param= new HashMap<String,Object>();
		param.put("product_code",product_code);
		param.put("order_num",order_num);
		//리뷰 등록여부
		reviewDAO.reviewYN(param);
		
		if(reviewService.reviewRegister(reviewDTO) == 1) {
			
		reviewDAO.star_sum(reviewDTO);
		reviewDAO.star_avg(reviewDTO);
			
		int result	 = reviewDAO.review_bno(reviewDTO);
		
		System.out.println(result);
			
			return result;
		}else {
			return 0;
		}

	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 타입에 따른 상품명 찾기
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value="/searchname" , method=RequestMethod.GET)
	public ModelAndView searchname(String produt_type) throws Exception{
		
		System.out.println("ReviewController 타입에 따른 상품명 찾기" + produt_type);
		ModelAndView mav = new ModelAndView();
		
		List<ProductDTO> productList = reviewService.searchname(produt_type);
		mav.addObject("produt_type", produt_type);
		mav.addObject("productList", productList);
		mav.setViewName("/review/reviewRegisterForm");
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 타입에 따른 상품명 찾기2
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value="/searchname2" , method=RequestMethod.GET)
	public ModelAndView searchname2(String produt_type) throws Exception{
		
		System.out.println("ReviewController 타입에 따른 상품명 찾기" + produt_type);
		ModelAndView mav = new ModelAndView();
		
		List<ProductDTO> productList = reviewService.searchname(produt_type);
		mav.addObject("produt_type", produt_type);
		mav.addObject("productList", productList);
		mav.setViewName("/review/reviewUpdateForm");
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 상세페이지 보여주기 
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value = "/reviewDetail", method = RequestMethod.GET)
	public ModelAndView reviewDetail(int review_bno) throws Exception {
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject(reviewService.reviewDetail(review_bno));
		
		// 리뷰 상세페이지 보여줄 때, 댓글이 있으면 댓글도 보여주기  
		if(reviewService.commentListCount(review_bno) != 0) {
			mav.addObject("commentList", reviewService.commentList(review_bno));
		}
		// 리뷰 리스트 제목에 댓글 수 표시
		mav.addObject(reviewService.updateReplyCount(review_bno)); 
		
		//리뷰 번호를 이용해 이미지 리스트를 가져온다.
		ReviewImagesDTO reviewimagesDTO = reviewDAO.ImagesName(review_bno);
		
		//이미지 리스트릉 모델에 담는다
		mav.addObject("imagesList" , reviewimagesDTO);
		
		
		return mav;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 수정화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/reviewUpdateForm", method = RequestMethod.GET)
	public ModelAndView reviewUpdateForm(int review_bno) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		ReviewDTO reviewDTO = reviewService.reviewDetail(review_bno);
		mav.addObject("reviewDTO", reviewDTO);
		
		mav.setViewName("/review/reviewUpdateForm");
		
		
		
		return mav;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 수정
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "reviewUpdate", method = RequestMethod.POST)
	public String reviewUpdate(ReviewDTO reviewDTO) throws Exception {
		
		if(reviewService.reviewUpdate(reviewDTO) == 1) {
			return "Y";
		}else {
			return "N";
		}
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 삭제
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "reviewDelete", method = RequestMethod.POST)
	public String reviewDelete(int review_bno, int[] reply_bno) throws Exception {
		
		int count = 0;
		
		if(reply_bno != null) {
			for(int i = 0; i < reply_bno.length; i++) {
				count += reviewService.replyDelete(reply_bno[i]);
			}
			if(count == reply_bno.length) { //댓글을 모두 삭제했으면
				//리뷰 삭제한다.
				if(reviewService.reviewDelete(review_bno) == 1) {
					return "Y";
				} else {
					return "N";
				}
			} else {
				return "N";
			}
		}else {
			if(reviewService.reviewDelete(review_bno) == 1) {
				return "Y";
			}else {
				return "N";
			}
		}
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰에 해당하는 댓글 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "reviewCommentRegister", method = RequestMethod.POST)
	public String reviewCommentRegister(ReviewCommentDTO reviewCommentDTO) throws Exception{
		
		if(reviewService.reviewCommentRegister(reviewCommentDTO) == 1) {
			return "Y";
		}else {
			return "N";
		}
	
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 댓글 번호에 해당하는 댓글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/replyDelete", method = RequestMethod.POST)
	public String replyDelete(int imsi_bno, int review_bno) throws Exception {
			
		if(reviewService.replyDelete(imsi_bno) == 1) {
				return "Y";
			} else {
				return "N";
			}
			
	} 
	
	//-----------------------------------------------------------------------------------------------------------
	// 댓글 번호에 해당하는 댓글 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/replyUpdate", method = RequestMethod.POST)
	public String replyUpdate(ReviewCommentDTO reviewCommentDTO) throws Exception {
							
		if(reviewService.replyUpdate(reviewCommentDTO) == 1) {
				return "Y";
			} else {
				return "N";
			}
			
	} 
}
