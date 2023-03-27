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
	
	
}
