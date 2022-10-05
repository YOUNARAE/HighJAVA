package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T02FileTest {
	public static void main(String[] args) {
		
		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		if(f1.exists()) {
			System.out.println(f1.getAbsolutePath() + "은 존재합니다. ");
		} else {
			System.out.println(f1.getAbsolutePath() + "은 없는 파일입니다. ");
			
			try {
				if(f1.createNewFile()) {
					System.out.println(f1.getAbsolutePath() + "파일을 새로 만들었습니다.");
				}
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
		if(f2.exists()) {
			System.out.println(f2.getAbsolutePath() + "은 존재합니다.");
		} else {
			System.out.println(f2.getAbsolutePath() + "은 없는 파일입니다.");
		}
		System.out.println("-------------------------------------------");
	
		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles();
		
		for (File f : files) {
			System.out.print(f.getName() + " => ");
			
			if(f.isFile()) {
				System.out.println("파일");
			} else if(f.isDirectory()) {
				System.out.println("디렉토리");
			}
		}
		System.out.println("===========================================");
		String[] strFiles = f3.list();
		
		for (String str : strFiles) {
			System.out.println(str);
		}
		
		System.out.println("-------------------------------------------");
		System.out.println();
	}
}
