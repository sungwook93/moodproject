package com.edu.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.common.util.ProductCriteria;
import com.edu.product.dao.ProductDAO;
import com.edu.product.dto.ProductDTO;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductDAO productDAO;
	
	
	@Override
	public List<ProductDTO> productList(ProductCriteria pCri) throws Exception {
		logger.info("ProductServiceImpl의 productList 불러오기....");
		
		return productDAO.productList(pCri);
	}

}
