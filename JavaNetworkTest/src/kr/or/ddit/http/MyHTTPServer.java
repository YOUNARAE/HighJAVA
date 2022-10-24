package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
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
				
				//URL 디코딩 처리(한글깨짐문제 해결)
				reqPath = URLDecoder.decode(reqPath, encoding);
				
				String filePath = "./WebContent" + reqPath;
				
				// 해당 파일 이름을 이용하여 Content-type 정보 추출하기
				String contentType = URLConnection.getFileNameMap()
						             .getContentTypeFor(filePath);
				
				// CSS파일인 경우 인식이 안되서 추가함.
				if(contentType == null && filePath.endsWith(".css")) {
					contentType = "text/css";
				}
				
				File file = new File(filePath);
				if(!file.exists()) {
					makeErrorPage(out,404,"Not Found");
					return;
				}
				
				byte[] body = makeResponseBody(filePath);
				
				byte[] header = makeResponseHeader(body.length, contentType);
				
				
				// 응답 헤더 데이터 보내기
				out.write(header);
				
				// 응답 내용 보내기 전 반드시 Empty Line을 보내야한다.
				out.write("\r\n\r\n".getBytes());
				
				// 응답 내용 데이터 보내기
				out.write(body);
				out.flush();
				
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

	
	/**
	 * 
	 * @param out
	 * @param i
	 * @param string
	 */
	private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {
		String statusLine = "HTTP/1.1" + " " + statusCode + " " + errMsg;
		
		try {
			out.write(statusLine.getBytes());
			out.flush();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 응답헤더 생성하기
	 * @param length 응답내용 크기
	 * @param contentType MIME타입
	 * @return 헤더정보 바이트 배열
	 */
	private byte[] makeResponseHeader(int length, String contentType) {
		String header = "HTTP/1.1 200 OK\r\n"
				      + "Server: MyHTTPServer 1.0\r\n"
				      + "Content-length: " + length + "\r\n"
					  + "Content-type: " + contentType
					  + "; charset=" + this.encoding;
					  
		return header.getBytes();
	}
	
	/**
	 * 응답내용 생성하기
	 * @param filePath 응답으로 사용할 파일경로
	 * @return 바이트배열 데이터
	 */
	private byte[] makeResponseBody(String filePath) {
		
		FileInputStream fis = null;
		byte[] data = null;
		
		try {
			File file = new File(filePath); 
			// 버퍼를 파일 사이즈만큼 할당받기 위한 코드
			data = new byte[(int) file.length()];
			
			fis = new FileInputStream(file);
			fis.read(data);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
	//
	
	
	
	//
	public static void main(String[] args) {
		
		new MyHTTPServer().start();
		
	}
	
	
}
