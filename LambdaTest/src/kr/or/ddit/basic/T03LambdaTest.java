package kr.or.ddit.basic;

public class T03LambdaTest {
	static int stVar = 0;
	private String name = "";
	
	public void testMethod(final int temp) {
		
		final int localVar = 50; 
		int kor = 100;
		/*
		    람다식 내부에서 사용되는 지역변수는 모두 final 이어야 한다.
		    보통은 final을 붙이지 않으면 컴파일러가 자동으로 붙여준다.
		    단, 지역변수의 값을 변경하는 식이 있을 경우에는 자동으로 붙여주지 않는다.
		 */
		
		
		// 람다식에서 지역변수 사용하기
		Lambdainterface1 lam1 = 
			() -> {
				System.out.println("temp = " + temp);
				System.out.println("localVar  = " + localVar);
				System.out.println("kor = " + kor);
				System.out.println("stVar = " + stVar);
				System.out.println("name = " + this.name);
			};
		lam1.test(); //람다식 익명객체 실행
	}
	
	public static void main(String[] args) {
		new T03LambdaTest().testMethod(200);
	}
}
