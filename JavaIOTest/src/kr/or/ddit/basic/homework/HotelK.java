package kr.or.ddit.basic.homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelK {
	private Scanner scanner;
	private Map<Integer, Room> roomMap;
	
	public HotelK() {
		scanner = new Scanner(System.in);
		roomMap = new HashMap<Integer, Room>();
	}
	
	private void checkIn() {
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방 번호 입력 => ");
		int number = scanner.nextInt(); 
		scanner.nextLine();
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scanner.nextLine();
		System.out.println();
		System.out.println("전화번호를 입력하세요.");
		System.out.print("전화번호 입력 => ");
		String tel = scanner.nextLine();
		
		Room room = new Room(number, name, tel);
		roomMap.put(number, room);

		// 직렬화
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(
				  new BufferedOutputStream(
				  new FileOutputStream("d:/D_Other/Hotel.bin")));
					
			oos.writeObject(roomMap);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("체크인 되었습니다.");
	}
	
	private void show() {
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(
				  new BufferedInputStream(
				  new FileInputStream("d:/D_Other/Hotel.bin")));
			
			Map<Integer, Room> map = (Map<Integer, Room>) ois.readObject();
			Set<Map.Entry<Integer, Room>> entrySet = map.entrySet();
			Iterator<Map.Entry<Integer, Room>> entryIt = entrySet.iterator();
			while (entryIt.hasNext()) {
				Map.Entry<Integer, Room> entry = entryIt.next();
				System.out.printf("방 번호 : %d, 투수객 : %s\n", entry.getKey(), entry.getValue().getName());
			}
		} catch (IOException ex) {
			System.out.println("**************************");
			System.out.println("출력 완료...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// 실행 처음 보이는 화면
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();
		HotelK hotel = new HotelK();

		// 실행 시 보이는 메뉴
		while (true) {
			System.out.println("");
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.println("");
			System.out.print("메뉴선택 => ");
			int choice = scanner.nextInt();
			System.out.println("");

			
			switch (choice) {
			case 1:
				hotel.checkIn();
				break;
			// 방이 이미 예약되어있는 경우 예외처리 하기
			case 2:
				System.out.println("어느 방을 체크아웃 하시겠습니까?");
				System.out.print("방 번호 입력 => ");
				int typeRoomNum = scanner.nextInt();
				scanner.nextLine();
				hotel.roomMap.remove(typeRoomNum);
				System.out.println("체크아웃 되었습니다.");
				break;
			// 방이 예약되어있지 않는 경우 체크아웃하는 예외처리 하기
			case 3:
				hotel.show();
				break;
			// 예약이 없는 경우 나타내는 예외처리 하기
			case 4:
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				return;
			}
		}
	}
}

class Room implements Serializable {
	private int number;
	private String name;
	private String tel;

	public Room() {
		super();
	}
	
	public Room(int number, String name, String tel) {
		super();
		this.number = number;
		this.name = name;
		this.tel = tel;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}