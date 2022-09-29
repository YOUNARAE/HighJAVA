package JavaExam;

public class basic0208 {
	public String country = "unknown";//공개용 스트링 타입의 컨츄리 매개변수의 값은 unknown이다
	public String getCountry() { // 스트링 타입의 getCountry 메서드는 
		return country; //country를 리턴받는다
	}
	public static void main(String[] args) {
		Won won = new Won(); // 현재 이 클래스를 상속받는 Won클래스 타입의 won으로 새로운 Won()을 생성했다
		Euro euro = new Euro(); //현재 이 클래스를 상속받는 Euro클래스 타입의 euro로 새로운 Euro()를 생성했다
		basic0208 money = new Won(); //이 클래스 타입의 money는 Won() 메소드를 새로 생성한 것이다
		System.out.println(won.getCountry());
		System.out.println(euro.getCountry());
		System.out.println(money.getCountry());
	}
}

class Won extends basic0208 {
	private String country = "Korea";  //Won 클래스에서는 country 값에 Korea가 들어간다.
	public String getCountry() {
		return country;
	}
}

class Euro extends basic0208 {
	private String country = "Spain"; 
	public String getCountry(int x) {
		return country;
	}
}
