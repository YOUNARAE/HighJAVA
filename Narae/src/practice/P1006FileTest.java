package practice;

import java.io.File;
import java.io.IOException;

public class P1006FileTest {
	public static void main(String[] args) throws IOException {
		
		File file = new File("d:\\D_Other\\test_narae.txt");
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일 여부 : " + file.isFile());
		System.out.println("디렉토리(폴더) 여부 : " + file.isDirectory());
		System.out.println("-----------------------------------------");
		
		File file2 = new File("d:\\D_Other");
		System.out.println(file2.getName() + "은 ");
		if(file2.isFile()) {
			System.out.print("파일");
		} else if(file2.isDirectory()) {
			System.out.println("디렉토리(폴더)");
		}
		System.out.println("-----------------------------------------");
		
		File file3 = new File(file2, "test_narae.txt");
		System.out.println(file3.getName() + "의 용량 크기 : " + file3.length() + "(bytes)");
		
		File file4 = new File(".\\D_Other\\test\\..", "test_narae.txt");
		System.out.println("절대 경로 : " + file4.getAbsolutePath());
		System.out.println("경로 : " + file4.getPath());
		System.out.println("표준(정식)경로 : " + file4.getCanonicalPath());
		
		/* 디렉토리(폴더) 만들기 */
		//mkdir ( make directory )
		//mkdirs ( make directorys )
		
		File file5 = new File("d:/D_Other/혼자연습용");
		if(file5.mkdir()) {
			System.out.println(file5.getName() + "만들기 성공!");
		} else {
			System.out.println(file5.getName() + "만들기 실패 !!!! 다시해.");
		}
		System.out.println();
		
		
		
		File file6 = new File("d:/D_Other/test_narae/java/src");
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + " 만들기 성공!!");
		} else {
			System.out.println(file6.getName() + " 만들기 실패!!!");
		}
		
	}
}

