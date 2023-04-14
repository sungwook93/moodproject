package com.edu.member.controller;

import java.util.HashMap;	
import java.util.List;
import java.util.Map;

import javax.inject.Inject;	
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.board.dto.BoardDTO;
import com.edu.common.util.AdminCriteria;
import com.edu.common.util.PageMaker;
import com.edu.common.util.ProductCriteria;
import com.edu.member.dao.MemberDAO;
//import com.edu.board.dao.BoardDAO;
//import com.edu.board.dao.ReviewDAO;
import com.edu.member.dto.MemberDTO;
import com.edu.member.service.MemberService;
import com.edu.order.dao.OrderDAO;
//import com.edu.product.dao.OrderDAO;
//import com.edu.product.dto.OrderDTO;
import com.edu.product.dto.ProductDTO;
import com.edu.review.dao.ReviewDAO;


@Controller("memberController")
@RequestMapping("/member")
public class MemberControllerImpl implements MemberController {
	
	//-----------------------------------------------------------------------------------------------------------
	//	@Inject		: Java에서 지원하는 어노테이션. 특정 Framework에 종속정이기 않다.
	//	@Autowired	: Spring에서 지원하는 어노테이션.
	//-----------------------------------------------------------------------------------------------------------
	@Autowired
	private	MemberDTO	memberDTO;
	
	// MemberService memberService = new MemberService();
	@Autowired
	private	MemberService	memberService;
	
	@Inject
	MemberDAO memberDAO;
		
		
	//@Inject
	//ReviewDAO reviewDAO;
		
	//@Inject
	//BoardDAO boardDAO;	
		
	@Inject
	OrderDAO orderDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
		
		
		//-----------------------------------------------------------------------------------------------------------
		// 회원가입 화면 불러오기
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@RequestMapping(value="/memberForm.do", method=RequestMethod.GET)
		public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

			logger.info("MemberControllerImpl 회원가입 화면 불러오기() 시작");
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/member/memberForm");	// 회원가입화면
			return mav;
		} // End - 회원가입 화면 불러오기()
		
		
		//-----------------------------------------------------------------------------------------------------------
		// 아이디 중복 검사 (Ajax) => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@ResponseBody	// JSON 관련 jar 파일이 없으면 @ResponseBody가 응답을 하지 않는다. 
		@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
		public int idCheck(MemberDTO memberDTO) throws Exception {
			
			System.out.println("MemberControllerImpl 아이디 중복 검사 (Ajax) 시작");
			System.out.println("회원 아이디: " + memberDTO.getUserID());
			
			int result = memberService.idCheck(memberDTO);
			
			logger.info("MemberControllerImpl 아이디 중복 검사 후 받은 값 : " + result);
			
			return result;
		}
		
		//-----------------------------------------------------------------------------------------------------------
		// 회원가입 처리하기
		//-----------------------------------------------------------------------------------------------------------
		@Override
		   @RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
		   public ModelAndView addMember(MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response)
		         throws Exception {

		      logger.info("MemberControllerImpl 회원가입 처리하기() 시작");
		      
		      System.out.println("memberDTO: " + memberDTO);
		      response.setContentType("text/html;charset=UTF-8");
		      
		      HttpSession session = request.getSession();
		      session.setAttribute("member1",    memberDTO);
		      session.setAttribute("isLogOn", true);
		      
		      //아이디로 t_orderProduct 테이블에서 데이터를 리스트형으로 받아와서 세션에 넣어준다(productDetail에서 사용하기 위해)
		      //List<OrderDTO> orderDetailList = orderDAO.getOrderDetailById(memberDTO.getUserID());
		      //session.setAttribute("member1OrderDetail",    orderDetailList);
		      
		      int result   = 0;   // 데이터 처리 완료 건수를 저장할 변수
		      
		      // 사용자가 입력한 회원정보를 서비스에게 넘겨주어서 처리하게 한다.
		      result = memberService.addMember(memberDTO);
		      
		      // 회원가입 처리후 회원전체목록 페이지로 이동한다.
		      ModelAndView mav = new ModelAndView("redirect:/main.do");

		      return mav;
		   } // End - 회원가입 처리하기   		
		
		//-----------------------------------------------------------------------------------------------------------
		// 로그인 처리
		// member => 로그인 창에서 보내온 정보, memberDTO => DB에서 가져온 정보
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@RequestMapping(value="/login.do", method=RequestMethod.POST)
		public ModelAndView login(MemberDTO member, HttpServletRequest request,
				HttpServletResponse response) throws Exception {   //ModelAttribute로 member라는 갹채애 값을 한번에 담아 넘긴다.
			
			System.out.println("MemberControllerImpl 로그인 처리 시작.....");
			System.out.println("로그인 정보 => " + member.getUserID() + " : " + member.getPwd());
			
			ModelAndView mav = new ModelAndView();
			
			// 아이디 값이 없이 넘어온 경우에는 돌려보낸다.
			if(member.getUserID().equals("") || member.getUserID() == null) {
				mav.addObject("result", "loginIdEmpty");
				//mav.setViewName("redirect:/member/loginForm.do");	
				mav.setViewName("/main");
				return mav;
			}
			
			// 로그인한 정보를 가지고 데이터베이스에 존재하는지 처리를 하고, 그 결과를 가져온다.
			// member => 로그인 창에서 보내온 정보, memberDTO => DB에서 가져온 정보
			memberDTO = memberService.login(member);
			System.out.println("로그인 처리 결과 ==> " + memberDTO);
			
			// 로그인한 정보가 데이터베이스에 존재하는지에 따라 처리를 다르게 한다.
			if(memberDTO != null) {	// 로그인 정보에 해당하는 자료가 있으면
				
				if(member.getPwd().equals(memberDTO.getPwd())) {
					
					// 아이디와 비밀번호가 일치하면 세션을 발급한다.
					HttpSession session = request.getSession();
					session.setAttribute("member1", 	memberDTO);
					session.setAttribute("isLogOn", true);
					
					//아이디로 t_orderProduct 테이블에서 데이터를 리스트형으로 받아와서 세션에 넣어준다(productDetail에서 사용하기 위해)
				    //List<OrderDTO> orderDetailList = orderDAO.getOrderDetailById(memberDTO.getUserID());
				    //session.setAttribute("member1OrderDetail",    orderDetailList);
				    //System.out.println("로그인한 회원의 주문 리스트: " + orderDetailList);
					
					mav.setViewName("/main");	// 메인화면으로 이동한다.
					
				} else { 	// 아이디는 있는데 비밀번호가 틀린 경우
							// 메시지를 가지고 로그인 화면으로 이동한다.
					mav.addObject("result", "PasswordFailed");
					//mav.setViewName("redirect:/member/loginForm.do");	
					mav.setViewName("/main");	// 메인화면으로 이동한다.
				}
				
			} else {	// 로그인한 아이디가 존재하지 않으면 
						// 로그인 실패 메시지를 가지고 로그인 화면으로 이동한다.
				mav.addObject("result", "loginFailed");
				//mav.setViewName("redirect:/member/loginForm.do");	
				mav.setViewName("/main");	// 메인화면으로 이동한다.
				
			}
			return mav;
		}
		
		
		//-----------------------------------------------------------------------------------------------------------
		// 로그인 처리2
		// member => 로그인 창에서 보내온 정보, memberDTO => DB에서 가져온 정보
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@RequestMapping(value="/login2.do", method=RequestMethod.POST)
		public ModelAndView login2(MemberDTO member,String product_code, HttpServletRequest request,
				HttpServletResponse response) throws Exception {   //ModelAttribute로 member라는 갹채애 값을 한번에 담아 넘긴다.
			
			System.out.println("MemberControllerImpl 로그인 처리 시작.....");
			System.out.println("로그인 정보 => " + member.getUserID() + " : " + member.getPwd());
			
			ModelAndView mav = new ModelAndView();
			
			// 아이디 값이 없이 넘어온 경우에는 돌려보낸다.
			if(member.getUserID().equals("") || member.getUserID() == null) {
				mav.addObject("result", "loginIdEmpty");
				//mav.setViewName("redirect:/member/loginForm.do");	
				mav.setViewName("/main");
				return mav;
			}
			
			// 로그인한 정보를 가지고 데이터베이스에 존재하는지 처리를 하고, 그 결과를 가져온다.
			// member => 로그인 창에서 보내온 정보, memberDTO => DB에서 가져온 정보
			memberDTO = memberService.login(member);
			System.out.println("로그인 처리 결과 ==> " + memberDTO);
			
			// 로그인한 정보가 데이터베이스에 존재하는지에 따라 처리를 다르게 한다.
			if(memberDTO != null) {	// 로그인 정보에 해당하는 자료가 있으면
				
				if(member.getPwd().equals(memberDTO.getPwd())) {
					
					// 아이디와 비밀번호가 일치하면 세션을 발급한다.
					HttpSession session = request.getSession();
					session.setAttribute("member1", 	memberDTO);
					session.setAttribute("isLogOn", true);
					
					//아이디로 t_orderProduct 테이블에서 데이터를 리스트형으로 받아와서 세션에 넣어준다(productDetail에서 사용하기 위해)
				    //List<OrderDTO> orderDetailList = orderDAO.getOrderDetailById(memberDTO.getUserID());
				    //session.setAttribute("member1OrderDetail",    orderDetailList);
				    //System.out.println("로그인한 회원의 주문 리스트: " + orderDetailList);
					
					mav.setViewName("/main");	// 메인화면으로 이동한다.
					
				} else { 	// 아이디는 있는데 비밀번호가 틀린 경우
							// 메시지를 가지고 로그인 화면으로 이동한다.
					mav.addObject("result", "PasswordFailed");
					//mav.setViewName("redirect:/member/loginForm.do");	
					mav.setViewName("/main");	// 메인화면으로 이동한다.
				}
				
			} else {	// 로그인한 아이디가 존재하지 않으면 
						// 로그인 실패 메시지를 가지고 로그인 화면으로 이동한다.
				mav.addObject("result", "loginFailed");
				//mav.setViewName("redirect:/member/loginForm.do");	
				mav.setViewName("/main");	// 메인화면으로 이동한다.
				
			}
			return mav;
		}
			
		
		
		//-----------------------------------------------------------------------------------------------------------
		// 로그아웃 처리
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@RequestMapping(value="/logout.do", method=RequestMethod.GET)
		public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

			// 로그아웃 버튼을 누르면 세션정보를 없애고, 메인페이지로 이동하게 한다.
			HttpSession session = request.getSession();
			session.removeAttribute("member1");
			session.removeAttribute("isLogOn");
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/main.do");	// 메인페이지로 이동
			
			return mav;
			
		} // End - 로그아웃 처리
		
		// 마이페이지 화면 불러오기
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@RequestMapping(value="/myPageForm.do", method=RequestMethod.GET)
		public ModelAndView myPageForm(String userID,HttpServletRequest request, HttpServletResponse response) throws Exception {

			logger.info("MemberControllerImpl 마이페이지 화면 불러오기() 시작");
			System.out.println("마이페이지화면불러오기1");
			
			// 아이디와 비밀번호가 일치하면 세션을 발급한다.
			HttpSession session = request.getSession();
			session.setAttribute("member1", 	memberDTO);
			session.setAttribute("isLogOn", true);
	
			ModelAndView mav = new ModelAndView();
			
			
			
			//qna 해당아이디에 속한 것만 가지고 온다
			mav.addObject("boardList", memberDAO.boardUserList(userID));
			System.out.println(memberDAO.boardUserList(userID));
			//System.out.println(mav+"1");
			
			//구매정보 가져오기
			mav.addObject("userUpdate",session);
			mav.addObject("orderList",memberDAO.orderList(userID));
			mav.setViewName("/member/myPageForm");	// 
			//System.out.println(userID+"!");
			return mav;
		} // End - 마이페이지 화면 불러오기()

		
		//----------------------------------------------------------------------------------------------------------
		// 마이페이지 아이디에 해당하는 마이페이지의 내용(비밀번호, 이름 등)을 수정 요청하기
		//----------------------------------------------------------------------------------------------------------
		@ResponseBody
		@RequestMapping(value = "/memberUpdate.do", method = RequestMethod.POST)
		public ModelAndView memberUpdate(@ModelAttribute("memberDTO") MemberDTO memberDTO,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			// 세션값 받기
			HttpSession session = request.getSession();
			session.setAttribute("member1", 	memberDTO);
			session.setAttribute("isLogOn", true);
			logger.info("MemberControllerImpl 아이디에 해당하는 회원 정보 수정하기() 시작");
			
			
			int result = memberService.memberUpdate(memberDTO);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/main");
			return mav;
			
			
		} // End - 게시글 번호에 해당하는 게시글의 내용(제목, 글쓴이, 내용)을 수정 요청하기
		
		
		//-----------------------------------------------------------------------------------------------------------
		// 아이디에 해당하는 회원 정보 삭제하기 - 개인계정
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@RequestMapping(value = "/removeMember.do", method = RequestMethod.GET)
		public ModelAndView removeMember(String userID, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			logger.info("MemberControllerImpl 아이디에 해당하는 회원 정보 삭제하기기() 시작");
			logger.info("JSP에서 넘겨준 회원아이디 : " + userID);
			
			
			// 탈퇴하면 세션을 없앤다
			HttpSession session = request.getSession();
			session.removeAttribute("member1");
			session.removeAttribute("isLogOn");
			
			request.setCharacterEncoding("UTF-8");
			int result = memberService.removeMember(userID);
			//ModelAndView mav = new ModelAndView("/member/unregisterForm");
			ModelAndView mav = new ModelAndView();
			mav.addObject("result", "removeMember");
			mav.setViewName("/main");
			return mav;
		}//End - 아이디에 해당하는 회원 정보 삭제하기
		
		//-----------------------------------------------------------------------------------------------------------
		// 아이디에 해당하는 회원 정보 삭제하기 - 관리자
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@RequestMapping(value = "/removeMember2.do", method = RequestMethod.GET)
		public ModelAndView removeMember2(String userID, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
					
			logger.info("MemberControllerImpl 아이디에 해당하는 회원 정보 삭제하기기() 시작");
			logger.info("JSP에서 넘겨준 회원아이디 : " + userID);
					
					
			// 관리자는 회원정보삭제후 세션값이 없어지면안된다
			//HttpSession session = request.getSession();
			//session.removeAttribute("member1");
			//session.removeAttribute("isLogOn");
					
			request.setCharacterEncoding("UTF-8");
			int result = memberService.removeMember(userID);
			ModelAndView mav = new ModelAndView();
			mav.addObject("result", "removeMember");
			mav.setViewName("/main");
			return mav;
		}//End - 아이디에 해당하는 회원 정보 삭제하기

		//-----------------------------------------------------------------------------------------------------------
		// 관리자 화면 불러오기
		//-----------------------------------------------------------------------------------------------------------
		@Override
		@RequestMapping(value="/adminForm.do", method=RequestMethod.GET)
		public ModelAndView adminForm(AdminCriteria aCri,String product_code,String qna_bno,String userID,HttpServletRequest request, HttpServletResponse response) throws Exception {

			logger.info("MemberControllerImpl 회원가입 화면 불러오기() 시작");
			
			ModelAndView mav = new ModelAndView();

			// map 형식을 담기위해 설정해둔다. 이유는 type쪽을 배열로 넘겨주기위해서 가공을 해야한다.
			// type을 배열로 담을려는 이유는 mapper에 where in을 넣어야 하는데 mybatis 부분의 where in은 foreach를 써야하므로
			// 문자열을 배열로 바꾼다.
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("perPageNum", 9);
			param.put("page", aCri.getPage());
			
			//들어오는 문자열을 배열로 만들고 map에다가 담아준다.
			//type
			String typeList[] = null;
			typeList = aCri.getProduct_type().split(",");
			param.put("typeList", typeList);	
			
			List<ProductDTO> productList = memberService.productList(param);
			mav.addObject("productList", productList);
			
			//넘어온 현재 페이지와 해당 상품 전체 데이터 갯수를 pageMaker에 세팅하고 model담는다.
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(aCri);
			pageMaker.setTotalCount(memberService.totalCount(param));
			System.out.println("totalCount: " + pageMaker.getTotalCount());
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("type" , aCri.getProduct_type());
			mav.addObject("totalCount", pageMaker.getTotalCount());
			
			List<BoardDTO> boardList = memberService.boardList(qna_bno);
			mav.addObject("boardList", boardList);
			
			
			List<MemberDTO> memberList = memberService.memberList(userID);
			mav.addObject("memberList", memberList);
			System.out.println(typeList+"프로덕트타입");
			
			//mav.setViewName("redirect:/member/adminForm.do?product_type=bed");
			return mav;
		} // End - 관리자 화면 불러오기()
}
//<!-- Google tag (gtag.js) -->
//<script async src="https://www.googletagmanager.com/gtag/js?id=G-0NRHYLJTPK"></script>
//<script>
//  window.dataLayer = window.dataLayer || [];
//  function gtag(){dataLayer.push(arguments);}
//  gtag('js', new Date());
//
//  gtag('config', 'G-0NRHYLJTPK');
//</script>