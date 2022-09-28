package kr.or.ddit.basic;

import java.io.Serializable;

@PrintAnnotation
public class Service implements Serializable { //시리얼라이지블은 껍데기만 있어서 오버라이딩 해주지 않아도 된다
	
	@PrintAnnotation
	public void method1() throws Exception {
		System.out.println("메서드1에서 출력되었습니다");
	}
	
	@PrintAnnotation(value = "%") //-와 20이 디폴트값 , value를 생략해도 된다 "%"
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다");
	}
	
	@PrintAnnotation(value = "#", count = 25)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다");
	}
	
}
