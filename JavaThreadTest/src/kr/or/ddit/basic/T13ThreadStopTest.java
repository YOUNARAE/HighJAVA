package kr.or.ddit.basic;

public class T13ThreadStopTest {
	public static void main(String[] args) {
		/*
		ThreadStopEx1 th1 = new ThreadStopEx1();
		th1.start();
		
		try {
			Thread.sleep(1000);
			
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		//th1.stop(); //메서드가 존재하긴 하지만 새로 코딩할 때는 사용하지 않기를 권장하는 표시
		th1.setStop(true);
		*/
		
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		
		try {
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		th2.interrupt(); //메인스레드가 th2한테 인터룹트를 걸은 상황, 슬립이란 메서드를 실행해서 인터룹트를 걸릴 수 있는 시간을 준다
	}
}

class ThreadStopEx1 extends Thread{

	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop; //기본값은 false이다
	}
	
	@Override
	public void run() {
		
		while(!stop) { //true
			System.out.println("스레드 처리중 ... ");
		}
		
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료");
		
	}
}

/**
 * interrupt() 메서드를 이용하여 스레드를 멈추게 하는 방법 
 */
class ThreadStopEx2 extends Thread {
	@Override
	public void run() {
		/* //방법 1 => sleep()메서드나 join()메서드 등을 사용했을 때
		//        interrupt()메서드를 호출하면 InterruptedException이 발생한다.
		//
		try {
			while(true) {
				System.out.println("스레드 처리 중...");		
				Thread.sleep(1); //슬립을 하고 있어야 외부에서 Interrupted를 하는 게 의미가 있기때문에 이 메서드가 있어야 예외가 발생한다
			} 
		}catch(InterruptedException ex) {
				
		}*/
		
		// 방법 2 => 인터럽트가 걸렸는지 검사하는 방법 
		while (true) {
			System.out.println("스레드 처리 중 ...");
			
			/* // 검사방법1 => 스레드의 인스턴스객체용 메서드를 이용하는 방법
			if(this.isInterrupted()) {
				System.out.println("인스턴스용 isInterrupted()");
				break;
			}*/
			
			// 검사방법 2 => 스레드의 정적 메서드를 이용하는 방법
			if(Thread.interrupted()) {
				System.out.println("정적 메서드 interrupted()");
				break;
			}
		}
		
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
}


