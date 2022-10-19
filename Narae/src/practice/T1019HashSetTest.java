package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class T1019HashSetTest {
	public static void main(String[] args) {
		
		Set hs1 = new HashSet(); 
		
		//추가
		hs1.add("DD");
		hs1.add("AA");
		hs1.add("CC");
		hs1.add(2);
		
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		boolean isAdded = hs1.add("FF");
		System.out.println("중복되지 않을 때 : "+isAdded);
		System.out.println("Set 데이터 : " + hs1);
		
		isAdded = hs1.add("AA");
		System.out.println("중복될 때 : "+isAdded);
		System.out.println("Set 데이터 : "+ hs1); //중복되면 안 들어간다
		
		//수정
		hs1.remove("AA");
		System.out.println("삭제 후 데이터 : "+hs1);
		
		hs1.add("EE");
		System.out.println("EE 추가 후 데이터 : "+hs1);
		
		System.out.println("Set의 데이터 갯수 : "+hs1.size());
		
		Iterator it = hs1.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		//1~100 사이의 중복되지 않는 정수 5개 만들기
		Set<Integer> intRnd = new HashSet<Integer>();
		
		//while문으로
		while(intRnd.size()<5) {
			int num = (int) (Math.random()*100+1);
			intRnd.add(num);
		}
		System.out.println("만들어진 난수들 : "+intRnd);
		List<Integer> intRndList = new ArrayList<Integer>();
		intRndList.addAll(intRnd);
		Collections.sort(intRndList);
		System.out.println("숫자 오름차순 정렬 : "+ intRndList);
		/*
		 * for (Integer irList : intRndList) { System.out.println(irList + " "); }
		 */
		
	}
}
