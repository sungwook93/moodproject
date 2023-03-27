package com.edu.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.edu.member.dto.MemberDTO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;

	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	private	static final String Namespace = "com.edu.member";
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사 => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int idCheck(MemberDTO memberDTO) throws DataAccessException {
			
		logger.info("MemberDAOImpl 아이디 중복 검사시작하기");
			
		return sqlSession.selectOne(Namespace + ".idCheck", memberDTO);
	}
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public MemberDTO loginByID(MemberDTO memberDTO) throws DataAccessException {
			
		MemberDTO memDTO = sqlSession.selectOne(Namespace + ".loginByID", memberDTO);
		return memDTO;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 처리하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addMember(MemberDTO memberDTO) throws DataAccessException {

		logger.info("MemberDAOImpl 회원가입 처리하기() 시작 : " + memberDTO);
			
		System.out.println("MemberDAOImpl 회원가입 처리하기() 시작 : " + memberDTO);
			
		int result = sqlSession.insert(Namespace + ".addMember", memberDTO);
		System.out.println("MemberDAOImpl 회원가입 처리하기() 결과 : " + result);
		return result;
			
	} // End - 회원가입 처리하기
}
