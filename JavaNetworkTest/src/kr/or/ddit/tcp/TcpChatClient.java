package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpChatClient {
	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("localhost", 7777);
			System.out.println("서버에 연결되었습니다.");
			
			Sender sender = new Sender(socket);
			sender.start();
			
			Receiver receiver = new Receiver(socket);
			receiver.start();

			
		} catch (UnknownHostException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
