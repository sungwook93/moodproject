package com.edu.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.edu.board.dto.BoardDTO;
import com.edu.member.dto.MemberDTO;
import com.edu.order.dto.OrderDTO;
import com.edu.product.dto.ProductDTO;

public interface MemberDAO {
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사 => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
	//-----------------------------------------------------------------------------------------------------------
	public int idCheck(MemberDTO memberDTO) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	//	로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	public MemberDTO loginByID(MemberDTO memberDTO) throws DataAccessException;
		
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 처리하기
	//-----------------------------------------------------------------------------------------------------------
	public int addMember(MemberDTO memberDTO) throws DataAccessException;
	
	//----------------------------------------------------------------------------------------------------------
	// 마이페이지 아이디에 해당하는 마이페이지의 내용(비밀번호, 이름 등)을 수정 요청하기
	//----------------------------------------------------------------------------------------------------------	
	public int memberUpdate(MemberDTO memberDTO);
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	public int deleteMember(String userID) throws DataAccessException;
	
	//qna 해당아이디에 속한 것만 가지고 온다
	public List<BoardDTO> boardUserList(String userID) throws Exception;
	
	//구매정보 가져오기
	public List<OrderDTO> orderList(String userID) throws Exception;
	
	//관리자페이지 상품정보 가져오기
	public List<ProductDTO> productList(Map<String, Object> param) throws Exception;
	
	//관리자 페이지 페이징처리를 위한 상품 총갯수 가져오기
	public int totalCount(Map<String, Object> param) throws Exception;
	
	//관리자페이지 qna게시판정보가져오기
	public List<BoardDTO> boardList(String qna_bno) throws Exception;
	
	//관리자페이지 회원정보가져오기
	public List<MemberDTO> memberList(String userID) throws Exception;
			
	
}
