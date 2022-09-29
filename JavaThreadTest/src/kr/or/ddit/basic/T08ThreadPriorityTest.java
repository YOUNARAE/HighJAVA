package kr.or.ddit.basic;
/**
 *  
 */
public class T08ThreadPriorityTest {
	public static void main(String[] args) {
		
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY); //10
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY); //1
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY); //5
		
		Thread[] ths = new Thread[] {
				new ThreadTest1(), 
				new ThreadTest1(), 
				new ThreadTest1(), 
				new ThreadTest1(), 
				new ThreadTest1(), 
				new ThreadTest2() 
		};
		
		// 우선순위는 start()메서드 호출전에 설정해야 한다.
		for(int i=0; i<ths.length; i++) {
			if(i==5) {
				ths[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				ths[i].setPriority(Thread.MIN_PRIORITY);
			}
		}
		
		// 우선순위 출력
		for(Thread th : ths) {
			System.out.println(th.getName() + "의 우선순위  : " + th.getPriority());
		}
		
		for(Thread th : ths) {
			th.start(); //설정 값을 맞춰놓은 후에 스타트를 시작해야된다
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
			
	}//main
}

// 대문자를 출력하는 스레드
class ThreadTest1 extends Thread {
	
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(ch);
		
			// 아무것도 하지 않는 반복문 (시간때우기 용)
			for(long i=1; i<=1000000000; i++) {}
		}
	}
	
}

//소문자를 출력하는 스레드
class ThreadTest2 extends Thread {
	
	@Override
	public void run() {
		for(char ch='a'; ch<='z'; ch++) {
			System.out.println(ch);
		
			// 아무것도 하지 않는 반복문 (시간때우기 용)
			for(long i=1; i<=1000000000; i++) {}
		}
	}
	
}