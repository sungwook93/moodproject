package com.edu.order.service;

import java.util.List;		

import com.edu.order.dto.CartDTO;
import com.edu.order.dto.OrderDTO;
import com.edu.product.dto.ProductDTO;

public interface OrderService {
	//장바구니에 담은 상품 보여주기
	public List<CartDTO> cartList(String userID) throws Exception;
	
	//장바구니 정보 등록하기
	public int addCart(CartDTO cartDTO) throws Exception;

	//해당상품이 장바구니에 이미 들어있는지 확인한다.
	public int checkcart(CartDTO cartDTO) throws Exception;
	
	//수량db에 변경하러 출발
	public int countUpdate(CartDTO cartDTO)throws Exception;

	//카트넘버에 해당하는 정보 가져오기
	public CartDTO bills(int cart_num)throws Exception;

	//카트넘버들을 삭제한다.
	public int cartdelete(int cart_num)throws Exception;
	
	//장바구니 번호에 해당하는 정보로 최종 금액 계산하기
	public int orderSum(int[] cartNum) throws Exception;
	
	//주문완료 영역
	
	//상품바로주문 카트넘받아오기
	public int addCartNum(CartDTO cartDTO) throws Exception;
	
	////주문 완료 테이블 번호 만들기
	public long getOrder_num() throws Exception;
	
	//주문 완료 정보 등록하고 해당 장바구니 삭제하기
	public int addOrder(int[] cartNumberList, OrderDTO orderDTO) throws Exception;
	
	//주문완료 번호에 해당하는 orderDTO 객체 가져오기
	public OrderDTO getOrderDTO(long order_num) throws Exception;

	//주문 완료 번호에 해당하는 상품 상세 정보를 oderDTO객체 리스트로 가져오기
	public List<OrderDTO> getOrderDetail(long order_num) throws Exception;

	//상품 디테일 리스트에 해당하는 정보로 최종 금액 계산하기
	public int orderListSum(List<OrderDTO> orderDetailList) throws Exception;


}
