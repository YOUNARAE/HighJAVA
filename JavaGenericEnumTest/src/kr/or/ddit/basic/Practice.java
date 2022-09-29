package kr.or.ddit.basic;

public class Practice {
	
	//가변형 인수 Args, 인수값이 변경될 수 있을 때 , 한 가지 자료형만 가능
	
	//배열을 이용한 메서드
	public int sumArr(int[] data) { //퍼블릭 인트 타입의 sumArr메서드는 int타입의 data를 배열로 가지고 있다
		int sum = 0; //int타입의 sum은 0으로 초기화;
		for(int i=0; i<data.length; i++) { //i가 0이고, i가 data의 갯수만큼동안 포문은 도는 동안에 1바퀴 돌때마다 i는 1씩 증가한다
			sum += data[i]; //sum은 i번째 data를 계속 해서 더해간다
		}
		return sum; //그리고 마지막에 sum을 리턴해준다
	}

	//가변형 인수를 이용한 메서드
	public int sumArg(int... data) {
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//가변형 인수와 일반적인 인수를 같이 사용할 경우
	public String sumArg2(String name, int...data) {
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return name + "씨 점수는 "+sum;
	}
	
	public static void main(String[] args) {
		Practice at = new Practice();
		
		int[] nums = {200,200,300};
		System.out.println(at.sumArr(nums));
		System.out.println(at.sumArr(new int[] {1,2,3,4,5}));
		
		System.out.println(at.sumArg(nums));
		System.out.println(at.sumArg(new int[] {1,2,3,4,5}));
		
		System.out.println(at.sumArg2("홍길동", 100,200,300));
	}
	
}
