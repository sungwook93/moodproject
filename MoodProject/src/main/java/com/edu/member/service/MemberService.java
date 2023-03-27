package com.edu.member.service;

import org.springframework.dao.DataAccessException;

import com.edu.member.dto.MemberDTO;

public interface MemberService {
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사 => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
	//-----------------------------------------------------------------------------------------------------------
	public int idCheck(MemberDTO memberDTO) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	public MemberDTO login(MemberDTO memberDTO) throws DataAccessException;

	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 처리하기
	//-----------------------------------------------------------------------------------------------------------
	public int addMember(MemberDTO memberDTO) throws DataAccessException;
		
}
