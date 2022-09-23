package kr.or.ddit.basic.task1;


public class Student implements Comparable<Student> { //내가 정렬시키고 싶은 객체에 다른 객체와 비교하여 ,나랑 비교했을때 내가 더 크면 양수, 내가 비교했을 때 내가 작으면 음수(오름차순일때)

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
            + ", 수학 : " + math + ", 총점 : " + sumScores;
   }
   
   @Override
   public int compareTo(Student student) { //Comparable 인터페이스를 쓰기 위해 compareTo라는 메소드를 이용해서 나 자신과 나 자신을 비교한다
      return this.getStudentId().compareTo(student.getStudentId()); // 자기 자신의 학번과 매개변수로 받은 학번과 비교한 값을 인트값으로 리턴받는다
   }
}