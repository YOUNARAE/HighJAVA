package exercise;

public class Exercise22 {
	public static void main(String[] args) {
		/* 지구에서 가장 가까운 별은 어디일까? 정답은 태양이다. 
		 * 태양 다음으로 가까운 별은 프록시마 센타우리(Proxima Centauri) 별이라고 한다. 
		 * 프록시마 센타우리는 지구로 부터 40 * 10^12km 떨어져 있다고 한다. 
		 * 빛의 속도로 프록시마 센타우리까지 간다면 시간이 얼마나 걸리는지 직접 계산해보기로 하자. 
		 * 빛의 속도는 300,000km/s이다.
		 */
		double distance = 40e12; //거리가 주어졌고
		double lightSpeed = 300000; //속력이 주어졌다
		
		//걸리는 시간을 구하려면 거리를 속력으로 나눠야한다. 속력의 단위가 km/s이고, 우리는 광년을 구해야한다.
		//처리(data processing)
		double LightSpeenPerYear = lightSpeed * 60 * 60 * 24 * 365;
		double time = distance / LightSpeenPerYear;
		double time2 = 40e12 / 300000 * 60 * 60 * 24 * 365;
		
		System.out.println("빛의 속도로 프록시타 센타우리 별까지 가는데 걸리는 시간은 " + time + "광년이다.");
		System.out.println("빛의 속도로 프록시타 센타우리 별까지 가는데 걸리는 시간은 " + time2 + "광년이다.");
		
	}
}
