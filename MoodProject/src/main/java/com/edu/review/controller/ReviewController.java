package com.edu.review.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.common.util.PageMaker;
import com.edu.common.util.SearchCriteria;
import com.edu.order.dto.CartDTO;
import com.edu.product.dto.ProductDTO;
import com.edu.review.dao.ReviewDAO;
import com.edu.review.dto.ReviewDTO;
import com.edu.review.service.ReviewService;


@Controller
@RequestMapping(value = "/review/*")
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Inject
	private ReviewService reviewService;
	
	@Inject
	private	ReviewDAO reviewDAO;

	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 목록 보여주기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/reviewList", method = RequestMethod.GET)
	public ModelAndView reviewList(SearchCriteria sCri) throws Exception {
		
		ModelAndView mav = new ModelAndView("/review/reviewList");
		
		// 리뷰 목록 보여주기
		List<ReviewDTO> reviewList = reviewService.reviewList(sCri);
		mav.addObject("reviewList", reviewList);
		
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
	public ModelAndView reviewRegisterForm(ProductDTO productDTO) throws Exception {
		
		logger.info("ReviewController 리뷰 등록화면 불러오기");
		System.out.println(productDTO);
		
		ModelAndView mav = new ModelAndView();
		
		List<ProductDTO> productList = reviewService.productList(productDTO);
		mav.addObject("productList", productList);

		return mav;	
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 등록하기
	//-----------------------------------------------------------------------------------------------------------	
	@ResponseBody
	@RequestMapping(value = "/reviewRegister", method = RequestMethod.POST)
	public String reviewRegister(ReviewDTO reviewDTO) throws Exception {
		
		System.out.println("ReviewController 리뷰 등록하기");
		
	
		if(reviewService.reviewRegister(reviewDTO) == 1) {
			return "Y";
		}else {
			return "N";
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
	// 리뷰 상세페이지 보여주기 
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value = "/reviewDetail", method = RequestMethod.GET)
	public ModelAndView reviewDetail(int review_bno) throws Exception {
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject(reviewService.reviewDetail(review_bno));
		
		
		return mav;
		
	}
	
	
}
