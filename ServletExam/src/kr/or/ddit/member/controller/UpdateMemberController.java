package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 값 조회
		String memId = req.getParameter("memId");

		// 서비스객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();

		MemberVO mv = memService.getMember(memId);

		req.setAttribute("mv", mv);

		// JSP에게 포워딩 처리
		req.getRequestDispatcher("/WEB-INF/views/member/updateForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 파라미터값 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");

		// 서비스 객체 생성하기
		IMemberService memberService = MemberServiceImpl.getInstance();

		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);

		int cnt = memberService.modifyMember(mv);
		
		String msg = "";
		
		if(cnt>0) {
			// 성공
			msg = "성공";
			
		} else {
			// 실패
			msg = "실패";
		}
		req.getSession().setAttribute("msg", msg);

		resp.sendRedirect(req.getContextPath() + "/member/list.do"); 
		// Redirect는 클라이언트 입장에서 주소를 적어줘야한다.

	}
}
