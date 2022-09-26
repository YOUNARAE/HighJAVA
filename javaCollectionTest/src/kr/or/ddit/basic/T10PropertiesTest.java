package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class T10PropertiesTest {
	public static void main(String[] args) throws IOException {
		/*
		 * 
		 * Properties는 Map보다 축소된 기능의 객체라고 할 수 있다.
		 * Map은 모든 형태의 객체데이터를 key와  value값으로 사용할 수 있지만 ㄴ
		 * Properties는 key와 value값으로 String만 사용할 수 있다.
		 * 
		 * Map은 put(), get() 메서드를 이용하며 데이터를 입출력 하지만, 
		 * Properties는 setProperty(), getProperty() 메서드를 사용한다.
		 * 
		 */
		Properties prop = new Properties(); //모든 키와 밸류는 스트링으로만 관리가 된다
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("tel", "010-1234-5678");
		prop.setProperty("addr", "대전");
		
		String name = prop.getProperty("name");
		String tel = prop.getProperty("tel");
		
		System.out.println("이름 : " + name);
		System.out.println("전화 : " + tel);
		System.out.println("주소 : " + prop.getProperty("addr"));
		
		//데이터 저장하기  , 쓴 걸 저장하고 싶으면 store를 이용한다
		prop.store(new FileOutputStream("src/kr/or/ddit/basic/test.properties"), "코멘트 정보입니다."); //프로퍼티스 파일로 저장하면 읽고 쓰는 것이 자유롭다
		//프로퍼티스는 키,밸류로 이루어져있고 스트링타입만 취급한다
		
	}
}
