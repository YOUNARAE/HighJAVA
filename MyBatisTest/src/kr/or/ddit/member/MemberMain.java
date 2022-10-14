package kr.or.ddit.member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberMain {
	
	private IMemberService memService;
	
	private Scanner scan = new Scanner(System.in); 
	
	public MemberMain() {
		memService = MemberServiceImpl.getInstance();
	}
	
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
		System.out.println("  5. 자료 검색");
		System.out.println("  6. 작업 끝.");
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
				case 5 :  // 자료검색
					searchMember();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}

	/**
	 * 전체 회원정보를 출력하기 위한 메서드
	 */
	private void selectAllMember() {

		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println(" ID\t이 름\t전화번호\t\t주소  ");
		System.out.println("-------------------------------------------------------");
		
		List<MemberVO> memList = memService.selectAllMember();
		
		if(memList.size()==0) {
			System.out.println("회원정보가 존재하지 않습니다");
		} else {
			
			for (MemberVO mv : memList) {

				System.out.println(mv.getMemId() + "\t"
						+ mv.getMemName() + "\t"
						+ mv.getMemTel() + "\t"
						+ mv.getMemAddr());		
			}
			
		}
		
		System.out.println("-------------------------------------------------------");
		System.out.println("출력 작업 끝.");
	}

	/**
	 * 회원정보를 삭제하기 위한 메서드
	 */
	private void deleteMember() {
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원 ID >> ");
		
		String memId = scan.next();
		
		int cnt = memService.removeMember(memId);
		
		if(cnt > 0) {
			System.out.println(memId + " 회원정보 삭제 성공.");
		} else {
			System.out.println(memId + "회원정보 삭제 실패!!!");
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
			
			exist = memService.checkMember(memId);
			
			if(!exist) { // 자료가 없으면
				System.out.println("회원ID가 " + memId + "인 회원은 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		} while(!exist); //exist가 true일 동안에 

		
		System.out.print("회원 이름 >>");
		String memName = scan.next();

		System.out.print("회원 전화번호 >>");
		String memTel = scan.next();

		scan.nextLine(); // 버퍼 비우기
		
		System.out.print("회원 주소 >>");
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memService.modifyMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId + " 회원정보 수정 성공.");
		} else {
			System.out.println(memId + "회원정보 수정 실패!!!");
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
			
			exist = memService.checkMember(memId);
			
			if(exist) { //여기가 true면 while도 true라서 다시 처음으로 돌아간다.
				System.out.println("회원ID가 " + memId + "인 회원은 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		} while(exist);

		System.out.print("회원 이름 >>");
		String memName = scan.next();

		System.out.print("회원 전화번호 >>");
		String memTel = scan.next();

		scan.nextLine(); // 버퍼 비우기
		
		System.out.print("회원 주소 >>");
		String memAddr = scan.nextLine();
		
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		
		int cnt = memService.registMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId + " 회원정보 추가 성공.");
		} else {
			System.out.println(memId + "회원정보 추가 실패!!!");
		}
	}
	
	/**
	 * 회원정보를 검색하기 위한 메서드
	 */
	public void searchMember() {
	// 검색할 회원ID, 회원이름, 전화번호, 주소 등을 입력하면
	// 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
	// 주소는 입력한 값이 포함만 되어도 검색되도록 한다.
	// 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
		
		scan.nextLine(); // 버퍼에 비우기
		System.out.println();
		System.out.println("검색할 회원 정보를 입력하세요.");
		System.out.print("회원 ID >> ");
		
		String memId = scan.nextLine().trim(); //trim()으로 좌우공백 지우기

		System.out.print("회원 이름 >> ");
		String memName = scan.nextLine().trim(); 

		System.out.print("회원 전화번호 >> ");
		String memTel = scan.nextLine().trim(); 

		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine().trim(); 
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println(" ID\t이 름\t전화번호\t\t주소  ");
		System.out.println("-------------------------------------------------------");
		
		List<MemberVO> memList = memService.searchMember(mv);
		
		if(memList.size()==0) {
			System.out.println("회원정보가 존재하지 않습니다");
		} else {
			
			for (MemberVO mv2 : memList) {

				System.out.println(mv2.getMemId() + "\t"
						+ mv2.getMemName() + "\t"
						+ mv2.getMemTel() + "\t"
						+ mv2.getMemAddr());		
			}
			
		}
		
		System.out.println("-------------------------------------------------------");
		System.out.println("검색 작업 끝.");
		
		
	}

	
	public static void main(String[] args) {
		MemberMain memObj = new MemberMain();
		memObj.start();
	}

}






