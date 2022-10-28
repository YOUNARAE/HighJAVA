package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T07ServletContextTest extends HttpServlet {
	/*
	 * 서블릿 컨텍스트 객체에 대하여...
	 * 
	 *  1. 서블릿 프로그램이 컨테이너와 정보를 주고 받기 위한 메서드를 제공함.
	 *    ex) 파일의 MIME타입정보 가져오기, 요청정보 보내기, 로깅 등
	 *  
	 *  2. 웹애플리케이션당 1개씩 생성됨.
	 *  
	 *  3. 서블릿 컨텍스트 객체는 서블릿이 초기화 될 때 ServletConfig객체를 
	 *     통해서 얻을 수 있다.
	 *     
	 */
	
	
	/*
	 * @Override public void init(ServletConfig config) throws ServletException {
	 * super.init(config); // 초기화할 떄 config를 넣어줌
	 * System.out.println(config.getServletContext()); }
	 */
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext ctx = req.getServletContext();
		ctx = getServletContext();
		
		System.out.println("서블릿 컨텍스트의 기본 경로 : " + ctx.getContextPath());
		System.out.println("서버 정보 : " + ctx.getServerInfo());
		System.out.println("서블릿 API의 메이저 버전 정보 : " + ctx.getMajorVersion());
		System.out.println("서블릿 API의 마이너 버전 정보 : " + ctx.getMinorVersion());
		System.out.println("배포기술자에 기술된 컨텍스트명 : " + ctx.getServletContextName());
		System.out.println("리소스 경록 목록 : " + ctx.getResourcePaths("/"));
		System.out.println("파일에 대한 MIME타입 정보 : " + ctx.getMimeType("abc.mp4"));
		System.out.println("파일 시스템 상의 실제 경로 : " + ctx.getRealPath("/"));
		
		// 속성값 설정
		ctx.setAttribute("attr1", "속성1");
		
		// 속성값 변경
		ctx.setAttribute("attr1", "속성2");
		
		// 속성값 가져오기
		System.out.println("attr1의 속성값 : " + ctx.getAttribute("attr1"));
		
		// 속성값 지우기
		ctx.removeAttribute("attr1");
		
		// 로깅작업
		ctx.log("서블릿 컨텍스트를 이용한 로깅작업 중입니다....");
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
