package kr.or.ddit.basic;
/**
 * 동기화 처리한 리스트 예제
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T18SyncCollectionTest {
/*
  Vector, Hashtable등의 예전부터 존재하던 Collection들은 내부에 동기화 처리가 되어 있다.
	그런데, 최근에 새로 구성된  Collection들은 동기화 처리가 되어있지 않다.
	그래서 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리를 한 후 에 사용해야 한다.
*/
	// 동기화를 하지 않을 경우
	private static List<Integer> list1 = new ArrayList<Integer>();
	
	// 동기화 처리를 할 경우
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());
		
	public static void main(String[] args) {
		// 익명클래스로 스레드 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=10000; i++) {
					// list1.add(i); (동기화처리하지 않은 것)
					list2.add(i);
				}
			}
		};
		
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r),
				new Thread(r), new Thread(r), new Thread(r)
		};
		
		for (Thread th : ths) {
			th.start();
		}
		
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("list의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size());
	}
}
