package exercise;

import java.util.Scanner;

public class Exercise23 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("원기둥 밑변의 반지름을 입력하시오. (단위: cm) : ");
		double radius = scanner.nextDouble();
		System.out.print("원기둥 높이를 입력하시오. (단위: cm) : ");
		double height = scanner.nextDouble();
		
		double cylinderArea = radius * radius * Math.PI;
		double cylinderVolume = cylinderArea * height;
		
		System.out.println("원기둥 밑변의 넓이는 " + cylinderArea + "㎠ 이고, 원기둥의 부피는 " + cylinderVolume + "㎤ 이다.");
		
	}
}
