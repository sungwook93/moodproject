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
import com.edu.product.dto.ImagesDTO;
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

	// 상품코드에 맞는 정보를 가져온다.
	@Override
	public ProductDTO productDetail(String product_code) throws Exception {
		System.out.println("ProductDAOImpl의 productDetail 불러오기....");
		
		return sqlSession.selectOne(Namespace + ".productDetail",product_code);
	}

	// 상품코드에 맞는 이미지이름을 가져온다.
	@Override
	public ImagesDTO ImagesName(String product_code) throws Exception {
		System.out.println("ProductDAOImpl의 ImagesName 불러오기....");
		return sqlSession.selectOne(Namespace + ".ImagesName",product_code );
	}

	//추천상품리스트를 가져온다.
	@Override
	public List<ProductDTO> productrecommend(ProductDTO productDTO) throws Exception {
		System.out.println("ProductDAOImpl의 추천상품리스트를 불러오기....");
		return sqlSession.selectList(Namespace + ".productrecommend",productDTO);
	}
	
	//마지막 상품코드 가져오기
	@Override
	public String getProductCode(String product_type) throws Exception {
		System.out.println("ProductDAOImpl의 마지막 상품코드를 불러오기....");
		return sqlSession.selectOne(Namespace + ".getProductCode",product_type);
	}

	// 상품을 등록한다.
	@Override
	public int productRegister(ProductDTO productDTO) throws Exception {
		System.out.println("ProductDAOImpl의 상품을 등록한다.....");
		return sqlSession.insert(Namespace + ".productRegister" , productDTO);
	}

	//이미지 DTO 입력
	@Override
	public int imagesRegister(ImagesDTO imagesDTO) throws Exception {
		System.out.println("ProductDAOImpl의 이미지 DTO 입력.....");
		return sqlSession.insert(Namespace + ".imagesRegister", imagesDTO);
	}
	

	
	
	
}
