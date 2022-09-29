package exercise;

import java.util.Scanner;

public class Exercise24 {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("500원짜리 동전의 갯수 : ");
		int coin500Count = scanner.nextInt();
		int coin500 = 500;
		System.out.print("100원짜리 동전의 갯수 : ");
		int coin100Count = scanner.nextInt();
		int coin100 = 100;
		System.out.print("50원짜리 동전의 갯수 : ");
		int coin50Count = scanner.nextInt();
		int coin50 = 50;
		System.out.print("10원짜리 동전의 갯수 : ");
		int coin10Count = scanner.nextInt();
		int coin10 = 10;
		
		int coin500Sum = coin500 * coin500Count;
		int coin100Sum = coin100 * coin100Count;
		int coin50Sum = coin50 * coin50Count;
		int coin10Sum = coin10 * coin10Count;
		
		int result = coin500Sum + coin100Sum + coin50Sum + coin10Sum;
		System.out.println("저금통 안의 동전의 총 액수 : " + result);
		
	}
}
