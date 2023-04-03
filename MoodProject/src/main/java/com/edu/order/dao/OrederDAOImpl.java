package com.edu.order.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.member.dao.MemberDAOImpl;
import com.edu.order.dto.CartDTO;

@Repository("orderDAO")
public class OrederDAOImpl implements OrderDAO {
	
	@Autowired
	private SqlSession sqlSession;

	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	private	static final String Namespace = "com.edu.order";
	
	
	
	//장바구니 상품 리스트 보여주기
	@Override
	public List<CartDTO> cartList(String userID) throws Exception {
		
		
		logger.info("MemberDAOImpl 장바구니 리스트 보여주기");
		
		return sqlSession.selectList(Namespace + ".cartList", userID);
	}


	//장바구니 담기
	@Override
	public int addCart(CartDTO cartDTO) throws Exception {
		
		logger.info("MemberDAOImpl 장바구니 담기");
		
		return sqlSession.insert(Namespace + ".cartList", cartDTO);
	}

}
