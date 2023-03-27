package com.edu.member.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.edu.member.dao.MemberDAO;
import com.edu.member.dto.MemberDTO;
//import com.edu.product.dao.OrderDAO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberDAO memberDAO;
	
	//@Inject
	//OrderDAO orderDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사 => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int idCheck(MemberDTO memberDTO) throws Exception {
			
		logger.info("MemberServiceImpl 아이디에 해당하는  아이디 중복 검사 시작");
		
		int result = memberDAO.idCheck(memberDTO);
			
		return result;
	}	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberDTO login(MemberDTO memberDTO) throws DataAccessException {
			
		logger.info("MemberServiceImpl login() 시작");

		return memberDAO.loginByID(memberDTO);
	}
		
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 처리하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addMember(MemberDTO memberDTO) throws DataAccessException {

		logger.info("MemberServiceImpl 회원가입 처리하기() 시작 : " + memberDTO);
		return memberDAO.addMember(memberDTO);

	} // End - 회원가입 처리하기()
}
