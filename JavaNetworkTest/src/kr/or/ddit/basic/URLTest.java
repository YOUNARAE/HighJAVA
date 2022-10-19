package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLTest {
	// URL 클래스 => 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 
	//               주소를 관리하는 클래스
	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		
		URL url = new URL("http","ddit.or.kr", 80, 
				"/main/index.html?name=홍길동&age=30#kkk");	
		// throws MalformedURLException 형태가 안 맞으면 발생하는 예외
		
		System.out.println("전체 URL주소 : " + url);
		
		System.out.println("protocol : " + url.getProtocol());
		System.out.println("host : " + url.getHost());
		System.out.println("query : " + url.getQuery());
		System.out.println("file : " + url.getFile()); // 쿼리 정보 포함
		System.out.println("path : " + url.getPath()); // 쿼리 정보 미포함
		System.out.println("port : " + url.getPort()); 
		System.out.println("ref : " + url.getRef());
		
		System.out.println(url.toExternalForm());
		System.out.println(url.toString());
		System.out.println(url.toURI().toString()); //throw URISyntaxException
	
	
	}
}
