package com.edu.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		mav.addObject("cartList", cartList);
		return mav;
	}
	
	//장바구니에 상품 담기
	@RequestMapping(value="/addCart.do", method=RequestMethod.GET)
	public int addCart(CartDTO cartDTO) throws Exception {
		
		logger.info("OrderController의 addCart....");
		System.out.println("OrderController의 addCart.... CartDTO: " + cartDTO);
		
		//넘어온 유저 아이디가 있는지 확인한다.
		if(cartDTO.getUserID().equals("")) {
			return -1;
		} else {
			
			//해당 유저의 장바구니에 해당 상품이 있으면 더한다
			return orderService.addCart(cartDTO);
		}	
	
	}
}
