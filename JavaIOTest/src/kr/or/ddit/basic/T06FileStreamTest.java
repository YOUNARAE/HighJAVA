package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일 출력(저장) 예제
 */
public class T06FileStreamTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null; //우리가 만든 데이터를 보조디스크로 보낼려고 아웃풋스트림을 이용하려고 한다.
		
		try {
			// 출력을 위한 FileOutputStream객체 생성
			fos = new FileOutputStream("d:/D_Other/out.txt", true); //out.txt가 만들어진다.
			
			for(char ch='a'; ch<='z'; ch++) {
				fos.write(ch);
			}
			System.out.println("파일 쓰기 작업 완료....");
			
			// 파일 읽기
			FileInputStream fis = new FileInputStream("d:/D_Other/out.txt");
			
			int data = 0;
			while((data = fis.read()) != -1) {
				System.out.print((char) data);
			}
			fis.close();
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try{
				fos.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
