package com.edu.order.service;

import java.text.DecimalFormat;	
import java.util.Calendar;
import java.util.List;	

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.edu.member.service.MemberServiceImpl;
import com.edu.order.dao.OrderDAO;
import com.edu.order.dto.CartDTO;
import com.edu.order.dto.OrderDTO;
import com.edu.product.dto.ProductDTO;

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

	//카트넘버들을 삭제한다.
	@Override
	public int cartdelete(int cart_num) throws Exception {
		System.out.println("OrderService 카트넘버들을 삭제한다.");
		
		return orderDAO.cartdelete(cart_num);
	}
	
	//주문 상세 페이지에서 보여줄 합계 금액을 반환하는 메소드
	@Override
	public int orderSum(int[] cart_num) throws Exception {
		System.out.println("OrderServiceImpl의 orderSum()....");
			
		//계산해줄 합계 변수 준비
		int result = 0;
			
		//반복문을 통해 장바구니에 해당하는 정보를 가져온 다음 계산해서 result에 더해준다.
		for(int i = 0; i < cart_num.length; i++) {
			CartDTO cartDTO = orderDAO.cartNumList(cart_num[i]); //번호에 해당하는 장바구니 정보
				
			int price = cartDTO.getProduct_price(); //해당 상품 가격
			int amount = cartDTO.getProduct_amount(); //해당 상품 주문 수량
				
			result += (price * amount); //상품 가격과 수량을 곱해서 result에 누적한다.
		}
			
		return result;
	}
	
	//주문 완료 테이블 번호 만들기
	@Override
	public long getOrder_num() throws Exception {
		System.out.println("OrderServiceImpl의 getOrder_num()....");
		
		//현재 날짜를 구해서 DB의 날짜 형태로 만든다. ex)1999-01-01
		Calendar cal = Calendar.getInstance(); //날짜 정보를 얻기 위한 캘린더 인스턴스
		String date = cal.get(Calendar.YEAR) + "-";
		date += new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1) + "-";
		date += new DecimalFormat("00").format(cal.get(Calendar.DATE));
		System.out.println("현재 날짜: " + date);
		
		//현재 날짜에 해당하는 주문 건수가 없으면
		if(orderDAO.orderDateCount(date) == 0) {
			System.out.println("주문 건수 없음");
			
			//날짜뒤에 001을 붙이고 '-'를 제거한 뒤 숫자로 바꿔서 리턴한다.
			date = date + "001";
			date = date.replaceAll("[-]", "");
			long result = Long.parseLong(date);
			return result;
			
		} else { //건수가 있으면
			System.out.println("주문 건수 있음");
			
			//가장 큰 주문번호 + 1 한 값을 가져와 리턴한다.\
			return orderDAO.getMaxOrderNum();
			
		}
	}

	@Override
	public int addOrder(int[] cartNumberList, OrderDTO orderDTO) throws Exception {
		System.out.println("OrderServiceImpl의 addOrder().... 장바구니 번호: " + cartNumberList[0]);
		
		//배송 메모가 있을 경우 배송메모의 공백을 "_"로 치환해서 세팅해준다.
		if(orderDTO.getOrder_memo() != null) {
			orderDTO.setOrder_memo(orderDTO.getOrder_memo().replaceAll(" ", "_"));
		}
		
		//t_order 테이블에 먼저 데이터를 등록한다.
		if(orderDAO.addOrder(orderDTO) == 1) { //성공하면
			//System.out.println("확인!!!!!!!!!");
			//System.out.println(cartNumberList[0]+"!!!!!!!!!!!!!!!");
			int count = 0;
			
			
			
			//반복문을 통해 장바구니 번호에 해당하는 cartDTO를 가져와서 해당 데이터로 orderDTO를 세팅한 후
			for(int i = 0; i < cartNumberList.length; i++) {
				
				//장바구니 번호에 해당하는 cartDTO를 가져온다.
				//System.out.println("장바구니번호: " + cartNumberList[i]);
				CartDTO cartDTO = orderDAO.cartNumList(cartNumberList[i]);
				
				//
				
				//orderProductDTO에 값을 세팅한다.
				orderDTO.setProduct_code(cartDTO.getProduct_code());
				orderDTO.setProduct_amount(cartDTO.getProduct_amount());
				orderDTO.setProduct_size(cartDTO.getProduct_size());
				orderDTO.setProduct_size(cartDTO.getProduct_name());
				//t_orderProduct 테이블에 등록한다.
				count += orderDAO.addOrderProduct(orderDTO);
			}
			
			int result = 0;
			//모두 등록에 성공하면
			if(count == cartNumberList.length) {
				
				//반복문을 통해 장바구니 데이터를 삭제한다.
				for(int i = 0; i < cartNumberList.length; i++) {
					result += orderDAO.cartdelete(cartNumberList[i]);
				}
				
				if(result == cartNumberList.length) {
					return result;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} else {
			return 0;
		}
		
	}

	@Override
	public OrderDTO getOrderDTO(long order_num) throws Exception {
		System.out.println("OrderServiceImpl의 getOrderDTO()....");
		OrderDTO orderDTO = orderDAO.getOrderDTO(order_num);
		
//		//컨트롤러로 넘어가기 전에 배송메모를 수정해준다.
//		String order_memo = orderDTO.getOrder_memo();
//		order_memo = order_memo.replaceAll("[_]", " ");//_를 없앤다
//		if(order_memo.contains("선택")) { //배송 메모를 선택해주세요 일때는 공백 처리
//			order_memo = "&nbsp;";
//		}
//		System.out.println(order_memo);
//		orderDTO.setOrder_memo(order_memo);
		
		return orderDTO;
	}
	@Override
	public List<OrderDTO> getOrderDetail(long order_num) throws Exception {
		System.out.println("OrderServiceImpl의 getOrderDetail()....");
		
		return orderDAO.getOrderDetail(order_num);
	}

	@Override
	public int orderListSum(List<OrderDTO> orderDetailList) throws Exception {
		System.out.println("OrderServiceImpl의 orderListSum()....");
		
		int result = 0;
		//반복문을 통해 합계를 구해준다.
		for(int i = 0; i < orderDetailList.size(); i++) {
			
			int price = orderDetailList.get(i).getProduct_price(); //상품의 가격
			int amount = orderDetailList.get(i).getProduct_amount(); //상품 수량
			
			result += (price * amount);
			
		}
		
		return result;
	}

	
	
}
