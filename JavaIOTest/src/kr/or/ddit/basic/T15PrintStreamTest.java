package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린터 기능을 제공하는 보조스트림 예제
 */

public class T15PrintStreamTest {
	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");
		
		/*
		  PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 
		  OutputStream의 서브클래스이다.
		 */
		// PrintStream out = new PrintStream(System.out);
		PrintStream out = new PrintStream(fos);
		
		out.print("안녕하세요. PrintStream 입니다.\n");
		out.println("안녕하세요. PrintStream입니다.2");
		out.println("안녕하세요. PrintStream입니다.3");
		out.print(out);
		out.print(3.14f);
		
		/*
		PrintStream은 데이터를 문자로 출력하는 기능을 수행함
		향상된 기능의 PrintWriter가 추가되었지만 계속 사용됨.
		    
		PrintWriter가 PrintStream보다 다양한 문자를 처리하는데 적합하다.
		*/
		
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print2.txt");
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "UTF-8"));
		
		pw.print("안녕하세요. PrintWriter 입니다.\n");
		pw.println("안녕하세요. PrintWriter 입니다.2");
		pw.println("안녕하세요. PrintWriter 입니다.3");
		pw.println(pw);

		pw.close();
		
	}
}
