package kr.or.ddit.basic;

public class Practice02 {
	public static void main(String[] args) {
		DoNotGeneric dng1 = new DoNotGeneric();
		dng1.setVal("안녕하세요");
		
		DoNotGeneric dng2 = new DoNotGeneric();
		dng2.setVal(1000);
		
		String rtnVal1 = (String) dng1.getVal();
		System.out.println("문자열 반환값 rtnVal1 => " + rtnVal1);
		
		Integer irtnVal2 = (int) dng2.getVal();
		System.out.println("정수 반환값 irtnVal2 => " + irtnVal2);
		
		MyGenericType<String> mg1 = new MyGenericType<String>();
		MyGenericType<Integer> mg2 = new MyGenericType<Integer>();
		
		mg1.setVal("대한민국");
		mg2.setVal(200);
		
		rtnVal1 = mg1.getVal();
		irtnVal2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : " + rtnVal1);
		System.out.println("제너릭 정수형 반환값 : " + irtnVal2);
	}
}
class DoNotGeneric {
	private Object val;
	
	public Object getVal() {
		return val;
	}
	public void setVal(Object val) {
		this.val = val;
	}
}

class MyGenericType<T> {
	private T val; 
	
	public T getVal() {
		return val;
	}
	public void setVal(T val) {
		this.val = val;
	}
}
