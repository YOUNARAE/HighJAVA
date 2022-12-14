package kr.or.ddit.basic.homework3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelReservation {
	//호텔 운영을 관리하는 프로그램 작성.(Map이용) - 키값은 방번호 
	private Map<String, Reservation> reservationRoomMap; //ㅔ
	private Scanner scan;
	
	public HotelReservation() {
		scan = new Scanner(System.in);
		reservationRoomMap = new HashMap<String, Reservation>();
	}
	
	//메뉴를 뿌려주는 메서드
	public void displayMenu() {
		System.out.println("****************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.업무종료");
		System.out.print("메뉴 입력 > ");
	}
	
	//프로그램을 시작하는 메서드
	public void startHotel() {
		System.out.println("*****************");
		System.out.println("호텔 문을 열었습니다");
		System.out.println("*****************");
		
		while (true) {
			displayMenu();
			int menuNum = scan.nextInt();
			
			switch (menuNum) {
			case 1: insert();
				break;
			case 2: delete();
				break;
			case 3: selectAll();	
				break;
			case 4: 
				System.out.println("프로그램을 종료합니다...");
				return;
			default:
				System.out.println("잘못된 입력입니다. 선택할 메뉴 번호를 입력해주세요");
			}//case문 끝
		}//while문 끝
	}//start메서드 끝
	
	
	private void delete() { //객실예약 삭제하는 메서드
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String room = scan.next();
		
		// remove(key) => 삭제 성공하면 삭제된 value값을 반환하고 실패하면 null을 리턴함.
		if(reservationRoomMap.remove(room)==null) {
			System.out.println(room + "방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println(room + "방을 체크아웃 하였습니다");
		}
	}

	private void selectAll() {	// 객실예약 조회하는 메서드

		Set<String> keySet = reservationRoomMap.keySet();
		
		if(keySet.size()==0) {
			System.out.println("체크인 내역이 없습니다");
		} else {
			Iterator<String> it = keySet.iterator();
			
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				String room = it.next();
				Reservation r = reservationRoomMap.get(room);
				System.out.println("--------------------------------");
				System.out.println(cnt + ". " + "방 번호 : " + r.getName() + ",\t투숙객 : " + r.getRoom());
			}
		}
		
	}
	private void insert() { //체크인하는 메서드

		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방 번호 입력 => ");
		String room = scan.next();
		
		// 이미 등록된 사람인지 체크하기
		// get()메서드로 값을 가져올 때 가져올 자료가 없으면 null을 리턴한다.
		if( reservationRoomMap.get(room) != null ) { //널이면 이미 넣으려고 하는 데이터와 같은 데이터 넣어진 자료가 없다는 것이다
			System.out.println(room + "방은 이미 체크인이 된 방입니다");
			return; // 메서드 종료.
		}
		
		System.out.print("이름 >> ");
		String name = scan.next(); //공백,탭,스페이스 구분으로 뜯어서 받아올 수 있는 것이 next
		
		Reservation p = new Reservation(room, name);
		
		reservationRoomMap.put(room, p);
		System.out.println("체크인 되었습니다");
	}

	public static void main(String[] args) {
		new HotelReservation().startHotel();
	}
}

class Reservation{
	private String room; //예약룸
	private String name; //예약자 이름
	
	public Reservation(String room, String name) {
		super();
		this.room = room;
		this.name = name;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Reservation [room=" + room + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}
}