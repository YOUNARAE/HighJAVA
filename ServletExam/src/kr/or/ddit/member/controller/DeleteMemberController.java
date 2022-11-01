package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

@WebServlet("/member/delete.do")
public class DeleteMemberController extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String memId = req.getParameter("memId");
			IMemberService memService = MemberServiceImpl.getInstance();
			
			int cnt = memService.removeMember(memId);
			
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
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req,resp);
		}
}
