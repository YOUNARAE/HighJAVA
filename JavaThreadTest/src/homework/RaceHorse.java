package homework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RaceHorse {

	static int currRank = 1; //현재의 순위 정보
	
	public static void main(String[] args) {
		 
		List<Horse> horseList = new ArrayList<Horse>();
		
		horseList.add(new Horse("1번마"));
		horseList.add(new Horse("2번마"));
		horseList.add(new Horse("3번마"));
		horseList.add(new Horse("4번마"));
		horseList.add(new Horse("5번마"));
		horseList.add(new Horse("6번마"));
		horseList.add(new Horse("7번마"));
		horseList.add(new Horse("8번마"));
		horseList.add(new Horse("9번마"));
		horseList.add(new Horse("10번마"));
		
		
		for (Thread th : horseList) {
			th.start();				
		}
		
		for (Thread th : horseList) {
			try {
				th.join();				
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		Collections.sort(horseList);
		
		System.out.println("경기 끝 ...");
		System.out.println("==========================");
		System.out.println();
		System.out.println("경기 결과");
		for (Horse h : horseList) {
			System.out.println(h.getRank() + "등 \t:\t" + h.getHorseName());
		}
		
	}
}

class Horse extends Thread implements Comparable<Horse>{

	// 이름을 관리해줘보자(멤버변수)
	private String horsename; 
	private int rank;
	
	public Horse(String horsename) {
		this.horsename = horsename;
	}
	
	public String getHorseName() {
		return horsename;
	}

	public void setHorseName(String horsename) {
		this.horsename = horsename;
	}

	public int getRank() { //스레드의 순위 정보
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	// 스레드
	@Override
	public void run() {
		for (int i=0; i<50; i++) {
			System.out.println("\n"+ horsename + " : ");
			for(int j=0; j<i; j++) {
				System.out.print("-");
			}
			System.out.print(">");

			System.out.println();
			
			try {
				Thread.sleep((int) (Math.random() * 501));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} 
		}
		System.out.println(horsename + "끝 ...");
		setRank(RaceHorse.currRank++); //전위와 후위의 차이 ++전행=>2시작 , 후행++=>1부터 시작
	}

	@Override //int값을 오름차순으로 정렬한 것이다
	public int compareTo(Horse h) {
		return new Integer(this.getRank()).compareTo(h.getRank());		
	}
}
