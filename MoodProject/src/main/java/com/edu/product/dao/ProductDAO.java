package com.edu.product.dao;

import java.util.List;

import com.edu.common.util.ProductCriteria;
import com.edu.product.dto.ProductDTO;

public interface ProductDAO {

	public List<ProductDTO> productList(ProductCriteria pCri)throws Exception;
}
