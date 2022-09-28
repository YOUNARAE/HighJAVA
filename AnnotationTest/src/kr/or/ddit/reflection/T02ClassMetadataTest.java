package kr.or.ddit.reflection;

import java.lang.reflect.Modifier;

import kr.or.ddit.basic.Service;

public class T02ClassMetadataTest {
	//클래스를 설명하기 위해 사용할 정보들을 메타정보들이라고 하고 그 메타정보들에 접근하기 위해 리플랙션 API를 이용할 것이다
	
	public static void main(String[] args) {
		
		// 클래스 오브젝트 생성하기
		Class<?> clazz = Service.class;
		
		System.out.println("심플클래스명 : " + clazz.getSimpleName());
		System.out.println("클래스명 : " + clazz.getName());
		System.out.println("상위클래스명 : " + clazz.getSuperclass().getName());
		
		// 패키지 정보 가져오기
		Package pkg = clazz.getPackage();
		System.out.println("패키지 정보 : " + pkg.getName());
		
		// 해당클래스에서 구현하고 있는 인터페이스 목록 가져오기
		Class<?>[] interfaces = clazz.getInterfaces();
		
		System.out.println("인터페이스 목록 => "); //인터페이스는 여러개 임플리먼트 할 수 있기때문에 배열로 내보내줌
		for (Class<?> inf : interfaces) {
			System.out.println(inf.getName() + " | ");
		}
		System.out.println();
		
		// 클래스의 접근제어자 정보 가져오기
		// (flag bit값 변환됨 => 접근제어자의 유무 체크정보)
		int modFlag = clazz.getModifiers();
		
		System.out.println("접근제어자 : " + Modifier.toString(modFlag));
		
		
		
		//여기까지 자바의 리플렉션 기술을 통해서 클래스의 정보를 얻어오는 것을 해보았다.
	}
}
