package com.edu.product.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.common.util.PageMaker;
import com.edu.common.util.ProductCriteria;
import com.edu.product.dao.ProductDAO;
import com.edu.product.dto.ImagesDTO;
import com.edu.product.dto.ProductDTO;
import com.edu.product.service.ProductService;


@Controller
@RequestMapping(value="/product/*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductDAO productDAO;
	
	//카테고리에 따른(상품 타입) 상품 리스트 페이지 이동 + 정렬 방식에 따른 상품 리스트 데이터 함께 넘기기
	@RequestMapping(value="productList", method=RequestMethod.GET)
	public ModelAndView productList(ProductCriteria pCri)throws Exception{		
		logger.info("ProductController의 productList 불러오기....");
		System.out.println("상품타입: " + pCri.getProduct_type() +"상품색상: " 
		+ pCri.getProduct_color() + ", 현재페이지: " + pCri.getPage());
		
		ModelAndView mav = new ModelAndView(); 
		
		// map 형식을 담기위해 설정해둔다. 이유는 type쪽을 배열로 넘겨주기위해서 가공을 해야한다.
		// type을 배열로 담을려는 이유는 mapper에 where in을 넣어야 하는데 mybatis 부분의 where in은 foreach를 써야하므로
		// 문자열을 배열로 바꾼다.
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("perPageNum", 9);
		param.put("page", pCri.getPage());
		param.put("array_type", pCri.getArray_type());
		param.put("keyword", pCri.getKeyword());
		
		//들어오는 문자열을 배열로 만들고 map에다가 담아준다.
		//color
		String colorList[] = null;
		colorList = pCri.getProduct_color().split(",");
		param.put("colorList", colorList);
		
		//type
		String typeList[] = null;
		typeList = pCri.getProduct_type().split(",");
		param.put("typeList", typeList);				
		
	
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
		
		
		
		//상품 색상, 검색 키워드, 상품 타입, 상품수를 담아서 보내준다(page 처리를 위해)
		mav.addObject("color", pCri.getProduct_color());
		mav.addObject("type" , pCri.getProduct_type());
		mav.addObject("array" , pCri.getArray_type());
		mav.addObject("totalCount", pageMaker.getTotalCount());
		if(pCri.getKeyword() != null) {
			mav.addObject("keyword", pCri.getKeyword());
		}
		
		mav.setViewName("/product/productList");
		
		return mav;
	}//end - public ModelAndView productList()throws Exception
	
	//상품 상세페이지 이동
	@RequestMapping(value="/productDetail", method=RequestMethod.GET)
	public ModelAndView productDetail(String product_code)throws Exception{
		
		System.out.println("productcontroller productDetail 불러오기" + product_code);
		
		ModelAndView mav = new ModelAndView();
		
		//상품 코드에 해당하는 상제정보를 model에 담는다
		ProductDTO productDTO = productService.productDetail(product_code);
		
		//System.out.println("상품 색상" + productDTO.getProduct_color());
		
		//추천상품리스트를 가져온다.
		List<ProductDTO> recommendlist = productService.productrecommend(productDTO);
	
		System.out.println ("추천상품 구경해보자구나 ===>" +recommendlist);
		// 추천상품 섞기
		Collections.shuffle(recommendlist);
		// 추천상품 리스트 뿌려주기
		mav.addObject("recommendlist", recommendlist);
		
		//해당 상품코드에 대한 정부 뿌려주기
		mav.addObject("product", productDTO);
		
		//상품코드에 해당하는 이미지 이름을 가져온다.
		ImagesDTO imagesDTO = productService.ImagesName(product_code);

		//상품 코드에 해당하는 이미지리스트를 model에 담아준다.
		mav.addObject("imagesList", imagesDTO);
		
		//정보를 보낼 주소를 세팅한다.
		mav.setViewName("/product/productDetail");
		
		return mav;
	}
	
	
	//상품 등록하기 페이지로 이동
	@RequestMapping(value="/productRegisterForm", method=RequestMethod.GET)
	public String productRegisterForm() throws Exception{
		System.out.println("ProductController의 productRegisterForm 불러오기.... ");
		
		return "/product/productRegisterForm";
	}
	
	//상품 등록하기
	//ajax를 사용하므로 값을 그곳에 돌려줄것이므로 responsebody를 사용한다.
	@ResponseBody
	@RequestMapping(value="/productRegister", method=RequestMethod.POST)
	public String productRegister(ProductDTO productDTO)throws Exception{
		System.out.println("ProductController의 productRegister시작하기");
		
		//상품 분류를 위해 해당 상품의 타입의 가장 마지막 상품코드를 가져온다.
		String lastCode = productService.getProductCode(productDTO.getProduct_type());
		
		System.out.println("마지막 상품코드 ==>" +lastCode );
		
		//상품코드를 만들기 위해서 숫자부분을 분리해서 더해준다.
		String type = lastCode.substring(0, 1);
		String lastCode_number = lastCode.substring(1);
		
		//새로운 코드부분을 구해준다.
		int newCode_number = Integer.parseInt(lastCode_number) + 1;
		
		String product_code = "";
		
		//값에 따른 코드를 만든다.
		if(newCode_number <= 9) {
			product_code = type + "00" + newCode_number;
		}else if(newCode_number <= 99) {
			product_code = type + "0" + newCode_number;
		}else {
			product_code = type + newCode_number;
		}
		
		System.out.println(product_code);
		
		//productDTO에 새로 만든 상품코드 세팅
		productDTO.setProduct_code(product_code);
		
		//받아온 상품코드로 상품을 등록한다.
		int result = productService.productRegister(productDTO);
		
		if(result <= 0) { //실패하면
			System.out.println("상품등록 실패");
			return "0";
		}else //상품등록에성공하면
		// 이미지 등록하는데 상품코드가 필요하므로 상품코드를 돌려준다.
		return product_code;
	}
	
	//상품 수정페이지 이동
	@RequestMapping(value="/productUpdateForm", method=RequestMethod.GET)
	public ModelAndView productUpdateForm(String product_code) throws Exception{
		System.out.println("ProductController의 상품수정페이지 이동");
		
		ModelAndView mav = new ModelAndView();
		
		//상품 코드에 해당하는 상품DTO를 보내준다.
		mav.addObject("product", productService.productDetail(product_code));
		
		//주소 세팅
		mav.setViewName("/product/productUpdateForm");

		return mav;
	}
	
	//상품 수정하기
	@ResponseBody
	@RequestMapping(value="/productUpdate", method=RequestMethod.POST)
	public String productUpdate(ProductDTO productDTO)throws Exception{
		System.out.println("ProductController의 productRegister 불러오기.... " + productDTO);	
		
		//업데이트를 한다.
		int result = productService.productUpdate(productDTO);
		
		if (result <= 0) { // 실패했으면
			return "0";
		}else // 이미지 수정한값을 위해 코드를 가져간다.
		System.out.println("상품수정 코드 ==>" + productDTO.getProduct_code());
			
		return productDTO.getProduct_code();
	}
	
	//상품 삭제하기
	@ResponseBody
	@RequestMapping(value="/productDelete", method=RequestMethod.POST)
	public String productDelete(String product_code) throws Exception{
	
		System.out.println("ajax에서 넘어온 값" + product_code);
		
		String product_type = productDAO.productfindType(product_code);
		
		int result = productService.productDelete(product_code);
		
		
		
		
		if (result <= 0) {// 실패함
			return "N";
		}else
		return product_type;
	}
	
	
}
