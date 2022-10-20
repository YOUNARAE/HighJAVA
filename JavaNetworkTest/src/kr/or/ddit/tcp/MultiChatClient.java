package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {

	// 클라이언트 시작
	public void clientStart() {
		Socket socket = null;
		
		try {
			socket = new Socket("localhost", 7777);
			
			System.out.println("서버에 연결되었습니다.");
			
			// 송신용 스레드 생성
			ClientSender sender = new ClientSender(socket);
			sender.start();
			
			
			// 수신용 스레드 생성
			ClientReciver receiver = new ClientReciver(socket);
			receiver.start();
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	// 메시지를 전송하는 스레드 클래스
	class ClientSender extends Thread {
		private DataOutputStream dos;
		private Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket) {
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		
		}
		
		@Override
		public void run() {
			
			try {
				if(dos != null) {
					// 시작하자마자 자신의 대화명을 서버로 전송
					System.out.print("대화명>>");
					dos.writeUTF(scan.nextLine());
				}
				
				while(dos != null) {
					//키보드로 입력받은 메세지를 서버로 전송
					dos.writeUTF(scan.nextLine());
				}
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class ClientReciver extends Thread{
		private DataInputStream dis;
		
		public ClientReciver(Socket socket) {
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dis != null) {
				// 서버로부터 수신한 메세지 출력
				try {
					System.out.println(dis.readUTF());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	public static void main(String[] args) {
		
		new MultiChatClient().clientStart();
	}
}
