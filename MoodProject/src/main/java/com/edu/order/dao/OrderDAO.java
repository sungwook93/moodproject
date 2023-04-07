package com.edu.order.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.edu.member.dto.MemberDTO;
import com.edu.order.dto.CartDTO;

public interface OrderDAO {
	
	//장바구니 리스트보여주기
	public List<CartDTO> cartList(String userID) throws Exception;
	
	//장바구니 담기
	public int addCart(CartDTO cartDTO) throws Exception;

	//해당상품이 장바구니에 이미 들어있는지 확인한다.
	public int checkcart(CartDTO cartDTO)throws Exception;
	
	//수량 변경
	public int countUpdate(CartDTO cartDTO)throws Exception;
	
	//카트넘버에 해당하는 정보 가져오기
	public CartDTO bills(String cart_num)throws Exception;
}
