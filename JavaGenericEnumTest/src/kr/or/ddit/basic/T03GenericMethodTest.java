package kr.or.ddit.basic;

class Util2 {
	//extends 키워드를 이용해서 파라미터의 범위를 제한 할 수 있다.
	public static <T extends Number> int compare(T t1, T t2) {
		
		double v1 =  t1.doubleValue();
		double v2 =  t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}


/**
 * 제한된 타입 파라미터(Bounded Parameter) 예제
 *
 */
public class T03GenericMethodTest {
	public static void main(String[] args) {
		int result1 = Util2.compare(10, 20);
		
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3);
		
		System.out.println(result2);
		
//		Util2.compare("C", "JAVA"); //에러 발생
	}
}