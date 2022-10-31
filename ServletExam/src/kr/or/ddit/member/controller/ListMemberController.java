package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet(value="/member/list.do")
public class ListMemberController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		// 회원목록 조회
		List<MemberVO> memList = memService.selectAllMember();
		
		req.setAttribute("memList", memList); // req에 잠시 파라미터로 받아서 임시 담아놓음
		
		
		// 회원목록 화면 처리하기
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/list.jsp");
		dispatcher.forward(req, resp); // req,resp 받아놓은 값들을 그대로 던져준다<-memList가 들어있음
		
		
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
