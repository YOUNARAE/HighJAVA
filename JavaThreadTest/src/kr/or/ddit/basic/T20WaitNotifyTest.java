package kr.or.ddit.basic;

public class T20WaitNotifyTest {
	public static void main(String[] args) {
		
		DataBox dataBox = new DataBox();
		
		ProducerThread pTh = new ProducerThread(dataBox);
		ConsumerThread cTh = new ConsumerThread(dataBox);
		
		pTh.start();
		cTh.start();
		
		
	}
}

// 데이터를 공통으로 사용하는 클래스(공유객체)
class DataBox {
	private String data; //공유객체 안에 있는 데이터(멤버변수)
	
	// data가 null이 아닐 때 data값을 반환하는 메서드
	public synchronized String getData() {
		if(this.data == null) { //데이터가 없다고 생각하면 됨
			try {
				System.out.println(Thread.currentThread().getName() + " wait() 호출 ");
				wait();
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		// 꺼내온 데이터로 초기화 시킨다
		String returnData = this.data;
		System.out.println("읽어온 데이터 : " + returnData);
		this.data = null;
		System.out.println(Thread.currentThread().getName() + " notify() 호출");
		notify();
		
		return returnData;
	}
		
		// data가 null일 경우에만 자료를 세팅하는 메서드
		public synchronized void setData(String data){
			if(this.data != null) {
				try {
					System.out.println(Thread.currentThread().getName()+" wait() 호출");
					wait();
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		
		this.data = data;
		System.out.println("세팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName() + " notify() 호출");
		
		notify();
	}
}


// 데이터를 세팅만 하는 스레드
class ProducerThread extends Thread {
	private DataBox dataBox;
	
	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=5; i++) {
			String data = "Data-" + i;
			System.out.println("dataBox.setData(" + data + ") 호출");
			dataBox.setData(data);
		}
	}
}

//데이터만 읽어만 오는 스레드
class ConsumerThread extends Thread {
	private DataBox dataBox;
	
	
	
	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=5; i++) { //5번 호출해서 값을 소비한다
			String data = dataBox.getData(); 
			System.out.println("dataBox.getData() : " + data);
		}
	}
}
