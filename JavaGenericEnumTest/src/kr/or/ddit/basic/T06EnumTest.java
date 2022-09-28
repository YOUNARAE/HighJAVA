package kr.or.ddit.basic;


public class T06EnumTest {
/*
 	열거형 상수 선언하는 방법
 	
 	enum 열거형이름{ 상수값1, 상수값2, ..., 상수값n};
 */
	// City 열거형 객체 선언 (기본값을 이용하는 열거형)
	public enum City{서울, 부산, 대구, 광주, 대전};
	
	public enum Hometown {대구, 서울, 대전, 부산, 울산, 진주};
	
	// 데이터값을 임의로 지정한 열거형 객체 선언
	// 데이터값을 정해줄 경우에는 생성자를 만들어서 괄호 속의 값이 변수에 저장되도록 해야 한다.
	public enum Season {
		봄("3월부터 5월까지"), 여름("6월부터 8월까지"), 가을("9월부터 11월까지"), 겨울("12월부터 2월까지");
		
		// 괄호 속의 값이 저장될 변수 선언
		private String str;
		//외부에서 접근 못r하고 억제시킬 수 있게 private
		
		//생성자 만들기(열거형의 생성자는 제어자가 묵시적으로 'private'이다.)
		Season(String data) { //생성자를 선언하고 변수를 만들어준다
			this.str = data;
		}
		
		// 값을 반환하는 메서드(Setter)
		public String getStr() {
			return this.str;
		}
	}
	
	public static void main(String[] args) {
	/*
	 	열거형에서 사용하는 메서드
	 	1. name() => 열거형 상수의 이름을 문자열로 반환한다.
	 	2. ordinal() => 열거형 상수가 정의된 순서값을 반환한다.(기본적으로 0부터 시작)
	 	3. valueOf("열거형상수이름") => 지정된 열거형에서 '열거형상수이름'과 일치하는 열거형상수를
 	   							       반환한다. 
	 */
		 City myCity1; //열거형 객체변수 선언
		 City myCity2;
		 
		 myCity1 = City.서울;
		 myCity2 = City.valueOf("서울");
		 
		 System.out.println("myCity1 : " + myCity1.name());
		 System.out.println("myCity1의 ordinal : " + myCity1.ordinal());
		 System.out.println();
		 System.out.println("myCity2 : " + myCity2.name());
		 System.out.println("myCity2의 ordinal : " + myCity2.ordinal());
		 System.out.println("========================================");
		 
		 Season ss = Season.valueOf("여름");
		 System.out.println("name => " + ss.name());
		 System.out.println("ordinal => " + ss.ordinal());
		 System.out.println("get메서드 호출 => " + ss.getStr());
		 System.out.println("----------------------------------------");
		 
		 // 열거형이름.values() => 데이터를 배열로 리턴함.
		 Season[] ssArr = Season.values();
		 for(Season s : ssArr) {
			 System.out.println(s.name() + " : " + s.getStr());
		 }
		 System.out.println();
		 
		 for(City city : City.values()) {
			 System.out.println(city + " : " + city.ordinal());
		 }
		 
		 City city = City.대구;
		 
		 Enum<City> city2 = City.대구; //이늄 타입의 시티의 city2 변수에 들어간다.
		 
		 System.out.println(city == City.대전);
		 System.out.println(city == City.대구);
		 // System.out.println(city == Hometown.대구); 타입 자체가 달라서 비교 자체가 안되서 컴파일이 오류난다
		 
		 System.out.println("대구 => " + city.compareTo(City.대구)); //대구와 대구 = 0
		 System.out.println("서울 => " + city.compareTo(City.서울)); //대구와 서울 = 양수, 대구가 더 크다는 뜻
		 System.out.println("대전 => " + city.compareTo(City.대전)); //대구와 대전 = 음수 , 대전이 더 크다는  뜻, ordinary
	}
}