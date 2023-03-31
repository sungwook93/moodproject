package com.edu.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.member.dto.MemberDTO;
import com.edu.product.dto.ProductDTO;

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
			
			HttpServletRequest request, HttpServletResponse response) //ModelAttribute로 member라는 갹채애 값을 한번에 담아 넘긴다.
			throws Exception;
				
	//-----------------------------------------------------------------------------------------------------------
	// 로그아웃 처리
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
		
	//마이페이지 불러오기
	public ModelAndView myPageForm(HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	//----------------------------------------------------------------------------------------------------------
	// 마이페이지 아이디에 해당하는 마이페이지의 내용(비밀번호, 이름 등)을 수정 요청하기
	//----------------------------------------------------------------------------------------------------------
	public ModelAndView memberUpdate(@ModelAttribute("memberDTO") MemberDTO memberDTO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기 -- 개인계정
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView removeMember(@RequestParam("userID") String userID, 	
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//관리자페이지불러오기
	public ModelAndView adminForm(String product_code,HttpServletRequest request, HttpServletResponse response) throws Exception;
}
