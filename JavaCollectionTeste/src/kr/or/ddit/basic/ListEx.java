package kr.or.ddit.basic;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListEx {

	/*
	 * 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
	  Student클래스를 만든다.
	  생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
	  
	  이 Student객체들은 List에 저장하여 관리한다.
	  List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
	  총점의 역순으로 정렬하는 부분을 프로그램 하시오.
	  (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
	  (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
	   총점 정렬기준은 외부클래스에서 제공하도록 한다.)
	 */

	public static void main(String[] args) {
		
		List<Student> list = new ArrayList<Student>();
		Student student1 = new Student("1","다인수",80,50,25);
		Student student2 = new Student("3","가인수",50,30,15);
		Student student3 = new Student("2","나인수",90,80,95);
		Student student4 = new Student("5","바인수",10,30,25);
		Student student5 = new Student("4","사인수",50,30,15);
		Student student6 = new Student("6","라인수",90,90,100);
		Student student7 = new Student("7","파인수",50,30,15);
		
		
		list.add(0, student3);
		list.add(1, student2);
		list.add(2, student1);
		list.add(3, student5);
		list.add(4, student4);
		list.add(5, student6);
		list.add(6, student7);
		
		
		System.out.println("정렬 전 : ");
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}

		System.out.println();
		Collections.sort(list);
		System.out.println("학번 오른 차순으로 정렬 후 :");
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		Collections.sort(list, new ScoreNoDesc());
		System.out.println();
		System.out.println("점수 내림 차순으로 정렬 후  점수가 같으면 학번을 내림차순으로:");
		
		for(int i=0; i<list.size(); i++) {
			Student x = list.get(i);
			x.setScoreNo(i+1);
			System.out.println(list.get(i));
		}

		
	}
}

class ScoreNoDesc implements Comparator<Student>{
	
	@Override
	public int compare(Student totalScore1, Student totalScore2) {
		
		if(totalScore1.getTotalScore() > totalScore2.getTotalScore()) {
			return -1;
		}else if(totalScore1.getTotalScore() < totalScore2.getTotalScore()) {
			return 1;
		}else {
			return totalScore1.getStudentNo().compareTo(totalScore2.getStudentNo())*-1;
		}
	
	}
	
}

// 학생 클래스
class Student implements Comparable<Student>{

	private String studentNo;
	private String name;
	private int korScore;
	private int engScore;
	private int mathScore;
//	private int totalScore = this.getKorScore()+this.getEngScore()+this.getMathScore();
	private int totalScore;
	private int scoreNo;
	
	
	
	
	public Student(String studentNo, String name, int korScore, int engScore, int mathScore) {
		this.studentNo = studentNo;
		this.name = name;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
		this.totalScore = total();
	}

	
	

	public String getStudentNo() {
		return studentNo;
	}


	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getKorScore() {
		return korScore;
	}


	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}


	public int getEngScore() {
		return engScore;
	}


	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}


	public int getMathScore() {
		return mathScore;
	}


	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	
	public int getTotalScore() {
		return totalScore;
	}


	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}


	public int getScoreNo() {
		return scoreNo;
	}


	public void setScoreNo(int scoreNo) {
		this.scoreNo = scoreNo;
	}
	
	public int total() {
		return this.getKorScore()+this.getEngScore()+this.getMathScore();
	}


	@Override
	public String toString() {
		return "Student [studentNo=" + studentNo + ", name=" + name + ", korScore=" + korScore + ", engScore="
				+ engScore + ", mathScore=" + mathScore + ", totalScore=" + totalScore + ", scoreNo=" + scoreNo + "]";
	}




	@Override
	public int compareTo(Student student) {
		return this.getStudentNo().compareTo(student.getStudentNo());
	}



}

