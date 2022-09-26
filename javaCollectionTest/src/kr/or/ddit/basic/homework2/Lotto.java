package kr.or.ddit.basic.homework2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Lotto {
   /*
    사용자는 로또를 구매할 때 구매할 금액을 입력하고 입력한 금액에 맞게 로또번호를 출력한다. 
    (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여 출력한다.)
    */
   public static void main(String[] args) {
      boolean toDo = true;
      Scanner scanner = new Scanner(System.in);
      
      while(toDo) {
         System.out.println("=========================");
         System.out.println("Lotto 프로그램");
         System.out.println("-------------------------");
         System.out.print("1.Lotto 구입\n2.프로그램 종료\n");
         System.out.print("메뉴선택 : ");
         int num = scanner.nextInt();
         
         switch (num) {
            case 1: {
                  System.out.println("Lotto 구입 시작\n(1000원에 로또번호 하나입니다.)");
                  System.out.print("금액 입력 : ");
                  int lottoPrice = scanner.nextInt();
                  if(lottoPrice < 1000) {
                     System.out.println("금액이 부족합니다");
                     break;
                  }
                  
                  int lottoCount = lottoPrice / 1000;
                  int changeMoney = lottoPrice % 1000;
                  
                  /* 랜덤 로또번호 출력*/
                  System.out.println("행운의 로또 번호는 아래와 같습니다");
                  Set<Integer> lottoNum = new HashSet<Integer>();
                  /*와일문을 돌려서 45까지의 숫자 중 중복되지 않는 6개 뽑는다 */
                  for(int i=0;i<lottoCount;i++) {
                     while(lottoNum.size()<6) {
                        int ranNum = (int) (Math.random() * 45 + 1);
                        lottoNum.add(ranNum);
                     }
                     //로또번호 작은 숫자부터 정렬하기
                     List<Integer> NumList = new ArrayList<Integer>();
                     NumList.addAll(lottoNum);
                     Collections.sort(NumList);
                     
                     System.out.println("로또 "+ (i+1) + "번째 장 만들어진 랜덤 숫자들 : " + NumList);      
                     lottoNum.clear();
                  }
                  System.out.println("받은 금액 : "+lottoPrice +" 원 , 거스름 돈 : " + changeMoney + "원 입니다.");
                  break;
               }
               case 2: {
                  System.out.println("감사합니다");
                  toDo = false;
                  break;
               }
         }
      }
   }
}