package com.edu.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.board.dao.BoardDAO;
import com.edu.board.dto.BoardDTO;
import com.edu.board.service.BoardService;
import com.edu.common.util.PageMaker;
import com.edu.common.util.SearchCriteria;

@Controller
@RequestMapping(value= "/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService boardService;
	
	@Inject
	private	BoardDAO boardDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보여주기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public ModelAndView boardList(SearchCriteria sCri) throws Exception{
		
		ModelAndView mav = new ModelAndView("/board/boardListQna");
		
		List<BoardDTO> boardList = boardService.boardList(sCri);
		mav.addObject("boardList", boardList);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(sCri);

		pageMaker.setTotalCount(boardService.boardListTotalCount(sCri));
		
		mav.addObject("pageMaker", pageMaker);
		
		return mav;		
		
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록화면 불러오기
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value="/boardRegisterForm", method = RequestMethod.GET)
	public ModelAndView boardRegisterForm(SearchCriteria sCri) throws Exception {
		
		logger.info("BoardController 게시글 등록화면");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("boardList", boardService.boardList(sCri));
		mav.setViewName("/board/boardRegisterForm");
		
		return mav;	
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 처리 하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardRegister", method = RequestMethod.POST)
	public String boardRegister(BoardDTO boardDTO) throws Exception {
		
		logger.info("boardController 게시글 등록하기" + boardDTO);
		
		if(boardService.boardRegister(boardDTO) ==1) {
			return "Y";
		}else {
			return "N";
		}
		
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 상세페이지 보여주기 
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public ModelAndView boardDetail(int qna_bno, Integer flag) throws Exception {
		
		logger.info("boardController 게시글 상세페이지 보여주기");
		
		ModelAndView mav = new ModelAndView();
		
		if(flag == null) {
			boardDAO.updateReadCount(qna_bno);
		}
			
		mav.addObject(boardService.boardDetail(qna_bno));
		
		return mav;
		
		
	}
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 수정화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardUpdateForm", method = RequestMethod.GET)
	public ModelAndView boardUpdateForm(int qna_bno) throws Exception {
		
		logger.info("BoardController 게시글 수정화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		
		//service의 게시글 상세 정보 가져오는 method를 이용해서 boardDTO를 가져와서 model에 담는다.
		BoardDTO boardDTO = boardService.boardDetail(qna_bno);
		mav.addObject("boardDTO", boardDTO);
		
		mav.setViewName("/board/boardUpdateForm");
	
		return mav;
		
	} // End - 게시글 수정화면 불러오기
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 수정
	//-----------------------------------------------------------------------------------------------------------	
	@ResponseBody
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(BoardDTO boardDTO) throws Exception {
		
		System.out.println("게시글 번호에 해당하는 내용 수정하기");
		
		if(boardService.boardUpdate(boardDTO) == 1) {
			return "Y";
		}else {
			return "N";
		}
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 삭제
	//-----------------------------------------------------------------------------------------------------------		
	@ResponseBody
	@RequestMapping(value = "/boardDelete", method = RequestMethod.POST)
	public String boardDelete(int qna_bno) throws Exception {
		
		if(boardService.boardDelete(qna_bno)==1) {
			return "Y";
		}else {
			return "N";
		}
		
	}
}
