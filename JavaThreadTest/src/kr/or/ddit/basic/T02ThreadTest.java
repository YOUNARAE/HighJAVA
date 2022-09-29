package kr.or.ddit.basic;

/**
 * 멀티스레드 프로그램 생성 예제
 * 멀티쓰레드 : 동시 실행되는 프로그램이라는 개념
 * 현재 3개의 쓰레드를 추가하였고 메인에 이미 1개가 항상 실행중이라고 생각하면 된다 => 총 4개의 쓰레드를 실행중이다
 */

public class T02ThreadTest {
	public static void main(String[] args) {
		
		// 방법 1 : Thread 클래스를 상속한 class의 인스턴스를 생성한 후 이 인스턴스의 start()메서드를 호출한다.
		Thread th1 = new MyThread1();
		th1.run(); //꼭 실행을 시켜줘야한다.
		 
		// 방법 2 : Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 파라미터로 넘겨준다.
		//        이렇게 생성된 인스턴스의 start()메서드를 호출한다.
		// 다중상속이 안되는 자바의 특징이 있기때문에 나온 방법이다. 익스텐즈를 한번 하면 다른 상속을 할 수가 없다. 러너블이라는 객체를 따로 만들어서 쓰레드객체를 만들어내는 방법이다.
		Runnable r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.run();
		
		// 방법 3 : 익명클래스를 이용하는 방법 / 1회용 용도일 때 사용한다.
		//        Runnable인터페이스를 구현한 익명클래스르르 Thread 인스턴스를 생성할 때 매개변수로 넣어준다.
		Thread th3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1; i<=200; i++) {
					System.out.print("@");
					
					
					try {
						// Thread.sleep(시간) => 주어진 시간만큼 잠시 멈춘다.
						// 시간은 밀리세컨드 단위이다. 
						// (즉, 1000ms = 1초, 100ms = 0.1초) 
						Thread.sleep(100);
					} catch (InterruptedException e) { //잠자고 있는 동안 깨울 수 있다는 예외발생할 수 있다
						e.printStackTrace(); 
					}
				}
			}
		});
		th3.start();
		
	}
}

class MyThread1 extends Thread{
	@Override
	public void run() { //스레드라면 무조건 가지고 있는 런 메서드**중요하다
		
		for(int i=1; i<=200; i++) {
			System.out.print("*");
			
			
			try {
				// Thread.sleep(시간) => 주어진 시간만큼 잠시 멈춘다.
				// 시간은 밀리세컨드 단위이다. 
				// (즉, 1000ms = 1초, 100ms = 0.1초) 
				Thread.sleep(100);
			} catch (InterruptedException e) { //잠자고 있는 동안 깨울 수 있다는 예외발생할 수 있다
				e.printStackTrace(); 
			}
		}
	}
}


class MyThread2 implements Runnable{

	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("$");
			
			
			try {
				// Thread.sleep(시간) => 주어진 시간만큼 잠시 멈춘다.
				// 시간은 밀리세컨드 단위이다. 
				// (즉, 1000ms = 1초, 100ms = 0.1초) 
				Thread.sleep(100);
			} catch (InterruptedException e) { //잠자고 있는 동안 깨울 수 있다는 예외발생할 수 있다
				e.printStackTrace(); 
			}
		}
	}

}
