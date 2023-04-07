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
	
	@ResponseBody
	@RequestMapping(value="/bills.do", method=RequestMethod.GET)
	public ModelAndView orderForm(CartDTO cartNum) throws Exception {
		logger.info("OrderController의 orderForm....");
		System.out.println("OrderController의 orderForm.... cartNum: " + cartNum);
		
		ModelAndView mav = new ModelAndView();
		
		//넘어온 장바구니 번호로 List를 구해서 model에 담아준다
		int userCartList=orderService.userCartList(cartNum);
		mav.addObject("cartList", userCartList);
		System.out.println(orderService.userCartList(cartNum));
		
		//상세페이지에서 보여줄 합계금액을 모델에 담아서 보내준다.
		mav.addObject("orderSum", orderService.orderSum(cartNum));
		
		//상세페이지 스크립트에서 이용할 장바구니 갯수를 구해 model에 담는다.
		//mav.addObject("cartCount", userID.length);
		
		//상세페이지 스크립트에서 이용할 장바구니 번호 배열을 List 형태로 model에 담는다.
		//mav.addObject("cartNumberList", orderService.cartNumberList(userID));
		
		//model에 주소를 세팅해준다.
		mav.setViewName("/order/bills");
		return mav;
	}
//	//상품 상세페이지에서 바로 주문하기를 누를 때 장바구니 정보를 가지고 주문 상세페이지로 이동
//		@RequestMapping(value="/preOrderForm", method=RequestMethod.GET)
//		public ModelAndView preOrderForm(int cartNum) throws Exception {
//			System.out.println("OrderController의 preOrderForm.... cartNum: " + cartNum);
//			
//			ModelAndView mav = new ModelAndView();
//			
//			//넘어온 장바구니 번호를 배열 형태로 만든다.
//			int[] num = {cartNum};
//			
//			//장바구니 번호로 리스트를 구해 model에 담는다.
//			mav.addObject("cartList", orderService.cartNumList(num));
//			
//			//상세페이지에서 보여줄 합계금액을 model에 담는다.
//			mav.addObject("orderSum", orderService.orderSum(num));
//			
//			//상세페이지에서 이용할 장바구니 갯수를 구해 model에 담는다.
//			mav.addObject("cartCount", num.length);
//			
//			//상세페이지 스크립트에서 이용할 장바구니 번호 배열을 List 형태로 model에 담는다.
//			mav.addObject("cartNumberList", orderService.cartNumberList(num));
//			
//			//주소를 세팅한다.
//			mav.setViewName("/order/orderDetail");
//			
//			return mav;
//		}
}
