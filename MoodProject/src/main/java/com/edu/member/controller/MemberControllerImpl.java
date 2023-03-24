package com.edu.member.controller;

import javax.inject.Inject;	
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//import com.edu.board.dao.BoardDAO;
//import com.edu.board.dao.ReviewDAO;
import com.edu.member.dto.MemberDTO;
import com.edu.member.service.MemberService;
//import com.edu.product.dao.OrderDAO;


@Controller("memberController")
@RequestMapping("/member")
public class MemberControllerImpl implements MemberController {
	
	//-----------------------------------------------------------------------------------------------------------
	//	@Inject		: Java에서 지원하는 어노테이션. 특정 Framework에 종속정이기 않다.
	//	@Autowired	: Spring에서 지원하는 어노테이션.
	//-----------------------------------------------------------------------------------------------------------
	@Autowired
	private	MemberDTO	memberDTO;
	
	// MemberService memberService = new MemberService();
	@Autowired
	private	MemberService	memberService;
		
		
	//@Inject
	//ReviewDAO reviewDAO;
		
	//@Inject
	//BoardDAO boardDAO;	
		
	//@Inject
	//OrderDAO orderDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
		//-----------------------------------------------------------------------------------------------------------
		// 로그인 화면 띄우기 1
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@RequestMapping(value="/loginForm.do", method=RequestMethod.GET)
		public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
			logger.info("MemberControllerImpl 로그인 화면 불러오기() 시작");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/member/loginForm");
			return mav;
			
		}
		
		//-----------------------------------------------------------------------------------------------------------
		// 회원가입 화면 불러오기
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@RequestMapping(value="/memberForm.do", method=RequestMethod.GET)
		public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

			logger.info("MemberControllerImpl 회원가입 화면 불러오기() 시작");
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/member/memberForm");	// 회원가입화면
			return mav;
		} // End - 회원가입 화면 불러오기()
		
		//-----------------------------------------------------------------------------------------------------------
		// 아이디 중복 검사 (Ajax) => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@ResponseBody	// JSON 관련 jar 파일이 없으면 @ResponseBody가 응답을 하지 않는다. 
		@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
		public int idCheck(MemberDTO memberDTO) throws Exception {
			
			System.out.println("MemberControllerImpl 아이디 중복 검사 (Ajax) 시작");
			System.out.println("회원 아이디: " + memberDTO.getUserID());
			
			int result = memberService.idCheck(memberDTO);
			
			logger.info("MemberControllerImpl 아이디 중복 검사 후 받은 값 : " + result);
			
			return result;
		}
}
//<!-- Google tag (gtag.js) -->
//<script async src="https://www.googletagmanager.com/gtag/js?id=G-0NRHYLJTPK"></script>
//<script>
//  window.dataLayer = window.dataLayer || [];
//  function gtag(){dataLayer.push(arguments);}
//  gtag('js', new Date());
//
//  gtag('config', 'G-0NRHYLJTPK');
//</script>