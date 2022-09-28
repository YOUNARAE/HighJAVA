package kr.or.ddit.reflection;

import kr.or.ddit.basic.Service;

/**
 * Class오브젝트(클래스 정보를 담고 있는)를 생성하기
 *
 */

public class T01ClassObjectCreationTest {
/*
 	Java Reflection에 대하여...
 	
 	1. 자바 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메서드 생성자 등에 대한 정보를 가져오거나 수정할 수 있고
 	      새로운 객체를 생성하거나 메서드를 실행할 수 있다.(컴파일 시점에 해당정보를 알 수 없는 경우(소스코드 부재)에 유용하게 사용될 수 있음)
 	2. Reflection API는 java.lang.reflect패키지와 java.lang.Class를 통해 제공된다.
 	3. java.lang.Class의 주요 메서드
 	  - getName(), getSuperclass(), getInterfaces(), getModifiers() 등
	4. java.lang.reflect 패키지의 주요 클래스
	  - Field, Method, Constructor, Modifier 등.
*/
	public static void main(String[] args) throws ClassNotFoundException {
		
		// 첫번째 방법 : Class.forName() 이용하기
		Class<?> klass = Class.forName("kr.or.ddit.basic.Service");
		// 두번째 방법 : getClass() 이용하기
		Service service = new Service();
		Class<?> clazz = service.getClass(); //서비스 클래스에 대한 오브젝트를 가지고 올 수 있음
		
		//세번째 방법 : .class 이용
		clazz = Service.class; //클래스타입의 오브젝트를 얻어오는 방법
		
	}
}
