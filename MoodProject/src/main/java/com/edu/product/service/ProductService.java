package com.edu.product.service;

import java.util.List;

import com.edu.common.util.ProductCriteria;
import com.edu.product.dto.ProductDTO;

public interface ProductService {

	public List<ProductDTO> productList(ProductCriteria pCri) throws Exception;
	
}
