package kr.or.ddit.basic;

/**
 * 문자 출력하기 예제
 */

public class T09CharTest {
	public static void main(String[] args) {
		
		char c1 = 'A'; //문자를 직접 저장
		char c2 = '\u0041'; //16진수 저장(유니코드)
		char c3 = 65; //10진수로 저장
		
		char c4 = '가'; // 문자를 직접 저장
		char c5 = 44032; // 10진수로 저장
		char c6 = '\uAC00'; //16진수로 저장(유니코드)
		
		int uniCode = c1; // 유니코드 값 얻기
		
		System.out.println("c1 => " + c1);		
		System.out.println("c2 => " + c2);		
		System.out.println("c3 => " + c3);		
		System.out.println("c4 => " + c4);		
		System.out.println("c5 => " + c5);		
		System.out.println("c6 => " + c6);		
		System.out.println("uniCode => " + uniCode);		
		
	}
}
