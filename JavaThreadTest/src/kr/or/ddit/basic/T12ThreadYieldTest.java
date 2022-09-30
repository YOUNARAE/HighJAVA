package kr.or.ddit.basic;

/**
 * 양보 개념의 메서드 스레드
 * 비양보 양보 랜덤으로 끝나는 비율, 이 메서드를 사용하고 믿어버리면 안된다는 의미로 풀어본 예제
 */
public class T12ThreadYieldTest {
/*
   yield() 메서드에 대하여...
   
   1. 현재 실행 대기중인 동등한 우선순위 이상의 다른 스레드에게 실행기회를 제공한다.(양보)
   2. 현재 실행중인 스레드의 상태를 Runnable 상태로 바꾼다.
   3. yield()메서드를 실행한다고 해서 현재 실행중인 스레드가 곧바로 Runnable 상태로 전이된다고 확신할 수 없다.
   
 */
	public static void main(String[] args) {
		
		Thread th1 = new YieldThreadEx1(); //양보하는 스레드
		Thread th2 = new YieldThreadEx2();
		
		th1.start();
		th2.start();
		
	}
}

class YieldThreadEx1 extends Thread{
	public YieldThreadEx1() {
		super("양보 스레드");
	}
	@Override
	public void run() {
		for(int i=0;i<10; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			
			Thread.yield(); //양보하기
		}
	}
}

class YieldThreadEx2 extends Thread{ 
	public YieldThreadEx2() {
		super("비양보 스레드");
	}
	@Override
	public void run() {
		for(int i=0;i<10; i++) { //for문 10번 돌면서 
			System.out.println(Thread.currentThread().getName() + " : " + i); //currentThread메서드는 현재 돌고 있는 스레드의 객체에 접근할 수 있다, 현재 실행중인 스레드의 겟 네임을 갖고올 수 있음
		}
	}
}