package com.edu.product.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.common.util.ProductCriteria;
import com.edu.product.dao.ProductDAO;
import com.edu.product.dto.ImagesDTO;
import com.edu.product.dto.ProductDTO;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductDAO productDAO;
	
	// 여러가지 조건에 맞는 상품리스트 발급
	@Override
	public List<ProductDTO> productList(Map<String, Object> param) throws Exception {
		logger.info("ProductServiceImpl의 productList 불러오기....");
		
		return productDAO.productList(param);
	}

	// 여러가지 조건에 맞는 상품리스트의 전체 수를 보내준다.
	@Override
	public int totalCount(Map<String, Object> param) throws Exception {
		logger.info("ProductServiceImpl의 totalCount 불러오기....");
		
		return productDAO.totalCount(param);
	}
	
	// 상품코드에 맞는 정보를 가져온다.
	@Override
	public ProductDTO productDetail(String product_code) throws Exception {
		System.out.println("ProductServiceImpl의 productDetail 불러오기....");
		return productDAO.productDetail(product_code);
	}
	
	// 상품코드에 맞는 이미지이름을 가져온다.
	@Override
	public ImagesDTO ImagesName(String product_code) throws Exception {
		System.out.println("ProductServiceImpl의 ImagesName 불러오기....");
		
		return productDAO.ImagesName(product_code);
	}

	
	
}
