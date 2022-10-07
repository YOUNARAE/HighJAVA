package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 성능향상을 위한 보조스트림 예제
 */
public class T12BufferedIOTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null; // 버퍼 기능이 제공되는 아웃풋스트림
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			// 버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기다
			// 8192byte(8kb)로 설정된다.
					
			// 버퍼크기가 5인 스트림 생성
			bos = new BufferedOutputStream(fos, 5);
			
			for(char ch='1'; ch<='9'; ch++) {
				bos.write(ch);
			}
			
			bos.flush(); // 작업을 종료하기전에 버퍼에 남아있는 데이터를 모두 출력시킨다
			             // (close시 자동호출됨)
			System.out.println("작업 끝...");
					
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
