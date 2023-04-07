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
	public ModelAndView reviewRegisterForm(String userID) throws Exception {
		
		logger.info("ReviewController 리뷰 등록화면 불러오기");
		System.out.println(userID);
		
		ModelAndView mav = new ModelAndView();
		
		List<ProductDTO> productList = reviewService.productList(userID);
		mav.addObject("productList", productList);

		return mav;	
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 등록하기
	//-----------------------------------------------------------------------------------------------------------	
	@ResponseBody
	@RequestMapping(value = "/reveiwRegister", method = RequestMethod.GET)
	public String reviewRegister(ReviewDTO reviewDTO) throws Exception {
		
		System.out.println("ReviewController 리뷰 등록하기");
		
	
		if(reviewService.reviewRegister(reviewDTO) == 1) {
			return "Y";
		}else {
			return "N";
		}

	}
	
	
	
	
	
}
