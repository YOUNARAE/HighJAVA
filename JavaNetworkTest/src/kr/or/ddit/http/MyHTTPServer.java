package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * 간단한 웹서버 예제
 */
public class MyHTTPServer {

	private final int port = 80; // 멤버변수에 상수값 정의
	private final String encoding = "UTF-8";
	
	public void start() {
		System.out.println("HTTP 서버가 시작되었습니다.");
		
		try(ServerSocket server = new ServerSocket(this.port)) {
			while (true) {
				try {
					Socket socket = server.accept();
					
					// Http 요청 처리를 위한 스레드 객체 생성
					HttpHander hander = new HttpHander(socket);
					hander.start();
					
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				
			}
	
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} // 클로즈 작업 따로 안 해줌
	}//
	
	/**
	 * Http 요청 처리를 위한 스레드 클래스
	 */
	private class HttpHander extends Thread {

		private final Socket socket;
		
		public HttpHander(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			OutputStream out = null;
			BufferedReader br = null;
					
			try {
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				// 요청 헤더 정보 파싱하기
				StringBuilder sb = new StringBuilder();
				
				// Request Line(첫줄은 요청라인)
				String reqLine = br.readLine();
				
				printMsg("Request Line : ", reqLine);
				
				while (true) {
					String str = br.readLine();
					
					// Empty Line 체크
					if(str.equals("")) break;
					
					sb.append(str + "\n");
				}
				
				// 헤더 정보(Header)
				String reqHeaderStr = sb.toString();
				
				printMsg("요청헤더 : ", reqHeaderStr);
				
				String reqPath = ""; // 요청 경로
				
				//요청 페이지 정보 가져오기
				StringTokenizer st = new StringTokenizer(reqLine); 
				//(reqLine,"#") 기본값은 공백,
				
				while(st.hasMoreTokens()) {
					String token = st.nextToken();
					if(token.startsWith("/")) {
						reqPath = token;
						break;
					}
				}
				
				System.out.println("reqPath =>" + reqPath);
				
				
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void printMsg(String title, String msg) {
			System.out.println("==================================");
			System.out.println("title");
			System.out.println("==================================");
			System.out.println(msg);
			System.out.println("----------------------------------");
		}
		
	}
	//
	public static void main(String[] args) {
		
		new MyHTTPServer().start();
		
	}
	
	
}
