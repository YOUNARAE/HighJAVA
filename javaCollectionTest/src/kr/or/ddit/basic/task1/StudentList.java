package kr.or.ddit.basic.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentList {
   public static void main(String[] args) {
      
     List<Student> studentList = new ArrayList<Student>(); //Student타입을 가지고 있는 리스트를 생성한다
      /*학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
     Student클래스를 만든다.//
     생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.//
     
     이 Student객체들은 List에 저장하여 관리한다.
     List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
     총점의 역순으로 정렬하는 부분을 프로그램 하시오.
     
     (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
     (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
      총점 정렬기준은 외부클래스에서 제공하도록 한다.)
      */
     
     studentList.add(new Student("20220101", "장원영", 100, 90, 50)); //생성한 스튜던트 리스트에 행을 추가했다, 임의적으로 값을 정해줬다
     studentList.add(new Student("20200113", "아이린", 50, 10, 20));
     studentList.add(new Student("20170511", "김태희", 20, 80, 30));
     studentList.add(new Student("20222121", "아이유", 70, 50, 50));
     studentList.add(new Student("20230001", "카리나", 95, 95, 50));
        
     //총점이 같으면 학번의 내림차순으로 정렬되도록 한다.
     
     
      System.out.println("정렬 없음 : " );
      for (Student std : studentList) { //향상된 for문을 통해서 스튜던트 리스트의 값들을 하나씩 출력한다
         System.out.println(std);
      }
      System.out.println("-----------------------------------------------------------------");
      
      Collections.sort(studentList); //Student 클래스에서 재선언한  compareTo메서드를 이용해서
      //컬렉션즈 소트 메소드에 넣어서 스튜던트 리스트를 정렬한다
   
      
      //학번오름차순
      System.out.println("학번 오름차순 : " ); //위에서 정렬된 스튜던트 리스트를 다시 한번 향상된 포문으로 값을 하나씩 출력준다
      for (Student std : studentList) { 
         System.out.println(std);
      }
      System.out.println("-----------------------------------------------------------------");
      //
      
 
      //외부 정렬 기준을 이용한 정렬하기
      Collections.sort(studentList, new SortScoreRank()); //외부정렬 클래스를 호출하는 형식
      System.out.println("총점의 내림차순 정렬 후 : " ); //SortScoreRank에 재선언한 메서드로 정렬한 결과가 출력된다
      for (Student std : studentList) {
         System.out.println(std);
      }
      System.out.println("----------------------------------------------");

   }
}


class SortScoreRank implements Comparator<Student> { //외부정렬자 글래스인 SortScoreRank 클래스를 만들어서, 만들어놓은 이유:내가 만든 객체가 아닌데, 만든 클래스를 내가 원하는대로 정렬하고 싶을 때 쓸 목적, 아무렇게나 쓰지말고 소트가 알아먹을 수 있도록 Comparator를 이용해라
   
   @Override
   public int compare(Student std1, Student std2) { //두 매개변수를 비교하는 compare메서드를 재정의했다.
      
      if(std1.getSumScore() > std2.getSumScore()) { //양수가 리턴되면 된다는 뜻 , 두 매개변수의 총점을 비교한는 기능을 선언했다.  앞에꺼가 큰데 음수이면 내림차순. 앞에가 크냐 작나 같냐만 생각하면됨
        return -1; //총점이 비교할 객체의 총점보다 작으면 양수를 리턴한다
      } else if(std1.getSumScore() == std2.getSumScore()) { //음수가 리턴되면 이라는 뜻 // 
         return 0; //총점이 비교할 객체와 같다면 0을 리턴한다
      } else {
          return -1; //총점이 비교할 객체의 총점보다 크면 음수를 리턴한다
      }
   }
}