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
import com.edu.product.dto.ImagesDTO;
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
		mav.addObject("product", productDTO);
		
		//이미지의 이름을 담을 리스트를 만든다. 상품상세페이지에 foreach 를 돌리기위해
		List<String> imagesList = new ArrayList<String>();
		
		//상품코드에 해당하는 이미지 이름을 가져온다.
		ImagesDTO imagesDTO = productService.ImagesName(product_code);
		// 이미지 이름을 imagesList리스트에 담는다.
		if(imagesDTO.getImages01() != null) imagesList.add(imagesDTO.getImages01());
		if(imagesDTO.getImages02() != null) imagesList.add(imagesDTO.getImages02());
		if(imagesDTO.getImages03() != null) imagesList.add(imagesDTO.getImages03());
		if(imagesDTO.getImages04() != null) imagesList.add(imagesDTO.getImages04());
		
		//상품 코드에 해당하는 이미지리스트를 model에 담아준다.
		mav.addObject("imagesList", imagesList);
		
		//정보를 보낼 주소를 세팅한다.
		mav.setViewName("/product/productDetail");
		
		return mav;
	}
	
	
	
}
