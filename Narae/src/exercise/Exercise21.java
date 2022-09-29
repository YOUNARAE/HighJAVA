package exercise;

import java.util.Scanner;

public class Exercise21 {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("가로의 길이는 ?(단위 : m) : ");
		double Width = scanner.nextDouble();
		System.out.print("세로의 길이는 ?(단위 : m) : ");
		double Height = scanner.nextDouble();
		
		double Round = Width + Width + Height + Height;
		double Area = Width * Height;
		
		System.out.println("직사각형의 넓이 : " + Area);		
		System.out.println("직사각형의 둘레 : " + Round);
	}
}
