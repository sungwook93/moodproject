package com.edu.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.board.dto.BoardDTO;
import com.edu.common.util.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject	// 의존 관계 주입(Defendency Inject, DI)
	private SqlSession sqlSession;
	
	@Inject
	private static String Namespace = "com.edu.board";
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보여주기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public List<BoardDTO> boardList(SearchCriteria sCri) throws Exception {
		
		return sqlSession.selectList(Namespace + ".boardList", sCri);	
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 수 가져오기 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardListTotalCount(SearchCriteria sCri) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".totalCount", sCri);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 전체 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BoardDTO> boardTotalList() throws Exception {
		List<BoardDTO> boardList = sqlSession.selectList(Namespace + ".totalList");
		
		return boardList;
			
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 처리 하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardRegister(BoardDTO boardDTO) throws Exception {
		
		return sqlSession.insert(Namespace + ".boardRegister", boardDTO);
	}	

}
