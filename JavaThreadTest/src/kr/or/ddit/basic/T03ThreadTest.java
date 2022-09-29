package kr.or.ddit.basic;

/**
 * 
 * 스레드의 수행시간 체크해 보기
 */
public class T03ThreadTest {
	public static void main(String[] args) {
		
		Thread th = new Thread(new MyRunner()); //쓰레드 생성자에 러너블 객체를 만들음

		// UTC(Universal Time Coordinated 협정 세계 표준시)를 사용하여 1970년 1월 1일 0시 0분 0초를 기준으로 경과한 시간을 밀리세컨드(1/1000초)단위로 나타낸다.
		long startTime = System.currentTimeMillis();
		th.start();
		
		try {
			// 현재 실행중인 스레드에서 작업중인 스레드(지금은 th스레드)가 종료될 때까지 기다린다.
			th.join(); //메인쓰레드와 th스레드가 조인되고 th스레드가 죽을때까지 같이 했다가 죽으면 다시 자기 할일을 하러간다
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과 시간 : "  + (endTime - startTime));
	}
}

// 1~1000000000 까지의 합게를 구하는 스레드 클래스
class MyRunner implements Runnable{

	@Override
	public void run() {
	
		long sum = 0;
		for(int i=1; i<1000000000; i++) {
			sum += 1;
		}
		System.out.println("합계 : " + sum);
		
	}
}