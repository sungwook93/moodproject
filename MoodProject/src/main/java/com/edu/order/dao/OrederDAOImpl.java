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
		
		System.out.println("OrderDAO 장바구니 담기");
		
		return sqlSession.insert(Namespace + ".addCart", cartDTO);
	}

	//해당상품이 장바구니에 이미 들어있는지 확인한다.
	@Override
	public int checkcart(CartDTO cartDTO) throws Exception {
		System.out.println("OrderDAO 해당상품이 장바구니에 이미 들어있는지 확인한다.");
		return sqlSession.selectOne(Namespace + ".checkcart", cartDTO);
	}

	//넘어온 장바구니 List를 구해서 보내준다
	@Override
	public int userCartList(CartDTO cartNum) throws Exception {
		
		return sqlSession.insert(Namespace + ".userCartList", cartNum);
	}

	//상세페이지에서 보여줄 합계금액을 보내준다.
	@Override
	public int orderSum(CartDTO cartNum) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".orderSum", cartNum);
	}

}
