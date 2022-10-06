package practice;

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
	 List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 총점의 역순으로 정렬하는 부분을 프로그램 하시오.
	 (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
	 (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다.)
	  
	  */
	public static void main(String[] args) {
		
		List<Student> list = new ArrayList<Student>();
		Student student1 = new Student("0901", "이상해씨", 80, 76, 20);
		Student student2 = new Student("0701", "파이리", 65, 60, 25);
		Student student3 = new Student("0903", "피카츄", 75, 50, 70);
		Student student4 = new Student("0801", "꼬부기", 90, 90, 65);
		Student student5 = new Student("0902", "럭키", 76, 80, 20);
		
		list.add(0, student1);
		list.add(1, student2);
		list.add(2, student3);
		list.add(3, student4);
		list.add(4, student5);
		
		System.out.println("정렬 전 ------------------------------------------------------------------------------");
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println();
		
		System.out.println("학번 오름차순으로 정렬 후  ------------------------------------------------------------------");
		Collections.sort(list);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println();
		
		System.out.println("성적이 같으면 학번이 신입생인 애들부터 위에 출력  -------------------------------------------------");
		Collections.sort(list, new ScoreRankDesc());
	
		for(int i=0; i<list.size(); i++) {
			Student std = list.get(i);
			std.setScoreRank(i+1);
			System.out.println(list.get(i));
		}
		System.out.println();
	}
}
class ScoreRankDesc implements Comparator<Student>{
	
	@Override
	public int compare(Student totalScore1, Student totalScore2) {

		if(totalScore1.getTotalScore()>totalScore2.getTotalScore()) {
			return -1;
		} else if(totalScore1.getTotalScore()<totalScore2.getTotalScore()) {
			return 1;
		} else {
			return totalScore1.getStudentId().compareTo(totalScore2.getStudentId()) * -1;
		}
	}
	
}

class Student implements Comparable<Student> {
	private String studentId;
	private String name;
	private int koreanScore;
	private int englishScore;
	private int mathScore;
	private int totalScore;
	private int scoreRank;
	
	public Student(String studentId, String name, int koreanScore, int englishScore, int mathScore) {
		this.studentId = studentId; 
		this.name = name;
		this.koreanScore = koreanScore;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
		this.totalScore = getTotalScore();
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKoreanScore() {
		return koreanScore;
	}

	public void setKoreanScore(int koreanScore) {
		this.koreanScore = koreanScore;
	}

	public int getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(int englishScore) {
		this.englishScore = englishScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getTotalScore() {
		return this.getKoreanScore()+this.getEnglishScore()+this.getMathScore();
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getScoreRank() {
		return scoreRank;
	}

	public void setScoreRank(int scoreRank) {
		this.scoreRank = scoreRank;
	}

	@Override
	public String toString() {
		return "학번 : " + studentId + ", 이름 : " + name + ", 국어점수 : " + koreanScore + ", 영어 점수 : "
				+ englishScore + ", 수학점수 : " + mathScore + ", 총 점수 : " + totalScore + ", 성적순위 : " + scoreRank + "]";
	}

	@Override
	public int compareTo(Student student) {
		return this.getStudentId().compareTo(student.getStudentId());
	}
	
}

