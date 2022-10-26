package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T03ServletParameterTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 * 요청 객체로부터 파라미터 정보를 가져오는 방법
		 * 
		 * - getParameter() - 파라미터값이 한 개인 경우...
		 * - getParameterValues() - 파라미터값이 여러개인 경우..
		 *                          ex) 체크박스
		 * - getParameterNames() - 요청메시지에 존재하는 모든 파라미터 정보를 가져올 때 사용함.
		 */
		
		req.setCharacterEncoding("UTF-8"); // 이 부분 처리를 안하면 한글로 안 나옴
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String[] hobby = req.getParameterValues("hobby");
		
		String rlgn = req.getParameter("rlgn");
		String[] food = req.getParameterValues("food");
		
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>username : " + username + "</p>");
		out.println("<p>password : " + password + "</p>");
		out.println("<p>gender : " + gender + "</p>");
		out.println("<p>hobby : " + Arrays.toString(hobby) + "</p>");
		out.println("<p>rlgn : " + rlgn + "</p>");
		
		if(food != null) {
			for(String str: food) {
				out.print("<p>food : " + str + "</p>");
			}
		}
		
		Enumeration<String> params = req.getParameterNames();
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			out.print("<p>파라미터 이름 : " + param + "</p>");
		}
		out.print("</body></html>");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
