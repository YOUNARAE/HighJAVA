package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * FileWriter 예제
 */

public class T07FileWriterTest {
	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기
		
		// 콘솔(표준입력장치)과 연결된 입력용 문자 스트림객체 생성하기
		// InputStreamReader => 바이트기반 스트림을 문자기반 스트림으로 변환해주는 보조 스트림이다.
		// 
		InputStreamReader isr = new InputStreamReader(System.in); //System.in은 콘솔을 의미한다
		
		FileWriter fw = null; //파일 출력용 문자 기반 스트림, 보조스트림
		
		
		try {
			// 파일 출력을 위한 스트림 객체 생성하기
			fw = new FileWriter("d:/D_Other/testChar.txt");
			
			int data = 0;
			
			System.out.println("아무거나 입력하세요.");
			
			// 콘솔에서 입할 때 입력의 끝을 나타내는 표시는 Ctrl + z키를 누르면 된다.
			while((data = isr.read()) != -1) {
				fw.write(data);
			}
			System.out.println("작업 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				isr.close();
				fw.close();				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
	}
}
