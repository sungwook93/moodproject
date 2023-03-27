package com.edu.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edu.common.util.ProductCriteria;
import com.edu.product.dto.ProductDTO;

public interface ProductService {

	// 여러가지 조건에 맞는 상품리스트 발급
	public List<ProductDTO> productList(Map<String, Object> param) throws Exception;
	
	// 여러가지 조건에 맞는 상품리스트의 전체 수를 보내준다.
	public int totalCount(Map<String, Object> param)throws Exception;
	
}
