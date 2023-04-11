package com.edu.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.board.dto.BoardDTO;
import com.edu.board.dto.CommentDTO;
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
	
	//qna 해당 아이디에 글 가져오기
	@Override
	public List<BoardDTO> boardUserList(String userID) throws Exception {
		System.out.println("BoardDAOImpl의 boardTotalList() 구하기....");
		List<BoardDTO> boardList2 = sqlSession.selectList(Namespace + ".boardUserList", userID);
		System.out.println(boardList2);
		return boardList2;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 상세페이지 보여주기 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public BoardDTO boardDetail(int qna_bno) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".detail", qna_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 조회수 증가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public void updateReadCount(int qna_bno) throws Exception {
		
		sqlSession.update(Namespace + ".updateReadCount", qna_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 수정
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		
		return sqlSession.update(Namespace + ".update", boardDTO);
	}	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 삭제
	//-----------------------------------------------------------------------------------------------------------	

	@Override
	public int boardDelete(int qna_bno) throws Exception {
		
		return sqlSession.delete(Namespace + ".delete", qna_bno);
	}
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 댓글 등록하기
	//-----------------------------------------------------------------------------------------------------------

	@Override
	public int commentRegister(CommentDTO commentDTO) throws Exception {
		
		return sqlSession.insert(Namespace + ".commentRegister", commentDTO);
	}
		
	//-----------------------------------------------------------------------------------------------------------
	// 댓글 번호에 해당하는 댓글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int replyDelete(int imsi_bno) throws Exception {
		
		return sqlSession.delete(Namespace + ".replyDelete", imsi_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 댓글 수 구하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int commentListCount(int qna_bno) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".commentCount", qna_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 댓글 수 구하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<CommentDTO> commentList(int qna_bno) throws Exception {
		
		return sqlSession.selectList(Namespace + ".commentList", qna_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시판 리스트 제목에 댓글 수 표시
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int updateReplyCount(int qna_bno) throws Exception {
		
		return sqlSession.update(Namespace + ".updateReplyCount", qna_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 댓글 번호에 해당하는 댓글 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int replyUpdate(CommentDTO commentDTO) throws Exception {
		
		return sqlSession.update(Namespace + ".replyUpdate", commentDTO);
	}
	

	
	
	
	
	
}
