package kr.or.ddit.basic.homework;

public class Planet{
	/*
	 * 문제) 행성의 면적을 구하여야 한다.
	 *      태양계 행성을 나타내는 enum Planet을 이용하여 구하시오. (단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 
		예) 행성의 반지름(KM):
						수성(2439), 
						금성(6052), 
						지구(6371), 
						성(3390), 
						목성(69911), 
						토성(58232), 
						천왕성(25362), 
						해왕성(24622)
	 */
	public enum EnumPlanet {
		수성(2439),금성(6052),지구(6371),성(3390),목성(69911),토성(58232),천왕성(25362),해왕성(24622);

		private double radius;

		EnumPlanet(double radius) {
			this.radius = radius;
		}

		public double getRadius() {
			return radius;
		}
	}
	
	public static void main(String[] args) {
		for (EnumPlanet enp : EnumPlanet.values()) {
			System.out.println(enp.name() + " : " + (4 * Math.PI * enp.getRadius()*enp.getRadius())+"㎢");
		}
		
	}
}
