package com.edu.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.member.dao.MemberDAO;
import com.edu.member.service.MemberService;
import com.edu.order.dto.CartDTO;
import com.edu.order.service.OrderService;



@Controller()
@RequestMapping("/order/*")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private	OrderService	orderService;
	
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
	public ModelAndView billsForm(String cart_num) throws Exception {
		System.out.println("OrderController의 countUpdate시작" + cart_num);
		
		//모델을 준비한다.
		ModelAndView mav =new ModelAndView();
		
		CartDTO cartDTO = orderService.bills(cart_num);
		//상품정보 넘겨주기
		mav.addObject("cartDTO" ,cartDTO);
		
		
		mav.setViewName("/order/bills");
		
		return mav;
	}
}
