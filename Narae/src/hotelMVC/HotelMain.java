package hotelMVC;

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
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import hotelMVC.service.HotelServiceImpl;
import hotelMVC.service.IHotelService;
import hotelMVC.util.JDBCUtil;
import hotelMVC.vo.ReservationVO;

public class HotelMain {
	
	private IHotelService hotelService;	
	private Scanner scan = new Scanner(System.in); 
	
	public HotelMain() {
		hotelService = new HotelServiceImpl();
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
			System.out.println("메뉴를 숫자로 입력해야지");
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
		
		List<ReservationVO> reservationList = hotelService.selectAll();
		
		if(reservationList.size()==0) {
			System.out.println("회원정보가 존재하지 않습니다");
		} else {
			for (ReservationVO hv : reservationList) {

				System.out.println(hv.getRoomId() + "\t"
						         + hv.getMemName() + "\t"
						         + hv.getRegDate());
				
			}
		}
		System.out.println("------------------------------");
		System.out.println(" 출력작업 끝났는데 퇴근해도 됩니까");
		
	}

	/**
	 * 예약정보를 삭제하는 메서드
	 */
	private void delete() {
		
		System.out.println();
		System.out.println("삭제할 예약 정보를 입력해주세요.");
		System.out.println("방 번호 입력 >> ");
		
		String roomId = scan.next();
		
		int cnt = hotelService.removeReservation(roomId);
		
		if(cnt>0) {
			System.out.println(roomId + "예약 정보 삭제 성공");
		} else {
			System.out.println(roomId + "예약 정보 삭제 실패!!!");
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
			
			exist = hotelService.checkMember(roomId);
			
			if(exist) {
				System.out.println(roomId + "호실은 이미 예약되었습니다");
				System.out.println("예약하실 방을 다시 입력해주세요");
			}
		} while(exist);
		
		scan.hasNextLine(); // 버퍼 비우기
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String memName = scan.next();
		
		ReservationVO hv = new ReservationVO();
		hv.setRoomId(roomId);
		hv.setMemName(memName);
		
		int cnt = hotelService.reservationRoom(hv);
		
		if(cnt > 0) {
			System.out.println(roomId + "호실 예약 성공");
		} else {
			System.out.println(roomId + "호실 예약 실패했다!!!");
		}
	}
	
	//main
	public static void main(String[] args) {
		HotelMain hotelObj = new HotelMain();
		hotelObj.openHotel();
	}	
}