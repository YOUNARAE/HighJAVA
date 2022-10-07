package kr.or.ddit.basic.homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TulipsCopy {
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream("d:/D_Other/Tulips.jpg");
			fos = new FileOutputStream("d:/D_Other/Tulips_복사본.jpg");
			
			int copy;
			
			while((copy = fis.read()) != -1) {
				fos.write(copy);
			}
			System.out.println("파일 복사 작업 완료....");
			
			fis.close();
			fos.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
