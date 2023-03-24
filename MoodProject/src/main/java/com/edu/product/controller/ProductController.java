package com.edu.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.common.util.ProductCriteria;
import com.edu.product.dto.ProductDTO;
import com.edu.product.service.ProductService;


@Controller
@RequestMapping(value="/product/*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;
	
	//카테고리에 따른(상품 타입) 상품 리스트 페이지 이동 + 정렬 방식에 따른 상품 리스트 데이터 함께 넘기기기기
	@RequestMapping(value="productList", method=RequestMethod.GET)
	public ModelAndView productList(ProductCriteria pCri)throws Exception{		
		logger.info("ProductController의 productList 불러오기....");
		System.out.println("상품타입: " + pCri.getProduct_type() + "상품색상: " + pCri.getProduct_color() + ", 현재페이지: " + pCri.getPage() 
							+ ", 정렬타입: " + pCri.getArray_type());
		
		ModelAndView mav = new ModelAndView(); 
		
		//상품타입에 해당하는 상품 리스트를 가져와서 model에 담는다.
		List<ProductDTO> productList = productService.productList(pCri);
		
		mav.setViewName("/product/productList");
		
		return mav;
	}//end - public ModelAndView productList()throws Exception
	
	
}
