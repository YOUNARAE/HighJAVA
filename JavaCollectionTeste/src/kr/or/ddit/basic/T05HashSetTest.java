package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class T05HashSetTest {
	public static void main(String[] args) {
		
		Set hs1 = new HashSet();
		
		// Set에 데이터를 추가할 때에도 add()메서드를 사용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		// Set은 데이터의 순서(인덱스개념)가 없고, 중복을  허용하지 않는다.
		// 그래서 이미 있는 데이터를 add하면 false를 반환하고, 데이터는 추가되지 않는다.
		boolean isAdded = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdded);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		isAdded = hs1.add("CC");
		System.out.println("중복될 때 : " + isAdded);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		// Set의 데이터를 수정하려면 수정하는 메서드가 따로 없기 때문에 해당 자료를 삭제한 후
		// 새로운 데이터를 추가해 주어야 한다.
		
		// 삭제하는 메서드
		// 1) clear() => Set 데이터 모두 삭제
		// 2) remove(삭제할자료) => 해당 자료 삭제
		// 예) 'FF'를 'EE'로 수정하기
		hs1.remove("FF");
		System.out.println("삭제 후 데이터 : " + hs1);
		System.out.println();
		
		hs1.add("EE"); 
		System.out.println("EE 추가 후 데이터 : " + hs1);
		System.out.println();
		
		System.out.println("Set의 데이터 개수 : " + hs1.size());
		System.out.println();
		
		// Set은 데이터의 인덱스가 없기 때문에 List처럼 인덱스로 데이터를 하나씩 불러올 수 없다.
		// 그래서 데이터를 하나씩 얻어내기 위해 Iterator를 이용해야 한다.
		
		// Set의 데이터를 접근하기 위해 Iterator 객체 가져오기
		Iterator it = hs1.iterator();
		
		// 데이터 개수만큼 반복하기
		// hasNext() => 포인터를 다음 위치에 데이터가 있으면 true, 없으면 false를 반환함.
		while(it.hasNext()) { // 다음에 데이터 있는지 검사
			// next() => 포인터를 다음 자료 위치로 이동하고 이동한 위치의 자료를 반환함.
			System.out.println(it.next());
		}
		
		// 1~100사이의 중복되지 않는 정수 5개 만들기
		Set<Integer> intRnd = new HashSet<Integer>();
		
		while(intRnd.size() < 5) {
			int num = (int)(Math.random() * 100 + 1);
			intRnd.add(num);
		}
		
		System.out.println("만들어진 난수들 : " + intRnd);
		
		// Collection유형의 객체들은 서로 다른 자료 구조로 쉽게 변경하여 사용할 수 있다.
		// 다른 종류의 객체를 생성할 때 생성자에 변경할 데이터를 넣어주면 된다.
		List<Integer> intRndList = new ArrayList<Integer>(intRnd);
		System.out.println("List의 자료 출력...");
		
		for(Integer num : intRndList) {
			System.out.print(num + " ");
		}
	}
}
