package homework;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/*
 테이블생성 쿼리문
 create table hotel(
    room_id varchar2(8) not null,  -- 방ID
    mem_name varchar2(100) not null, -- 이름
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT HOTEL_PK PRIMARY KEY (room_id)
);
 */

public class Hotel {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//호텔 운영을 관리하는 프로그램 작성.(Map이용) - 키 값은 방번호
	private Map<String, Reservation> reservationRoomMap; // 키 값에 맞는 밸류값을 폰에서 가져온다
	private Scanner scan = new Scanner(System.in); 
	
	public Hotel() {
		reservationRoomMap = new HashMap<String, Reservation>();
	}
	
	//메뉴를 뿌려주는 메서드
	public void displayMenu() {
		System.out.println("**************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.업무종료");
		System.out.print("메뉴 입력 > ");
	}
	
	//프로그램을 시작하는 메서드
	public void openHotel() {
		System.out.println("**************************************");
		System.out.println("호텔 문을 열었습니다");
		System.out.println("**************************************");
		
		try {
			int menuNum;
			do {
				displayMenu();
				menuNum = scan.nextInt();
				switch(menuNum) {
				case 1: 
					insert();
					break;
				case 2: 
					delete();
					break;
				case 3: 
					selectAll();
					break;
				case 4: // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default: 
					System.out.println("잘못된 입력입니다. 선택할 메뉴 번호를 다시 입력하세요");
				}//case문 끝
			} while(menuNum!=4);
		} catch (InputMismatchException ex) {
			System.out.println("메뉴를 숫자로 제대로 입력해야지 임마");
			displayMenu();
		} 
	}//openHotel메서드 끝
	
	/**
	 * 예약정보를 모두 조회하는 메서드
	 */
	private void selectAll() {
		System.out.println("------------------------------");
		System.out.println("  예약 호실\t예약이름\t예약완료시간    ");
		System.out.println("------------------------------");
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from hotel";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String roomId = rs.getString("ROOM_ID");
				String memName = rs.getString("MEM_NAME");
				String rsDate = rs.getString("REG_DT");
				
				System.out.println(roomId + "\t"
							      + memName + "\t"
							      + rsDate);
			}
			System.out.println("------------------------------");
			System.out.println(" 출력작업 끝났는데 퇴근해도 됩니까");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, pstmt, rs);
		}
		
	}

	/**
	 * 예약정보를 삭제하는 메서드
	 */
	private void delete() {
		System.out.println();
		System.out.println("삭제할 예약 정보를 입력해주세요.");
		System.out.println("방 번호 입력 >> ");
		
		String roomId = scan.next();
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from hotel where room_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(roomId + "예약 정보 삭제 성공");
			} else {
				System.out.println(roomId + "예약 정보 삭제 실패!!!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, pstmt, rs);
		}
		
	}
	
	/**
	 * 회원정보 추가하는 메서드
	 */
	//insert
	private void insert() {
		
		boolean exist = false;
		String roomId= "";
		
		// 이미 등록된 사람인지 체크하기
		// get()메서드로 값을 가져올 때 가져올 자료가 없으면 null을 리턴한다.
		do {
			System.out.println();
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.print("방 번호 입력 >> ");
			roomId = scan.next();
			
			exist = checkMember(roomId);
			
			if(exist) {
				System.out.println(roomId + "호실은 이미 예약되었습니다");
				System.out.println("예약하실 방을 다시 입력해주세요");
			}
		} while(exist);
		
		// 자료준비
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String memName = scan.next();
		
		
		//JDBC코딩을 위한 템플릿이나 마찬가지인 부분
		try {
			conn = JDBCUtil.getConnection();
				
			String sql = "insert into hotel "
					   + "(ROOM_ID, MEM_NAME, REG_DT)"
					   + " values (?,?,sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			 
			pstmt.setString(1, roomId);
			pstmt.setString(2, memName);
			
			//인서트라서 업데이트이고 셀렉트일 때는 executeQuery
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(roomId + "호실 예약 성공");
			} else {
				System.out.println(roomId + "호실 예약 실패했다!!!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, pstmt, rs);
		}
	}
	/**
	 * 룸번호를 이용하여 회원이 존재하는지 체크하기 위한 메서드
	 * @param roomId 체크할 회원 ID
	 * @return 존재하면 true, 존재하지 않으면 false
	 */
	private boolean checkMember(String roomId) {
		
		boolean exist = false;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt " 
			           + "from hotel " 
					   + "where room_id = ?";
			
			pstmt = conn.prepareStatement(sql); //
			pstmt.setString(1, roomId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				exist=true;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, pstmt, rs);
		}
		return exist;
	}
	
	//main
	public static void main(String[] args) {
		Hotel hotelObj = new Hotel();
		hotelObj.openHotel();
	}	
}

class Reservation {
	private String roomId; //예약룸
	private String memName; //예약자 이름
	private Date rsDate; //예약한 시간
	
	public Reservation(String roomId, String memName, Date rsDate) {
		super();
		this.roomId = roomId;
		this.memName = memName;
		this.rsDate = rsDate;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memName == null) ? 0 : memName.hashCode());
		result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (memName == null) {
			if (other.memName != null)
				return false;
		} else if (!memName.equals(other.memName))
			return false;
		if (roomId == null) {
			if (other.roomId != null)
				return false;
		} else if (!roomId.equals(other.roomId))
			return false;
		return true;
	}
}
