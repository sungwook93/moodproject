package com.edu.order.service;

import java.util.List;

import com.edu.order.dto.CartDTO;

public interface OrderService {
	//장바구니에 담은 상품 보여주기
	public List<CartDTO> cartList(String userID) throws Exception;
	
	//장바구니 정보 등록하기
	public int addCart(CartDTO cartDTO) throws Exception;

	//해당상품이 장바구니에 이미 들어있는지 확인한다.
	public int checkcart(CartDTO cartDTO) throws Exception;
}
