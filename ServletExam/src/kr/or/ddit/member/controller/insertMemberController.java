package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@MultipartConfig
@WebServlet("/member/insert.do")
public class insertMemberController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	
		req.getRequestDispatcher("/WEB-INF/views/member/insertForm.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		
		//req.setCharacterEncoding("UTF-8");
		
		// 파라미터값 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		
		// 서비스 객체 생성하기
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		
		AtchFileVO atchFileVO = new AtchFileVO();
		
		// 첨부파일 목록 저장하기(공통기능)
		atchFileVO = fileService.saveAtchFileList(req);
				
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		mv.setAtchFileId(atchFileVO.getAtchFileId());
		
		int cnt = memberService.registMember(mv);
		
		String msg = "";
		
		if(cnt>0) {
			// 성공
			msg = "성공";
			
		} else {
			// 실패
			msg = "실패";
		}
		
		//같은 요청이 아니라서 리퀘스트로는 처리가 불가능
		//req.setAttribute("mgs", msg);
		req.getSession().setAttribute("msg", msg);
		//req.getRequestDispatcher("/member/list.do").forward(req, resp);
		
		resp.sendRedirect(req.getContextPath() + "/member/list.do"); //Redirect는 클라이언트 입장에서 주소를 적어줘야한다.
		
	}
}
