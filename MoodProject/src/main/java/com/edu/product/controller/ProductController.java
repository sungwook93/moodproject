package com.edu.product.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.common.util.PageMaker;
import com.edu.common.util.ProductCriteria;
import com.edu.product.dto.ProductDTO;
import com.edu.product.service.ProductService;


@Controller
@RequestMapping(value="/product/*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;
	
	//카테고리에 따른(상품 타입) 상품 리스트 페이지 이동 + 정렬 방식에 따른 상품 리스트 데이터 함께 넘기기
	@RequestMapping(value="productList", method=RequestMethod.GET)
	public ModelAndView productList(ProductCriteria pCri)throws Exception{		
		logger.info("ProductController의 productList 불러오기....");
		System.out.println("상품타입: " + pCri.getProduct_type() +"상품색상: " + pCri.getProduct_color() + ", 현재페이지: " + pCri.getPage());
		
		ModelAndView mav = new ModelAndView(); 
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("product_color", pCri.getProduct_color());
		param.put("perPageNum", 9);
		param.put("page", pCri.getPage());
			
		
		String arrShopList[] = null;
		arrShopList = pCri.getProduct_type().split(",");

		param.put("arrShopList", arrShopList);				
		
	
		//상품조건에 해당하는 상품 리스트를 가져와서 model에 담는다.
		List<ProductDTO> productList = productService.productList(param);
		mav.addObject("productList", productList);
	
		System.out.println("productList mav: " + productList);
		
		//넘어온 현재 페이지와 해당 상품 전체 데이터 갯수를 pageMaker에 세팅하고 model담는다.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(pCri);
		pageMaker.setTotalCount(productService.totalCount(param));
		System.out.println("totalCount: " + pageMaker.getTotalCount());
		mav.addObject("pageMaker", pageMaker);
		
		
		//상품 색상, 검색 키워드, 상품 타입를 담아서 보내준다(script 처리를 위해)
		mav.addObject("color", pCri.getProduct_color());
		mav.addObject("product_type" , pCri.getProduct_type());
		if(pCri.getKeyword() != null) {
			mav.addObject("keyword", pCri.getKeyword());
		}
		
		mav.setViewName("/product/productList");
		
		return mav;
	}//end - public ModelAndView productList()throws Exception
	
	
}
