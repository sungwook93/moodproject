package com.edu.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.edu.board.dto.BoardDTO;
import com.edu.member.dto.MemberDTO;
import com.edu.product.dto.ProductDTO;

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
	
	//----------------------------------------------------------------------------------------------------------
	// 마이페이지 아이디에 해당하는 마이페이지의 내용(비밀번호, 이름 등)을 수정 요청하기
	//----------------------------------------------------------------------------------------------------------	
	@Override
	public int memberUpdate(MemberDTO memberDTO) {
			
		logger.info("MemberDAOImpl 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기() 시작");
			
		return sqlSession.update(Namespace + ".update", memberDTO);
		
	} // End - 게시글 번호에 해당하는 게시글의 내용(제목, 글쓴이, 내용)을 수정 요청하기	
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteMember(String userID) throws DataAccessException {
		int result = sqlSession.delete(Namespace + ".deleteMember", userID);
		return result;
	} // End - // 아이디에 해당하는 회원 정보 삭제하기
	
	//qna 해당 아이디에 글 가져오기
	@Override
	public List<BoardDTO> boardUserList(String userID) throws Exception {
		System.out.println("MemberDAOImpl의 boardTotalList() 구하기....");
		List<BoardDTO> boardList2 = sqlSession.selectList(Namespace + ".boardUserList", userID);
		System.out.println(boardList2);
		return boardList2;
	}	
	
	//관리자페이지 상품정보가져오기
	@Override 
	public List<ProductDTO> productList(Map<String, Object> param) throws Exception {
		logger.info("MemberDAOImpl 관리자페이지 상품정보가져오기() 시작");
		return sqlSession.selectList(Namespace+".productList",param);
	}
	//관리자 페이징 처리를 위한 상품 총 갯수 가져오기
	@Override
	public int totalCount(Map<String, Object> param) throws Exception {
		logger.info("MemberDAOImpl 관리자 페이징 처리를 위한 상품 총 갯수 가져오기() 시작");
		return sqlSession.selectOne(Namespace+".totalCount",param);
	}
	
	//관리자페이지 qna게시판정보가져오기
	@Override 
	public List<BoardDTO> boardList(String qna_bno) throws Exception {
		logger.info("MemberDAOImpl 관리자페이지 qna게시판정보가져오기() 시작");
		return sqlSession.selectList(Namespace+".boardList",qna_bno);
	}
	//관리자페이지 회원정보가져오기
	@Override
	public List<MemberDTO> memberList(String userID) throws Exception {
		logger.info("MemberDAOImpl 관리자페이지 회원정보 가져오기() 시작");
		return sqlSession.selectList(Namespace+".memberList",userID);
	}
}
