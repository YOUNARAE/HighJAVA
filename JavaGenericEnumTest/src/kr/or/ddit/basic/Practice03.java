package kr.or.ddit.basic;


class Putil{
	public static <K, V> boolean compare(Pairs<K, V> p1, Pairs<K, V> p2) {
		
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		
		return keyCompare && valueCompare;
		
	}
}

class Pairs<K, V> {
	private K key;
	private V value;
	
	public Pairs(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	//키와 값을 모두 출력
	public <K, V>void displayAll(K key, V val){
		System.out.println(key + ", " + val);
	}
}


public class Practice03 {
	public static void main(String[] args) {
		Pairs<Integer, String> p1 = new Pairs<Integer, String>(1, "홍길동");
		Pairs<Integer, String> p2 = new Pairs<Integer, String>(1, "홍길동");
		
		//boolean result1 = Util.<In
	}

}


