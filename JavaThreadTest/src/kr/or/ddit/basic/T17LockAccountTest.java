package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 은행의 입출금을 스레드로 처리하는 예제
 * (Lock객체를 이용한 동기화 처리) 
 */
public class T17LockAccountTest {
/*
     락 기능을 제공하는 클래스
  ReentrantLock : Read 및 Write 구분 없이 사용하기 위한 락 클래스.
                  synchronized를 이용한 동기화 처리보다 부가적인 기능이 제공된다.
                  ex) Fairness 설정등. => 가장 오래 기다린 스레드가 가장 먼저 획득하게 함.
                  
  ReentrantReadWriteLock : Read 및 Write 락을 구분하여 사용 가능함.
                                                             여러 스레드가 동시에 read작업은 가능하지만, write작업은 단지 하나의 스레드만 가능함.
                           => write보다 read 위주의 작업이 많이 발생하는 경우에 사용하면 유리하다.                           
 */
	public static void main(String[] args) {
		
		ReentrantLock lock = new ReentrantLock(true);
		
		LockAccount lAcc = new LockAccount(lock);
		
		lAcc.deposit(10000);
		
		Thread th1 = new BankThread2(lAcc);
		Thread th2 = new BankThread2(lAcc);
		
		th1.start();
		th2.start();
		
	}
}

// 입출금을 담당하는 클래스(공유객체)
class LockAccount {

	private int balance; //잔액이 저장될 변수
	
	// lock객체 생성 => 되도록이면 private final로 만든다
	private final Lock lock;
	public LockAccount(Lock lock) {
		this.lock = lock;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void deposit(int money) {
		// Lock객체의 lock()메서드가 동기화 시작이고, unlock()
		// 메서드가 동기화의 끝을 나타낸다.
		// lock()메서드로 동기화를 설정한 곳에서는 반드시 unlock()
		// 메서드로 해체해주어햐 한다.
		lock.lock(); //락 설정(락을 획득하기 전까지 Block상태)
		balance += money; // 동기화 처리 부분
		lock.unlock(); // 락 해제
	}
	
	
	// 출금하는 메서드(출금 성공: true, 출금 실패 : false)
	public boolean withdraw(int money) {
		boolean chk = false;
		
		// try~catch 블럭을 사용할 경우에는
		// unlock() 메서드 호출은 finally 블럭에서 하도록 한다.
		try {
			lock.lock(); //락 설정
			
			if(balance >= money) {
				for(int i=0; i<=1000000000; i++) {}
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				chk = true;
			} else {
				chk = false;
			}
			
		} catch(Exception ex){
			
		}finally {
			lock.unlock(); // 락 해제
		}
		
		return chk;
	}
}


//은행 업무를 처리하는 스레드
class BankThread2 extends Thread {
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);// 6000원 인출
		System.out.println("스레드 안에서 result = " + result + ", balance =" + lAcc.getBalance());
	}
}