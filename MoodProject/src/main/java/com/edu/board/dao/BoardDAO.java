package com.edu.board.dao;

import java.util.List;

import com.edu.board.dto.BoardDTO;
import com.edu.common.util.SearchCriteria;

public interface BoardDAO {
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보여주기
	//-----------------------------------------------------------------------------------------------------------
	public List<BoardDTO> boardList(SearchCriteria sCri) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 수 가져오기 
	//-----------------------------------------------------------------------------------------------------------
	public int boardListTotalCount(SearchCriteria sCri) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 전체 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------	
	public List<BoardDTO> boardTotalList() throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 처리 하기
	//-----------------------------------------------------------------------------------------------------------
	public int boardRegister(BoardDTO boardDTO) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 상세페이지 보여주기 
	//-----------------------------------------------------------------------------------------------------------
	public BoardDTO boardDetail(int qna_bno) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 조회수 증가
	//-----------------------------------------------------------------------------------------------------------	
	public void updateReadCount(int qna_bno) throws Exception;
}
