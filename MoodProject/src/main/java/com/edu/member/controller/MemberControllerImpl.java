package com.edu.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller("memberController")
@RequestMapping("/member")
public class MemberControllerImpl implements MemberController {
	
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