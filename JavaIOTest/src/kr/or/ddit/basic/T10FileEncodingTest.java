package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T10FileEncodingTest {
/*
  한글 인코딩 방식에 대하여...
   
 	한글인코딩 방식은 크게 UT-8과 EUC-KR 방식, 두가지로 나누어 볼 수 있다.
 	원래 한글윈도우는 CP949방식을 사용했는데, 윈도우를 개발한 마이크로소프트에서
	EUC-KR방식에서 확장하였기 때문에 MS949라고도 부른다.
	한글윈도우의 메모장에서 말하는 ANSI 인코딩이랑 CP949(Code Page 949)를 말한다.
	CP949는 EUC-KR의 확장이며, 하위 호완성이 있다.
   
	- MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI계열)
	- UTF-8 => 유니코드 UTF-8 인코딩 방식(영문자 및 숫자 : 1byte, 한글: 3byte)
	- US-ASCII => 영문전용 인코딩 방식
	- 유니코드 => 서로 다른 문자 인코딩을 사용하는 컴퓨터간의 문서교환에 어려움을 겪게 되고,
	     이런 문제점을 해결하기 위해 전 세계의 모든 문자를 하나의 통일된 문자집합(CharSet)으로 표현하게 되었다.
   
	ANSI는 처음에는 영어를 표기하기 위해 만든 코드로 규격자체에 한글이 없었다가
	나중에 여기에 EUC-KR, CP949 라는 식으로 한글을 포함시켜 사용하였다.
*/
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			//fis = new FileInputStream("d:/D_Other/test_ansi.txt");
			fis = new FileInputStream("d:/D_Other/test_utf8.txt");
			
			// 파일 인코딩 정보를 이용하여 읽어오기
			// InputStreamReader객체는 파일의 인코딩 방식을 지정할 수 있다.
			// 형식) new InputStreamReader(바이트기반스트림객체, 인코딩방식);
			// isr = new InputStreamReader(fis, "EUC-KR");
			isr = new InputStreamReader(fis, "UTF-8");
			
			int data = 0;
			
			while((data = isr.read()) != -1) {
				System.out.print((char) data);
			}
			System.out.println();
			System.out.println("출력 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			
			try {
				isr.close(); //보조 스트림만 닫아도 된다
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
}
