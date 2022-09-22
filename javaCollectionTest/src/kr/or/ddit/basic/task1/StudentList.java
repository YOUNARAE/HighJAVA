package kr.or.ddit.basic.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentList {
	public static void main(String[] args) {
		
	  List<Student> studentList = new ArrayList<Student>();
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
	  
	  studentList.add(new Student("20220101", "장원영", 100, 90, 50));
	  studentList.add(new Student("20200113", "아이린", 50, 10, 20));
	  studentList.add(new Student("20170511", "김태희", 20, 80, 30));
	  studentList.add(new Student("20222121", "아이유", 70, 50, 50));
	  studentList.add(new Student("20230001", "카리나", 95, 95, 50));
	  	
	  //총점이 같으면 학번의 내림차순으로 정렬되도록 한다.
	  for(int i=0;i<studentList.size();i++) {
		  for(int j=i+1;j<studentList.size();j++) {
			  if(studentList.get(i).getSumScore() > studentList.get(j).getSumScore()) {
				  studentList.get(i).setRank(studentList.get(i).getRank() + 1);
			  } else {
				  studentList.get(j).setRank(studentList.get(j).getRank() + 1);
			  }
		  }
	  }
	  
	  
		System.out.println("정렬 없음 : " );
		for (Student std : studentList) {
			System.out.println(std);
		}
		System.out.println("-----------------------------------------------------------------");
		
		Collections.sort(studentList); 
		
		
		//학번오름차순
		System.out.println("학번 오름차순 : " );
		for (Student std : studentList) {
			System.out.println(std);
		}
		System.out.println("-----------------------------------------------------------------");
		
		
		//외부 정렬 기준을 이용한 정렬하기
		Collections.sort(studentList, new SortScoreRank());
		System.out.println("총점의 내림차순 정렬 후 : " );
		for (Student std : studentList) {
			System.out.println(std);
		}
		System.out.println("----------------------------------------------");

	}

}

class SortScoreRank implements Comparator<Student> {

	@Override
	public int compare(Student std1, Student std2) {
		
		if(std1.getSumScore() > std2.getSumScore()) { //양수가 리턴되면 된다는 뜻
			return 1;
		} else if(std1.getSumScore() == std2.getSumScore()) { //음수가 리턴되면 이라는 뜻
			return 0;
		} else {
			return -1;
		}
	}
}
