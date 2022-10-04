package kr.or.ddit.basic;

import com.sun.management.jmx.Trace;

/**
 * 은행의 입출금을 스레드로 처리하는 예제
 */

public class T16SyncAccountTest {
	public static void main(String[] args) {
		
		SyncAccount sAcc = new SyncAccount();
		sAcc.deposit(10000); //10000 원 먼저 입금해보자
		
		Thread th1 = new BankThread(sAcc);
		Thread th2 = new BankThread(sAcc);
		
		th1.start();
		th2.start();
		
	}
}

// 은행의 입출금을 관리하는 클래스(공유객체)
class SyncAccount{
	private int balance; //잔액이 저장될 변수

	synchronized public int getBalance() {
		return balance;
	}
	
	//입금 처리를 수행하는 메서드
	public  void deposit(int money) {
		balance += money;
	}
	
	// 출금 처리를 수행하는 메서드 (출금 성공 : true, 출금 실패 : false반환)
	// 동기화 영역에서 호출하는 메서드도 동기화 처리를 해 주어야 한다.
	synchronized public boolean withdraw(int money) {
	
		if(balance>=money) { //잔액이 많을 경우..
			for(int i=1; i<=1000000000; i++) {}
			balance -= money;
			System.out.println("메서드 안에서 balance = " + getBalance()); 
			//동기화된 클래스라고 해도 그 안에서의 메서드 들 내용들이 수행되는 것에 대해서는 또 동기화가 안된다.
			//그래서 getBalance 메서드에 싱크로를 붙여준다
			
			return true;
			
		} else {
			
			return false;
			
		}
	}
}

//은행 업무를 처리하는 스레드
class BankThread extends Thread {
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000);// 6000원 인출
		System.out.println("스레드 안에서 result = " + result + ", balance =" + sAcc.getBalance());
	}
}