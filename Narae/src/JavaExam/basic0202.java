package JavaExam;

public class basic0202 {
	public static void main(String[] args) {
		
		int n = 4;
		n = n + basic0102().multiply(8, 2);
		//Foo f = new Foo(10);
		//n = n + f.getA();
		//n = n + new Bar().getA();
		out(n);
	}
	private static basic0202 basic0102() {
		// TODO Auto-generated method stub
		return null;
	}
	private static void out(int x) {
		System.out.println(x);
	}
	private static int add(int x, int y) {
		int z = x + y;
		return z;
	}
	private int multiply(int x, int y) {
		return x * y;
	}
}
/*
 * public class Foo { int a; public Foo(int a) { this.a = a; }//생성자다 public int
 * getA() { return a; } }
 * 
 * public class Bar { int a; public int getA() { return a; } }
 */