package kr.or.ddit.basic;

@FunctionalInterface
public interface Lambdainterface1 {
	// 반환값이 없고 매개변수도 없는 추상메서드
	 public void test();
}

@FunctionalInterface
interface LambdaInterface2 {
	// 반환값이 없고 매개변수도 없는 추상메서드
	public void test(int a);
}

@FunctionalInterface
interface LambdaInterface3 {
	// 반환값이 있고 매개변수도 있는 추상메서드
	public int test(int a, int b);
}