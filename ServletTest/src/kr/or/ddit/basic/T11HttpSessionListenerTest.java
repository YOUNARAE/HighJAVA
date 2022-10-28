package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T11HttpSessionListenerTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		session.setAttribute("ATTR1", "속성1"); // 추가
		session.setAttribute("ATTR1", "속성11"); // 속성변경
		session.setAttribute("ATTR2", "속성2"); //속성2를 추가
		
		session.removeAttribute("ATTR1"); //속성1을 삭제
		
		// session.invalidate(); // 세션을 바로 로그아웃시켜버리기
		
		// HTTP 세션 영역내에 HttpSessionBindingListener를 구현한 
		// 객체가 바인딩 되면 호출됨.
		MySessionBindingListener bindingListener = new MySessionBindingListener();
		
		session.setAttribute("obj", bindingListener);
		session.removeAttribute("obj");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
