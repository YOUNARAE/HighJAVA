package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T1019HashMapTest {
	public static void main(String[] args) {
	
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("name","지더래곤");
		map.put("age","나이는 비밀");
		map.put("addr","서울특별시 한남동 부자동네");
		
		System.out.println("map =>"+map);
		
		map.put("addr","대전");
		System.out.println("map =>"+map);
		
		map.remove("name");
		System.out.println("map =>"+map);
		
		System.out.println("addr => " +map.get("addr"));
		System.out.println();
		
		
		// key값들을 읽어와 자료를 출력하는 방법
		Set<String> keySet = map.keySet();
		
		
		//1.keySet() 메서드 이용, Map의 key값을 읽어와서 출력
		System.out.println("Iterator를 이용하는 방법");
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("----------------------------------------");
		
		//2.향상된 for문
		System.out.println("향상된 for문을 이용하는 방법");
		for (String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("----------------------------------------");
		
		//3.
		System.out.println("values() 메서드를 이용하는 방법");
		for (String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("----------------------------------------");
		
		//4
		Set<Map.Entry<String, String>> entrySet = map.entrySet();
		
		Iterator<Map.Entry<String, String>> entryIt = entrySet.iterator();
		
		while(entryIt.hasNext()) {
			Map.Entry<String, String> entry = entryIt.next();
			
			System.out.println("key :" + entry.getKey());
			System.out.println("value :" + entry.getValue());
			System.out.println();
		}
		
		System.out.println("----------------------------------------");
		System.out.println("향상된 for문으로 이용한 방법");
		
		for (Map.Entry<String, String> entry : entrySet) {
			System.out.println(entry);
		}
		
		
	}
}
