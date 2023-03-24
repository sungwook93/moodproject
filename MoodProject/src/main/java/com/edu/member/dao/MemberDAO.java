package com.edu.member.dao;

import com.edu.member.dto.MemberDTO;

public interface MemberDAO {
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사 => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
	//-----------------------------------------------------------------------------------------------------------
	public int idCheck(MemberDTO memberDTO) throws Exception;
}
