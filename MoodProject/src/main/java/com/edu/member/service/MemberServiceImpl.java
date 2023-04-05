package com.edu.member.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.edu.board.dto.BoardDTO;
import com.edu.member.dao.MemberDAO;
import com.edu.member.dto.MemberDTO;
//import com.edu.product.dao.OrderDAO;
import com.edu.product.dto.ProductDTO;

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
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberDTO login2(MemberDTO memberDTO) throws DataAccessException {
			
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
	
	//----------------------------------------------------------------------------------------------------------
	// 마이페이지 아이디에 해당하는 마이페이지의 내용(비밀번호, 이름 등)을 수정 요청하기
	//----------------------------------------------------------------------------------------------------------
	@Override
	public int memberUpdate(MemberDTO memberDTO){
			
		logger.info("MemberServiceImpl 게시글 번호에 해당하는 게시글의 내용(제목, 글쓴이, 내용)을 수정 요청하기() 시작 ");
			
			
		return memberDAO.memberUpdate(memberDTO);
		
	} // End - 게시글 번호에 해당하는 게시글의 내용(제목, 글쓴이, 내용)을 수정 요청하기	

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int removeMember(String userID) throws DataAccessException {

		logger.info("MemberServiceImpl 아이디에 해당하는 회원 정보 삭제하기() 시작");
		return memberDAO.deleteMember(userID);

	} // End - 아이디에 해당하는 회원 정보 삭제하기()	
	
	
	//관리자페이지 상품정보 가져오기
	@Override
	public List<ProductDTO> productList(String product_code) throws Exception {
		logger.info("MemberServiceImpl 관리자페이지 상품정보 가져오기() 시작");
		return memberDAO.productList(product_code);
	}
	
	//관리자페이지 qna게시판정보가져오기
	@Override
	public List<BoardDTO> boardList(String qna_bno) throws Exception {
		logger.info("MemberServiceImpl 관리자페이지 qna게시판정보 가져오기() 시작");
		return memberDAO.boardList(qna_bno);
	}
	//관리자페이지 회원정보가져오기
	@Override
	public List<MemberDTO> memberList(String userID) throws Exception {
		logger.info("MemberServiceImpl 관리자페이지 회원정보 가져오기() 시작");
		return memberDAO.memberList(userID);
	}
}
