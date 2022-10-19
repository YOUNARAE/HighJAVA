package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LottoProgram {
	/*
	 * 사용자는 로또를 구매할 때 구매할 금액을 입력하고 입력한 금액에 맞게 로또번호를 출력한다. (단, 로또 한장의 금액은 1000원이고
	 * 거스름돈도 계산하여 출력한다.)
	 */
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		boolean todo = true;

		while (todo) {
			System.out.println("=======================");
			System.out.println("로또프로그램");
			System.out.println("-----------------------");
			System.out.println("1. 로또 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("=======================");
			System.out.print("메뉴 선택 >> ");

			int num = scan.nextInt();

			switch (num) {

			case 1:
				System.out.println("Lotto 구입 시작");
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.println("금액 입력 >> ");
				int lottoPrice = scan.nextInt();

				int lottoCount = lottoPrice / 1000;
				int changeMoney = lottoPrice % 1000;

				System.out.println("행운의 번호는 다음과 같습니다");
				// HashSet을 이용하여 lottoNum 번호 중복제거
				Set<Integer> lottoNum = new HashSet<Integer>();
				// 45개 숫자 돌리기
				for (int i = 0; i < lottoCount; i++) {
					while (lottoNum.size() < 6) {
						int ranNum = (int) (Math.random() * 45 + 1);
						lottoNum.add(ranNum);
					}
					// 리스트로 담는다
					List<Integer> numList = new ArrayList<Integer>();
					numList.addAll(lottoNum);
					Collections.sort(numList);

					System.out.println("로또 " + (i + 1) + "번째 장 숫자들 : " + numList);
					lottoNum.clear();
				}
				System.out.println("받은 금액 : " + lottoPrice + "원, 거스름돈 " + changeMoney);
				break;

			case 2:
				System.out.println("=======================");
				System.out.println("프로그램을 종료합니다.\n감사합니다");
				todo = false;
				break;
			}

		}

	}
}
