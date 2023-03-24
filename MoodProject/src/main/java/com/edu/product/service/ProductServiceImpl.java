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
	
	// 여러가지 조건에 맞는 상품리스트 발급
	@Override
	public List<ProductDTO> productList(ProductCriteria pCri) throws Exception {
		logger.info("ProductServiceImpl의 productList 불러오기....");
		
		return productDAO.productList(pCri);
	}

	// 여러가지 조건에 맞는 상품리스트의 전체 수를 보내준다.
	@Override
	public int totalCount(ProductCriteria pCri) throws Exception {
		logger.info("ProductServiceImpl의 totalCount 불러오기....");
		
		return productDAO.totalCount(pCri);
	}

	
	
}
