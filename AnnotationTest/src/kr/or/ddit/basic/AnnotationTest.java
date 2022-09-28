package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		// 선언된 메서드 목록을 조회하고, 메서드를 실행해 보기
		Class<?> klass = Service.class;
		//Service service = new Service(); 
		Service service = (Service) klass.newInstance(); //리플랙션을 이용해서 만드는 기능 
		
		Method[] methodArr =klass.getDeclaredMethods();
		//Method[] methodArr = Service.class.getDeclaredMethods();
		
		for (Method m : methodArr) {
			
			System.out.print(m.getName() + " => ");
			Annotation[] annos = m.getDeclaredAnnotations();
			for (Annotation anno : annos) {
				
				if(anno.annotationType().getSimpleName().equals("PrintAnnotation")) {
					//어노테이션을 쓰려는 이유는 필요한 정보를 필요하는 곳에 제공해주려고,value,count
					PrintAnnotation printAnn = (PrintAnnotation) anno;
					for(int i=0;i<printAnn.count();i++) {
						System.out.print(printAnn.value()); //카운트 수만큼 돌면서 밸류값을 찍어보라는 의미
					}
				}
			}
			m.invoke(service);
			System.out.println();
		}
		//오늘은 어노테이션에 대해서만 알면 된다~ 오버라이딩이 주목적~
	}		
}
