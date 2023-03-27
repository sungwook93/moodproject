package com.edu.product.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.common.util.ProductCriteria;
import com.edu.product.dto.ProductDTO;
import com.edu.product.service.ProductServiceImpl;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Inject
	private SqlSession sqlSession;
	
	private static String Namespace = "com.edu.product";
	
	// 여러가지 조건에 맞는 상품리스트 발급
	@Override
	public List<ProductDTO> productList(Map<String, Object> param) throws Exception {
		logger.info("ProductDAOImpl의 productTypeList 불러오기....");
		System.out.println(param);
		
		return sqlSession.selectList(Namespace + ".typeList", param);
	}
	
	// 여러가지 조건에 맞는 상품리스트의 전체 수를 보내준다.
	@Override
	public int totalCount(Map<String, Object> param) throws Exception {
		logger.info("ProductDAOImpl의 totalCount 불러오기....");
		
		return sqlSession.selectOne(Namespace + ".totalCount", param);
	}

	// 리스트에 쓰일 대표이미지를 가지고 온다.(ImageController에서)
	@Override
	public String ImageName(String name) throws Exception {
		logger.info("ProductDAOImpl의 대표이미지 불러오기....");
		return sqlSession.selectOne(Namespace + ".ImageName", name);
	}

	
	
	
}
