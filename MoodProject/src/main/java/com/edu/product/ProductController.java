package com.edu.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/product/*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	//카테고리에 따른(상품 타입) 상품 리스트 페이지 이동 + 정렬 방식에 따른 상품 리스트 데이터 함께 넘기기기기
	@RequestMapping(value="productList", method=RequestMethod.GET)
	public ModelAndView productList()throws Exception{		
		
		ModelAndView mav = new ModelAndView(); 
		mav.setViewName("/product/productSearchResult");
		
		
		return mav;
	}
	
	
}
