package kr.or.ddit.basic.task1;

public class Student implements Comparable<Student> {

	private String studentId;
	private String name;
	private int korea;
	private int english;
	private int math;
	private int sumScores;
	private int rank = 1;

	public Student(String studentId, String name, int korea, int english, int math) {
		this.studentId = studentId;
		this.name = name;
		this.korea = korea;
		this.english = english;
		this.math = math;
		this.sumScores = korea + english + math;
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

	public int getKorea() {
		return korea;
	}

	public void setKorea(int korea) {
		this.korea = korea;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSumScore() {
		return sumScores;
	}

	public void setSumScore(int sumScore) {
		this.sumScores = sumScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "학번 : " + studentId + ", 이름 : " + name + ", 국어 : " + korea + ", 영어 : " + english
				+ ", 수학 : " + math + ", 총점 : " + sumScores + ", 등수 : " + rank;
	}
	
	@Override
	public int compareTo(Student student) {
		return this.getStudentId().compareTo(student.getStudentId());
	}
}