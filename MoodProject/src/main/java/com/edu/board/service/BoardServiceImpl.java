package com.edu.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.board.dao.BoardDAO;
import com.edu.board.dto.BoardDTO;
import com.edu.board.dto.CommentDTO;
import com.edu.common.util.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보여주기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public List<BoardDTO> boardList(SearchCriteria sCri) throws Exception {
		
		return boardDAO.boardList(sCri);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 수 가져오기 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardListTotalCount(SearchCriteria sCri) throws Exception {
		
		return boardDAO.boardListTotalCount(sCri);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 처리 하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardRegister(BoardDTO boardDTO) throws Exception {
		
		return boardDAO.boardRegister(boardDTO);
	}
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 상세페이지 보여주기 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public BoardDTO boardDetail(int qna_bno) throws Exception {
		
		return boardDAO.boardDetail(qna_bno);
	}
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 수정
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		
		return boardDAO.boardUpdate(boardDTO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardDelete(int qna_bno) throws Exception {
		
		return boardDAO.boardDelete(qna_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 댓글 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int commentRegister(CommentDTO commentDTO) throws Exception {
		
		return boardDAO.commentRegister(commentDTO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 댓글 번호에 해당하는 댓글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int replyDelete(int reply_bno) throws Exception {
		
		return boardDAO.replyDelete(reply_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 댓글 수 구하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int commentListCount(int qna_bno) throws Exception {
		
		return boardDAO.commentListCount(qna_bno);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 댓글 수 구하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<CommentDTO> commentList(int qna_bno) throws Exception {
		
		return boardDAO.commentList(qna_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시판 리스트 제목에 댓글 수 표시
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int updateReplyCount(int qna_bno) throws Exception {
	
		return boardDAO.updateReplyCount(qna_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 댓글 번호에 해당하는 댓글 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int replyUpdate(CommentDTO commentDTO) throws Exception {
		
		return boardDAO.replyUpdate(commentDTO);
	}

	
	
}
