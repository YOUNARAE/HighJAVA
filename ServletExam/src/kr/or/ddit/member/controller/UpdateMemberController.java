package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 값 조회
		String memId = req.getParameter("memId");

		// 서비스객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();

		MemberVO mv = memService.getMember(memId);
		
		

		if (mv.getAtchFileId() > 0) { // 첨부파일이 존재하면...

			// 첨부파일 목록 조회
			IAtchFileService fileService = AtchFileServiceImpl.getInstance();
			// 파일 목록 조회하는 서비스를 호출한다.

			AtchFileVO atchFileVO = new AtchFileVO();
			atchFileVO.setAtchFileId(mv.getAtchFileId());

			// 파일목록 조회 호출
			List<AtchFileVO> atchFileList = fileService.getAtchFileList(atchFileVO);

			req.setAttribute("atchFileList", atchFileList);
		}
		
		

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
		long atchFileId = Long.parseLong(req.getParameter("atchFileId"));		
		
		
		

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
		if(atchFileVO == null) { // 첨부를 안했다면(첨부파일이 null인 상태라면)
			mv.setAtchFileId(atchFileId); //파일이 없다면 기존에 있는 아이디를 불러옴
		} else {
			mv.setAtchFileId(atchFileVO.getAtchFileId());
		}
		
		
		
		

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
