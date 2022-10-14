package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil3;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/

/**
 * 1) 오라클 Driver 설정
 *  - JDK 자바에서 미리 정해놓은 JDBC 코딩에 필요한 인터페이스 : 
 *    Connection
 *    Statement, PreparedStatement
 *    ResultSet
 *    => 4개를 가지고 코딩하는 것이 JDBC 코딩이라고 한다
 * 	  => 이것들을 모아놓은 것이 ojdbc6.jar파일
 * 
 * 2) Connection 객체 생성
 * 
 * 3) 커넥션으로 가져온 객체를 이용해  Statement 객체를 생성
 * 
 * 4) select 인 경우 Result 객체를 리턴받는다
 * 
 * 5) close 자원반납
 * 
 */

public class T01MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					selectAllMember();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}

	/**
	 * 전체 회원정보를 출력하기 위한 메서드
	 */
	private void selectAllMember() {

		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println(" ID\t이 름\t전화번호\t\t주소  ");
		System.out.println("-------------------------------------------------------");
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
			
				System.out.println(memId + "\t"
						+ memName + "\t"
						+ memTel + "\t"
						+ memAddr);
				// 와일문 돌때마다 한 줄씩 내용을 찍어줌
			}
			System.out.println("-------------------------------------------------------");
			System.out.println("출력 작업 끝.");
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원정보를 삭제하기 위한 메서드
	 */
	private void deleteMember() {
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원 ID >> ");
		
		String memId = scan.next();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + " 회원정보 삭제 성공.");
			} else {
				System.out.println(memId + "회원정보 삭제 실패!!!");
			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
				
	}

	/**
	 * 회원정보를 수정하기 위한 메서드
	 */
	private void updateMember() {
		
		boolean exist = false;
		
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요.");
			System.out.print("회원 ID >> ");
			memId = scan.next(); 
			
			exist = checkMember(memId);
			
			if(!exist) { // 자료가 없으면
				System.out.println("회원ID가 " + memId + "인 회원은 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		} while(!exist); //exist가 true일 동안에 

		//JDBC코딩을 위한 자료를 준비하는 과정
		System.out.print("회원 이름 >>");
		String memName = scan.next();

		System.out.print("회원 전화번호 >>");
		String memTel = scan.next();

		scan.nextLine(); // 버퍼 비우기
		
		System.out.print("회원 주소 >>");
		String memAddr = scan.nextLine();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "update mymember " 
			           + "set mem_name = ?," 
					   + " mem_tel = ?,"
			           + " mem_addr = ?" 
					   + " where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + " 회원정보 수정 성공.");
			} else {
				System.out.println(memId + "회원정보 수정 실패!!!");
			}

			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
	}

	/**
	 * 회원정보 추가하는 메서드
	 */
	private void insertMember() {

		boolean exist = false; //존재하는 사람인지 체크하기 위한
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.print("회원 ID >> ");
			memId = scan.next(); //scan은 미리 초기화를 해놨다 멤버변수로
			
			exist = checkMember(memId);
			
			if(exist) { //여기가 true면 while도 true라서 다시 처음으로 돌아간다.
				System.out.println("회원ID가 " + memId + "인 회원은 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		} while(exist); //exist가 true일 동안에 

		//JDBC코딩을 위한 자료를 준비하는 과정
		System.out.print("회원 이름 >>");
		String memName = scan.next();

		System.out.print("회원 전화번호 >>");
		String memTel = scan.next();

		scan.nextLine(); // 버퍼 비우기
		
		System.out.print("회원 주소 >>");
		String memAddr = scan.nextLine();

		
		//JDBC코딩을 위한 템플릿이나 마찬가지인 부분
		try {
			
			// Annotation T01 참조
			//Class.forName("orcle.jdbc.driver.OracleDriver"); // 클래스 오브젝트를 리턴하는 메소드
			
			// 서버에 필요한 오라클 정보를 넣어줌
			//conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WDH94", "java");
			
			conn = JDBCUtil3.getConnection(); // 만들어놓은 것을 갖고 오기만 하면 된다
			
			// 쿼리문 준비
			String sql = "insert into mymember "
					   + "(MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR, REG_DT)" 
					   + "values (?, ?, ?, ?, sysdate)";
			
			// 쿼리문 날림
			pstmt = conn.prepareStatement(sql);
			
			// 물음표박스 셋팅
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			//인서트라서 업데이트이고 셀렉트일때는 executeQuery
			int cnt = pstmt.executeUpdate(); 
			
			if(cnt > 0) {
				System.out.println(memId + " 회원정보 추가 성공.");
			} else {
				System.out.println(memId + "회원정보 추가 실패!!!");
			}

			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원ID를 이용하여 회원이 존재하는지 체크하기 위한 메서드
	 * @param memId 체크할 회원 ID
	 * @return 존재하면 true, 존재하지 않으면 false
	 */
	private boolean checkMember(String memId) {
		
		boolean exist = false;

		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select count(*) as cnt "
					   + "from mymember " 
					   + "where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql); // pstmt 객체가 만들어짐
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) { //1번밖에 안 돌기때문에 와일문을 써도 되지만 if문을 써도 된다
				cnt = rs.getInt("cnt"); //컬럼명
			}
			
			if(cnt > 0) {
				exist = true;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return exist; //마지막에 반환해준다
	}

	
	public static void main(String[] args) {
		T01MemberInfoTest memObj = new T01MemberInfoTest();
		memObj.start();
	}

}






