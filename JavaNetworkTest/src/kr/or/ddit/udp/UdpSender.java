package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpSender {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg; // 패킷 송수신을 위한 바이트 배열 선언
	
	public UdpSender(int port) {
		try {
			// 메시지 수신을 위한 포트번호 설정
			ds = new DatagramSocket(port); // 소켓 객체 설정
		} catch (SocketException ex) {
			ex.printStackTrace(); 
		}
	}
	
	public void start() throws IOException {
		while (true) {
			
			// 데이터를 수신하기 위한 패킷을 생성한다.
			msg = new byte[1];
			dp = new DatagramPacket(msg, msg.length);
			
			System.out.println("패킷 수신 대기 중...");
			
			// 패킷을 통해 데이터를 수신(receive)한다.
			ds.receive(dp);
			
			System.out.println("패킷 수신 완료");
			
			// 수신한 패킷으로 부터 client의 IP주소 및 포트번호를
			// 얻는다.
			InetAddress ipAddr = dp.getAddress();
			int port = dp.getPort();
			
			// 서버의 현재 시간을 시분초 형태([hh:mm:ss])로 
			// 클라이언트에 보내준다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			
			// 시간문자열을 byte배열로 변환한다.
			msg = time.getBytes();
			
			// 패킷을 생성해서 Client에게 전송(send)한다.
			dp = new DatagramPacket(msg, msg.length, ipAddr, port);
			ds.send(dp); // 전송 시작...
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		new UdpSender(8888).start();
	}
}
