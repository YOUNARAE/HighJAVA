package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T1006FileStreamTest2 {
/**
 * 파일 출력(저장) 예제
 */
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		
		try {
			
			fos = new FileOutputStream("d:/D_Other/out.txt", true);
	
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
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
