package com.edu.board.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value ="/board/*")

public class boardController {

	private static final Logger logger = LoggerFactory.getLogger(boardController.class);
	
	// @Inject
 	// private BoardService boardService;
	
	// @Inject
	// private BoardDAO boardDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value = "/boardListQna", method = RequestMethod.GET)
	public String boardRegisterForm() throws Exception {
		
		logger.info("BoardController 게시글 화면 불러오기() 시작");
		
		return "/board/boardListQna";
		
	} // End - 게시글 화면 불러오기
	
}
