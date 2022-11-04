

import java.io.File;
import java.io.IOException;

public class T01FileTest {
	public static void main(String[] args) throws IOException {
		
		// File객체 만들기 연습
		
		// 1.new File(String 파일 또는 경로명) => 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는 '\' 또는 '/'를 사용할 수 있다.
		// 객체를 만든다고 파일이 만들어지는 것이 아님. 준비작업일 뿐
		File file = new File("d:\\D_Other\\test.txt"); //escape 문자기 때문에 \\ 두 번!
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일 여부 : " + file.isFile());
		System.out.println("디렉토리(폴더) 여부 : " + file.isDirectory());
		
		System.out.println("-----------------------------------------");
		
		File file2 = new File("d:\\D_Other"); //파일과 디렉토리 모두 관리하는 클래스
		
		System.out.println(file2.getName() + "은");
		if (file2.isFile()) {
			System.out.print("파일");
		} else if(file2.isDirectory()) {
			System.out.println("디렉토리(폴더)");
		}
		System.out.println("-----------------------------------------");
		
		// 2.new File(File parent, String child) => 'parent' 디렉토리 안에 있는 'child' 파일 또는 디렉토리를 갖는다.
		File file3 = new File(file2, "test.txt");
				System.out.println(file3.getName() + "의 용량크기" + file3.length() + "(bytes)");
		
		// 3.new File(String parent, String child) => 생성자는 여러 개가 있다!라는 것을 보여주는 것
		File file4 = new File(".\\D_Other\\test\\..", "test.txt"); // ./~/~ -> .은 현재경로 
		// 절대경로 교탁 앞에 있는 사람 = 찬영씨
		// 상대경로 내 옆에 있는 사람 = 시윤이, 옮기면 나래누나
		System.out.println("절대 경로 : " +  file4.getAbsolutePath()); 
		System.out.println("경로 : " + file4.getPath());
		System.out.println("표준 경로 : " + file4.getCanonicalPath());
		
		/*
		 * 디렉토리(폴더) 만들기 -> 일단 폴더 만들기 권한이 있어야 함.
		 * 
		 * 1. mkdir() 	=> File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		 * 				=> 중간의 경로가 모두 미리 만들어져 있어야 한다.
		 * 
		 * 2. mkdirs()	=> 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 마지막 위치의 디렉토리를 만들어준다.
		 * 
		 * => 위 두 메서드 모두 만들기를 성공하면 true, 실패하면 false를 반환함.(이미 있으면 false)
		 */
		
		File file5 = new File("d:/D_Other/연습용");
		if (file5.mkdir()) {
			System.out.println(file5.getName() + " 만들기 성공!");
		} else {
			System.out.println(file5.getName() + " 만들기 실패ㅜ");
		}
		System.out.println();
		
		File file6 = new File("d:/D_Other/test/java/src");
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + " 만들기 성공!");
		} else {
			System.out.println(file6.getName() + " 만들기 실패ㅜ");
		}
		System.out.println();
	}
}
