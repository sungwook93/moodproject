package com.edu.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.member.dto.MemberDTO;

public interface MemberController {
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사  => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
	//-----------------------------------------------------------------------------------------------------------	
	public int idCheck(MemberDTO memberDTO) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 처리하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addMember(@ModelAttribute("member") MemberDTO memberDTO,
			HttpServletRequest request, HttpServletResponse response) 
					throws Exception;
		
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView login(@ModelAttribute("member") MemberDTO member,
			RedirectAttributes rAttr, 
			HttpServletRequest request, HttpServletResponse response) //ModelAttribute로 member라는 갹채애 값을 한번에 담아 넘긴다.
			throws Exception;
				
	//-----------------------------------------------------------------------------------------------------------
	// 로그아웃 처리
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
		
}
