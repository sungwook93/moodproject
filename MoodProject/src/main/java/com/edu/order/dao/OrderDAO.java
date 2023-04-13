package com.edu.order.dao;

import java.util.List;			

import com.edu.order.dto.CartDTO;
import com.edu.order.dto.OrderDTO;
import com.edu.product.dto.ProductDTO;


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
	public CartDTO bills(int cart_num)throws Exception;
	
	//카트넘버들을 삭제한다.
	public int cartdelete(int cart_num)throws Exception;

	//장바구니 번호에 해당하는 장바구니 정보 가져오기
	public CartDTO cartNumList(int cartNum) throws Exception;
	
	//주문완료영역
	//가장 큰 주문번호 구하기
	public long getMaxOrderNum() throws Exception;
	
	//주문 완료 정보 등록하기
	public int addOrder(OrderDTO orderDTO) throws Exception;
	
	//주문 완료 상품 등록하기
	public int addOrderProduct(OrderDTO orderDTO) throws Exception;
	
	//해당 날짜의 주문건수 구하기
	public int orderDateCount(String date) throws Exception;
	
	//주문 완료 번호와 상품 코드에 해당하는 orderDTO 객체 가져오기 (t_orderProduct)
	public OrderDTO getOrderDetailDTO(OrderDTO orderDTO) throws Exception;

	
	//주문 완료 번호에 해당하는 상품 상세 정보를 oderDTO객체 리스트로 가져오기(t_orderProduct)
	public List<OrderDTO> getOrderDetail(long order_num) throws Exception;	
	
	//주문완료 번호에 해당하는 orderDTO 객체 가져오기(t_order)
	public OrderDTO getOrderDTO(long order_num) throws Exception;

	
	
	//주문완료영역 controller에서 바로타고 온부분
	//회원 아이디에 해당하는 모든 t_orderProduct 데이터 가져오기
	public List<OrderDTO> getOrderDetailById(String userID) throws Exception;



}
