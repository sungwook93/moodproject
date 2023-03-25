package com.edu.product.dao;

import java.util.List;

import com.edu.common.util.ProductCriteria;
import com.edu.product.dto.ProductDTO;

public interface ProductDAO {
	
	// 여러가지 조건에 맞는 상품리스트 발급
	public List<ProductDTO> productList(ProductCriteria pCri)throws Exception;

	// 여러가지 조건에 맞는 상품리스트의 전체 수를 보내준다.
	public int totalCount(ProductCriteria pCri)throws Exception;
	
	// 리스트에 쓰일 대표이미지를 가지고 온다.(ImageController에서)
	public String ImageName(String name)throws Exception;
	
	
}