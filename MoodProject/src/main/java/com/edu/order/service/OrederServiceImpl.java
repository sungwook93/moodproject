package com.edu.order.service;

import java.util.List;	

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.member.dao.MemberDAO;
import com.edu.member.service.MemberServiceImpl;
import com.edu.order.dao.OrderDAO;
import com.edu.order.dto.CartDTO;

@Service("orderService")
public class OrederServiceImpl implements OrderService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	
	@Autowired
	private OrderDAO orderDAO;
	
	//장바구니에 담은 상품 보여주기
	@Override
	public List<CartDTO> cartList(String userID) throws Exception {
		
		logger.info("OrderServiceImpl 장바구니에 담은 상품 보여주기 시작");
		
		return orderDAO.cartList(userID);
	}
	
	//해당상품 장바구니에 담기
	@Override
	public int addCart(CartDTO cartDTO) throws Exception {
		
		System.out.println("OrderService 장바구니 담기");
		
		return orderDAO.addCart(cartDTO);
	}

	//해당상품이 장바구니에 이미 들어있는지 확인한다.
	@Override
	public int checkcart(CartDTO cartDTO) throws Exception {
		System.out.println("OrderService 해당상품이 장바구니에 이미 들어있는지 확인한다.");
		
		return orderDAO.checkcart(cartDTO);
	}

	//수량변경
	@Override
	public int countUpdate(CartDTO cartDTO) throws Exception {
		System.out.println("OrderService 수량변경.");
		return orderDAO.countUpdate(cartDTO);
	}

	//카트넘버에 해당하는 정보 가져오기
	@Override
	public CartDTO bills(int cart_num) throws Exception {
		System.out.println("OrderService 카트넘버에 해당하는 정보 가져오기");
		return orderDAO.bills(cart_num);
	}
	
	
	
}
