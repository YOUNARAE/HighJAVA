package kr.or.ddit.ex;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Lotto {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			
			
			System.out.println("==========================");
			System.out.println("      Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("      1. Lotto 구입");
			System.out.println("      2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴 선택 : ");
			int menu = scanner.nextInt();
			if (menu == 1) {
				System.out.println("Lotto 구입 시작");
				System.out.println();
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.print("금액 입력 : ");
				int price = scanner.nextInt();
				int no = price / 1000;
				System.err.println("행운의 로또번호는 아래와 같습니다.");
				for (int i = 0; i < no; i++) {
					Set<Integer> lotto = new HashSet<Integer>();
					while (lotto.size() < 6) {
						Random random = new Random();
						int num = (random.nextInt(45) + 1);
						lotto.add(num);
					}
					System.out.println("로또번호" + (i + 1) + " : " + lotto);
				}
				System.out.println("받은 금액은 " + price + "원이고 거스름돈은 " + (price%1000) + "원입니다.");
			} else {
				System.out.println("감사합니다");
				break;
			}
		}
	}
}