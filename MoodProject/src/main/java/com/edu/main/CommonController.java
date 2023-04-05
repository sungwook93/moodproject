package com.edu.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/common/*")
public class CommonController {
	
	//concept 화면 불러오기
		@RequestMapping(value="/conceptForm.do", method=RequestMethod.GET)
		public ModelAndView conceptForm() throws Exception {

			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/common/conceptForm");	// concept 화면
			return mav;
			
		} // End - concept 화면 불러오기
	
}
