package com.edu.product.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
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
	
	private static String Namespace = "con.edu.product";
	
	@Override
	public List<ProductDTO> productList(ProductCriteria pCri) throws Exception {
		logger.info("ProductDAOImpl의 productTypeList 불러오기....");
		
		return sqlSession.selectList(Namespace + ".typeList", pCri);
	}

	
	
	
}
