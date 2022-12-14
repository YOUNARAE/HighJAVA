package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class T07EqualsHashcodeTest {
	/*
	 * 해시함수(hash function)는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑해주는 함수이다.
	 * 해시 함수에 의해 얻어지는 값은 해시값, 해시코드, 해시 체크썸 또는 간단하게 해시라고 한다.
	 * 
	 * HashSet, HashMap, Hashtable과 같은 객체들을 사용할 경우 객체가 서로 같은지를 비교하기 위해 
	 * eqauls()와 hashcode()를 호출한다. 그래서 객체가 서로 같은지 여부를 결정하려면 두 메서드를 재정의 해야한다.
	 * 
	 * HasSet, HashMap, Hashtable에서 객체가 같은지 여부는 데이터를 추가할 때 검사한다.
	 * 
	 * 
	 * - equals() => 두 객체의 내용(값)이 같은지 비교하는 메서드.
	 * - hashCode() => 객체에 대한 해시 코드값을 반환하는 메서드.
	 * 
	 * - equals()와 hashCode()에 관한 규칙
	 * 1. 두 객체가 같으면 반드시 같은 hashcode를 가져야 한다.
	 * 2. 두 객체가 같으면 equals()메서드를 호출했을 때 true값은 반환해야 한다.
	 * 즉, 객체 a, b가 같다면 a.equals(b)와 b.equals(a) 둘 다 true이여야 한다.
	 * 3. 두 객체의 hashcode가 같다고 해서 두 객체가 반드시 같은 객체는 아니다. 
	 * 하지만 두 객체가 같으면 반드시 hashcode가 같아야 한다.
	 * 4.equals()를 override하면 반드시 hashCode()도 override 해야한다.
	 * 5.hashCode()는 기본적으로 Heap 메모리에 있는 각 객체에 대한 메모리 주소 매핑 정보를 기반으로 한 정수값을 반환한다.
	 * 그러므로 클래스에서 hashCode()메서드를 override하지 않으면 절대로 두 객체가 같은 해시값을 반환하지 않는다.
	 * 
	 * hashCode()에서 사용하는 '해싱알고리즘'에서 서로 다른 객체에 대하여 같은 hashCode값을 만들어 낼 수 있다. 그래서 객체가 같지 않더라도 hashcode값이 같을 수 있다.
	 */
	public static void main(String[] args) {
		
		System.out.println("Aa".hashCode());
		System.out.println("BB".hashCode()); //이런 경우가 있을 때가 있어서 해쉬코드를 쓰고 이퀄스까지 써야한다.
		
		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		Person p3 = new Person(1, "이순신");
		
		System.out.println("p1.equals(p2) : " + p1.equals(p2));
		System.out.println("p1 == p2 : "+ (p1 == p2));
		
		Set<Person> pSet = new HashSet<Person>();
		
		/*성공여부 볼 때는 잠깐 가려놓음
		   pSet.add(p1);
		   pSet.add(p2);
		 */
		
		System.out.println("pSet.add(p1) 성공여부: " + pSet.add(p1));
		System.out.println("pSet.add(p2) 성공여부: " + pSet.add(p2));
		
		System.out.println("p1, p2 등록 후 데이터 : ");
		Iterator<Person> it = pSet.iterator();
		while(it.hasNext()) {
			Person p = it.next();
			System.out.println(p.getId() + " : " + p.getName());
		}
		
		System.out.println("add(p3) 성공 여부 : " + pSet.add(p3)); //remove가 안될 수도 있으니까 값을 확인하라고 값을 리턴해준다
		
		System.out.println("add(p3) 후 데이터 : ");
		//Iterator 대신에 foreach문으로도 쓸 수 있다. 이터러블 인터페이스에 있는 건 다 foreach문으로 접근해서 쓸 수 있다
		for(Person p : pSet) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		
		System.out.println("remove(p2) 성공 여부 : " + pSet.remove(p2));
		System.out.println("remove(p2) 후 데이터 : ");
		for (Person p : pSet) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		
	}
}


class Person{
	private int id;
	private String name;

	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
	
	/* 이퀄스,해쉬코드를 우리가 커스텀 오버라이딩한 경우*/
//	@Override
//	public boolean equals(Object obj) {
//		//우리가 원하는 방식으로 오버라이딩 시켜줘야 우리가 원하는 비교값이 나온다.
//		Person p = (Person) obj;
//		
//		return (this.getId() == p.getId()) 
//				&& this.getName().equals(p.getName()); //현재 나 자신 iD과 날라오는 Id
//	}
//	
//	@Override
//	public int hashCode() {
//		//해쉬코드를 오버라이딩 해야되는 이유는 우리가 원하는 방식으로 사용해야할 때가 있기때문이다. 
//		//이퀄스를 같이 사용할 때는 같이 쌍으로 오버라이딩을 해준다고 생각하면 된다
//		return (name + id).hashCode(); 
//		//같은 홍길동 1이 되었기 때문에 같은 해쉬코드 주소가 되어버렸다
//		//같은 스트링이면 같게 하고 다른 스트링 넣으면 다르게 나오라고 미리 만들어놓았다.해쉬코드 만든 개발자들이
//	}
	
	
	/*두개를 같이 쓰는 이유
	해쉬코드를 쓰는데 이퀄스까지 쓰면 낭비다
	두 다른 인트값의 정수가 해쉬코드를 통해 나왔을 때 두 객체는 그냥 다른 것이다.
	but 같으면 그떄부터 충돌방지를 위해 이퀄스까지 호출해서 비교를 해야하는 것이다.
	+해쉬코드가 충돌이 난다는 의미 : 위에 대문자 Aa와 BB 
	*/
	
	/*자동완성기능으로 만든 해쉬코드와 이퀄스*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}