package kr.or.ddit.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
  Annotation에 대하여..
   
      프로그램 소스 코드 안에 다른 프로그램 을 위한 정보를 미리 약속된 형식으로 포함시킨 것 (JDK1.5부터 지원)
   
      주석처럼 프로그램에 영향을 미치지 않으면서도 다른 프로그램에게 유용한 정보를 제공함.
   
      종류 : 1. 표준 애너테이션
        2. 메타 애너테이션(애너테이션을 위한 애너테이션, 즉 애너테이션을 정의할 때 사용하는 애너테이션)
  
       
  Annotation 요소의 규칙
  
  1. 요소의 타입은 기본형, String, enum, annotation, class만 허용함.
  2. ()안에 매개변수를 선언할 수 없다.
  3. 예외를 선언할 수 없다.
  4. 요소의 타입에 타입 파라미터(제너릭타입글자)를 사용할 수 없다      
 */

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER}) //적용 대상 지정함. + 클래스도 타입이라고 한다
@Retention(RetentionPolicy.RUNTIME) // 유지기간 지정함. (기본:CLASS레벨까지 유지되는데, 런타임까지 유지시키고 싶으면 리텐션 런타임을 지정해줘야함)
public @interface PrintAnnotation { //제너릭 문법 사용X
	int id = 100; //상수 선언 가능, static final int id = 100;
	String value() default "-"; //스트링 타입의 value라는 요소를 기본값으로 만들고 싶다.괄호는 의미가 없다, 디폴트도 안 써도 되는데
	                              //디폴트값을 지정해주려고 우선 내용을 넣어준 상태이다.
	int count() default 20; // 인트 타입의 카운트라는 요소를 기본값으로 만들고 싶다, 메서드와 모양은 같지만 요소를 선언하는 문법이다
}
