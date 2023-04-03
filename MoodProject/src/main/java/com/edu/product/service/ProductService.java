package com.edu.product.service;


import java.util.List;
import java.util.Map;


import com.edu.product.dto.ImagesDTO;
import com.edu.product.dto.ProductDTO;

public interface ProductService {

	// 여러가지 조건에 맞는 상품리스트 발급
	public List<ProductDTO> productList(Map<String, Object> param) throws Exception;
	
	// 여러가지 조건에 맞는 상품리스트의 전체 수를 보내준다.
	public int totalCount(Map<String, Object> param)throws Exception;
	
	// 상품코드에 맞는 정보를 가져온다.
	public ProductDTO productDetail(String product_code)throws Exception;
	
	//상품 코드에 해당하는 이미지이름을 가져온다.
	public ImagesDTO ImagesName(String product_code)throws Exception;
	
	//추천상품리스트를 가져온다.
	public List<ProductDTO> productrecommend(ProductDTO productDTO)throws Exception;
	
	//마지막 상품코드 가져오기
	public String getProductCode(String product_type) throws Exception;
	
	//상품 등록하기
	public int productRegister(ProductDTO productDTO)throws Exception;
	
}
