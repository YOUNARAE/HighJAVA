package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class T11DisplayCharacterTest {
	
	/*
	 * 3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기. (쓰레드 3개를 만들어서
	 * 각각 출력)
	 */
	static int currRank = 1; //현재의 순위 정보
	
	public static void main(String[] args) {
		
		List<DisplayCharacter> disCharList = new ArrayList<DisplayCharacter>();
		
		disCharList.add(new DisplayCharacter("홍길동"));
		disCharList.add(new DisplayCharacter("이순신"));
		disCharList.add(new DisplayCharacter("강가찬"));
		
		for (Thread th : disCharList) {
			th.start();				
		}
		
		for (Thread th : disCharList) {
			try {
				th.join();				
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		Collections.sort(disCharList);
		
		System.out.println("경기 끝 ...");
		System.out.println("--------------------------");
		System.out.println("경기 결과");
		System.out.println();
		System.out.println("==========================");
		System.out.println("순위\t:\t이름");
		System.out.println("--------------------------");
		for (DisplayCharacter dc : disCharList) {
			System.out.println(dc.getRank() + "\t:\t" + dc.getName() );
		}
		
	}
}

// 알파벳 대문자 출력하는 스레드
class DisplayCharacter extends Thread implements Comparable<DisplayCharacter>{

	// 이름을 관리해줘보자(멤버변수)
	private String name; 
	private int rank;
	
	public DisplayCharacter(String name) {
		super(name);
		this.name = name;
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
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + "의 출력 문자 : " + ch);

			try {
				// sleep() 시간은 200~ 500 사이의 난수로 한다.
				Thread.sleep((int) (Math.random() * 301 + 200));
				// 1,0,3 에서 200이 더해져야 500 사이로 나오기 때문에

			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} 
		}
		System.out.println(name + " 출력 끝 ...");
	
		setRank(T11DisplayCharacterTest.currRank++); //전위와 후위의 차이 ++전행=>2시작 , 후행++=>1부터 시작
	}

	@Override //int값을 오름차순으로 정렬한 것이다
	public int compareTo(DisplayCharacter o) {
		return new Integer(this.getRank()).compareTo(o.getRank());		
	}
}
