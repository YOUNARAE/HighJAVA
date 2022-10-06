package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

public class T08FileReaderTest {
	public static void main(String[] args) {
		
		// 문자기반 스트림을 이용한 파일 내용 읽기
		FileReader fr = null;
		
		try {
			// 문자 단위의 입력을 담당하는 Reader 객체 생성
			fr = new FileReader("d:/D_Other/testChar.txt");
			
			int data = 0;
			
			while((data = fr.read()) != -1) {
				System.out.print((char) data);
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fr.close();				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}//main End
}
