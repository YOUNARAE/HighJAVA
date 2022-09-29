package JavaExam;

public class basic0207 {
	public static void main(String[] args) {
		new basic0207().compare(); //비교한다
	}
	
	private void compare() {
		User a = new User("Kim", "Chopper");
		User b = new User("Kim", "Chopper"); 
		User c = a;
		System.out.println(a == b); //a랑 b는 생성된 게 달라서 주소값이 달라서  false이다
		System.out.println(a == c); //c는 a로 생성된 거라서 true이다
		System.out.println(a.equals(b)); //a와 b는 같은 내용이라서 equals가 true이다
	}
	
	public class User { //클래스 생성
		
		private String firstName; //스트링 타입의 firstName 필드 선언 
		private String lastName; //스트링 타입의 lastName 필드 선언
		public User(String firstName, String lastName) { //스트링 타입의 firstName과 lastName의 매개변수를 갖는 생성자를 생성하고
			this.firstName = new String(firstName); 
			this.lastName = new String(lastName);
		}
		
		public boolean equals(User other) {
			return match(firstName, other.firstName) || match(lastName, other.lastName);
		}
		
		private boolean match(String part1, String part2) {
			return part1 == part2 || part1.equals(part2);
		}
	}
}
