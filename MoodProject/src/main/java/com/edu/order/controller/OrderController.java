package com.edu.order.controller;

import java.util.ArrayList;		
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.edu.order.dto.CartDTO;
import com.edu.order.service.OrderService;
import com.edu.order.dao.OrderDAO;
import com.edu.order.dto.OrderDTO;



@Controller()
@RequestMapping("/order/*")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private	OrderService	orderService;
	
	@Inject
	OrderDAO orderDAO;
	
	
	//장바구니화면 이동하기
	@RequestMapping(value="/cartForm.do", method=RequestMethod.GET)
	public ModelAndView cartForm(String userID) throws Exception {
		
		
		ModelAndView mav =new ModelAndView(userID);
		
		List<CartDTO> cartList = orderService.cartList(userID);
		System.out.println("장바구니 리스트 ==>"+ cartList);
		mav.addObject("cartList", cartList);
		
		mav.setViewName("/order/cartForm");
		return mav;
	}
	
	
	//장바구니에 상품 담기
	@ResponseBody
	@RequestMapping(value="/addCart.do", method=RequestMethod.POST)
	public int addCart(CartDTO cartDTO) throws Exception {
		
		logger.info("OrderController의 addCart....");
		System.out.println("OrderController의 addCart.... CartDTO: " + cartDTO);
		
		//넘어온 유저 아이디가 있는지 확인한다.
		if(cartDTO.getUserID().equals("")) {
			return -1;
		} else {
			//해당상품이 장바구니에 이미 들어있는지 확인한다.
			if(orderService.checkcart(cartDTO) == 0) {
				//상품을 장바구니에 넣는다
				
				int result = orderService.addCart(cartDTO);
				
				return result;
			} else { // 장바구니에 이미 해당상품이 있음
				return -2;
			}
		}
	
	}
	
	
	
	
	//장바구니 수량 변경
	@ResponseBody
	@RequestMapping(value="/countUpdate", method=RequestMethod.POST)
	public String countUpdate(CartDTO cartDTO)throws Exception{
		
		System.out.println("OrderController의 countUpdate시작" + cartDTO);
		
		//수량db에 변경하러 출발
		int result = orderService.countUpdate(cartDTO);
		
		if(result == 1) {
			return cartDTO.getUserID();
		}
		
		return null;
	}
	
	//장바구니 담은 상품 주문페이지
	@RequestMapping(value="/bills.do", method=RequestMethod.GET)
	public ModelAndView billsForm(int[] cart_num) throws Exception {
		System.out.println("OrderController의 countUpdate시작" + cart_num);
		
		//모델을 준비한다.
		ModelAndView mav =new ModelAndView();
		
		//카트정보를 담을 리스트를 만든다
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		
		//장바구니에서 넘어온 배열만큼 cartList에 담아준다.
		for(int i = 0; i < cart_num.length; i++) {
			cartList.add(i,orderService.bills(cart_num[i]));
		}
		System.out.println(cartList);
		
		//장바구니 리스트의 상품의 합계를 구해 model에 담아준다.
		//장바구니 리스트의 장바구니 번호를 배열로 만든다.
		int[] cartNum = new int[cartList.size()];
		for(int i = 0; i < cartList.size(); i++) {
			cartNum[i] = cartList.get(i).getCart_num();
		}
		mav.addObject("orderSum", orderService.orderSum(cartNum));
		
		//상세페이지 스크립트에서 이용할 장바구니 갯수를 구해 model에 담는다.
		mav.addObject("cartCount", cartNum.length);
		
		//리스트 모델에 담기
		mav.addObject("cartList", cartList);
		
		//주소 입력
		mav.setViewName("/order/bills");
		
		return mav;
	}
	
	
	//상품주문하기
	@RequestMapping(value="/bills", method=RequestMethod.GET)
	public ModelAndView bills(CartDTO cartDTO) throws Exception {
		System.out.println("OrderController의 상품주문하기" + cartDTO);
		
		//모델을 준비한다.
		ModelAndView mav =new ModelAndView();
		
		
		//리스트만든다
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		cartList.add(0, cartDTO);
		
		mav.addObject("orderSum", cartDTO.getProduct_amount()*cartDTO.getProduct_price());
		
		mav.addObject("cartList", cartList);
		mav.addObject("cartCount", 1);
		
		mav.setViewName("/order/bills");
		return mav;
	}
	
	
	//장바구니 상품 삭제하기
	@ResponseBody
	@RequestMapping(value="/cartdelete", method=RequestMethod.POST)
	public String cartdelete(int[] cart_num)throws Exception{
		System.out.println("OrderController의 cartdelete시작" + cart_num[0]);
		
		int result=0;
		//for문을 이용해서 들어온 카트넘버들을 삭제한다.
		for(int i = 0; i < cart_num.length; i++) {
		 result	+= orderService.cartdelete(cart_num[i]);
		}
		
		if(result == cart_num.length) {
			return "Y";			
		}else {
			return "N";
		}
	}
	
	//주문 완료 처리후 주문 번호 리턴 (t_cart 데이터 +알파 를 t_order 테이블로 옮기기)
	@ResponseBody
	@RequestMapping(value="/orderComplete", method=RequestMethod.POST)
	public long orderComplete(int[] cartNumberList, OrderDTO orderDTO, HttpServletRequest request) throws Exception {
		System.out.println("OrderController의 orderComplete.... 회원 아이디: " + orderDTO.getUserID() + "장바구니 번호: " + cartNumberList[0]);	
		System.out.println("확인해보장=========" + orderDTO);
		
		//orderDTO에 order_num을 계산해서 세팅한다.
		orderDTO.setOrder_num(orderService.getOrder_num());
		System.out.println("주문번호: " + orderDTO.getOrder_num());
			
			// 바로 주문하기
			if(cartNumberList[0] == 0) {
				orderDAO.addOrder(orderDTO);
				orderDAO.addOrderProduct(orderDTO); 
				return orderDTO.getOrder_num();
			}
			
			//orderDTO로 주문 완료 테이블에 데이터를 등록한다.
			// 장바구니에서 주문하기
			if(orderService.addOrder(cartNumberList, orderDTO) == cartNumberList.length && cartNumberList[0] != 0) { //성공시
			return orderDTO.getOrder_num();
			
		} else {
			return 0;
		}	
	}
		
		
		
	//주문 성공 페이지 이동하기
	@RequestMapping(value="/orderCompleteForm", method=RequestMethod.GET)
	public ModelAndView orderCompleteForm(long order_num) throws Exception {
		System.out.println("OrderController의 orderCompleteForm.... 주문번호: " + order_num);	
		
		ModelAndView mav = new ModelAndView();
		
		//주문번호에 해당하는 orderDTO 객체를 가져와서 model에 담는다.
		mav.addObject("orderDTO", orderService.getOrderDTO(order_num));
		
		//주문번호에 해당하는 상품 상세가 담긴 orderDTO 리스트를 가져와서 model에 담는다.
		List<OrderDTO> orderDetailList = orderService.getOrderDetail(order_num);
		mav.addObject("orderDetailList", orderDetailList);
		
		//주문 완료 페이지에서 사용할 합계 금액을 구해서 model에 담는다.
		mav.addObject("orderSum", orderService.orderListSum(orderDetailList));
		
		System.out.println(orderService.getOrderDTO(order_num) + "확인!!!!!!!");
		System.out.println(orderDetailList + "확인!!!!!!!2");
		
		
		//주소를 세팅한다.
		mav.setViewName("/order/orderComplete");
		
		return mav;
	}
		
		
	//주문 완료 상세 페이지 이동하기
	@RequestMapping(value="/orderCompleteDetail", method=RequestMethod.GET)
	public ModelAndView orderCompleteDetail(long order_num) throws Exception {
		System.out.println("OrderController의 orderCompleteForm.... 주문번호: " + order_num);
		
		ModelAndView mav = new ModelAndView();
		
		//주문번호에 해당하는 orderDTO 객체를 가져와서 model에 담는다.
		mav.addObject("orderDTO", orderService.getOrderDTO(order_num));
		
		//주문번호에 해당하는 상품 상세가 담긴 orderDTO 리스트를 가져와서 model에 담는다.
		List<OrderDTO> orderDetailList = orderService.getOrderDetail(order_num);
		mav.addObject("orderDetailList", orderDetailList);
		
		//주문 완료 페이지에서 사용할 합계 금액을 구해서 model에 담는다.
		mav.addObject("orderSum", orderService.orderListSum(orderDetailList));
		
		//주소를 세팅한다.
		mav.setViewName("/order/orderCompleteDetail");
		
		return mav;
	}
		
	
}
