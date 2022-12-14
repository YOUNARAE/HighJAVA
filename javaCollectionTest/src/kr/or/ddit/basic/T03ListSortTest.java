package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T03ListSortTest {
	
	public static void main(String[] args) {
	
		List<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전 : " + list);
		
		/* 정렬은 Collections.sort()메서드를 이용하여 정렬한다
		 * 정렬은 기본적으로 '오름차순 정렬'을 수행한다.
		 * 정렬박식을 변경하려면 정렬방식을 결정하는 객체를 만들어서 Collections.sort()메서드의 인수값으로 넘겨주면 된다.
		 */
		
		Collections.sort(list); //오름차순으로 정렬하기
		//Collection은 Interface이다.
		//Collections는 클래스이다.
		//static 메소드는 객체(인스턴스)를 만들지 않고 바로 쓸 수 있다. Util같은 것들이 이러하다. 보통 인스턴스 메소드를 실행하려면 인스턴스를 만들어야한다.
		System.out.println("정렬 후 : " + list);
		
		Collections.shuffle(list); //데이터를 섞어준다.
		System.out.println("섞은 후 : " + list);
		
		//정렬방식을 결정하는 객체를 이용하여 정렬하기
		Collections.sort(list, new Desc()); //객체를 만들어서 바로 사용한다
		System.out.println("외부 정렬자로 정렬 후 : " + list);
		
	}
}

/*
 *  정렬 방식을 결정하는 class는 Comparator라는 인터페이스를 구현해야한다.
 *  이  Comparator인터페이스의 compare()라는 메서드를 재정의하여 구현하면 된다.
 */
class Desc implements Comparator<String> {
/*
 * compare() 메서드의 반환값을 결정하는 방법
 * 
 * - 오름차순 정렬일 경우...
 *   => 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 한다.
 *   	
 */
	@Override //임플리먼트했기때문에 무조건 한번 오버라이딩 해준다.
	public int compare(String str1, String str2) {
		// TODO Auto-generated method stub
		return str1.compareTo(str2) * -1; //스트링타입은 오름차순으로 기본적으로 장착한 상태라서 음수를 곱해주어서 음수로 만들면 내림차순이 가능하다
	}
	
}
