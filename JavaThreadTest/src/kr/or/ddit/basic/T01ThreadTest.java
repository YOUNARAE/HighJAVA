package kr.or.ddit.basic;

/*
 * 싱글스레드 프로그램 에제
 * 
 */
public class T01ThreadTest {
	public static void main(String[] args) {
		
		for(int i=1; i<=200; i++) {
			System.out.print("*");
		}
		System.out.println();
		
		for(int i=1; i<=200; i++) {
			System.out.print("$");
		}
	}
}
