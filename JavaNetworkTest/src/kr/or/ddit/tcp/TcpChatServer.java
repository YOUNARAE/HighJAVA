package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatServer {
	public static void main(String[] args) {
		
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버 준비 완료...");
			socket = server.accept(); 
			System.out.println("클라이언트가 접속되었습니다.");
			
			Sender sender = new Sender(socket); //방금 만든 소켓을 만들어서 넣어준다
			sender.start();
			
			Receiver receiver = new Receiver(socket);
			receiver.start(); // 이렇게 실행하면 메인쓰레드만 먼저 죽는다

			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
				
		
	}
}
