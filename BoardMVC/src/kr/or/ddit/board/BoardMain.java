package kr.or.ddit.board;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;

/**
 * 
 * 게시판 만들기
 * 
 * 위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 

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
 */

public class BoardMain {
	
	private IBoardService boardService;
	
	private Scanner scan = new Scanner(System.in); 
	
	public BoardMain() {
		boardService = new BoardServiceImpl();
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새글 작성");
		System.out.println("  2. 글 삭제");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 글 검색");
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
					insertBoard();
					break;
				case 2 :  // 자료 삭제
					deleteBoard();
					break;
				case 3 :  // 자료 수정
					updateBoard();
					break;
				case 4 :  // 전체 자료 출력
					selectAllBoard();
					break;
				case 5 :  // 자료검색
					searchBoard();
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
	private void selectAllBoard() {

		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println(" 번호\t제 목\t글쓴이\t\t글내용  ");
		System.out.println("-------------------------------------------------------");
		
		List<BoardVO> boardList = boardService.selectAllBoard();
		
		if(boardList.size()==0) {
			System.out.println("게시글이 존재하지 않습니다");
		} else {
			
			for (BoardVO bv : boardList) {

				System.out.println(bv.getBoardNo() + "\t"
						+ bv.getBoardTitle() + "\t"
						+ bv.getBoardContent() + "\t"
						+ bv.getBoardWriter() + "\t"		
						+ bv.getRegDate());		
			}
			
		}
		
		System.out.println("-------------------------------------------------------");
		System.out.println("출력 작업 끝.");
	}

	/**
	 * 회원정보를 삭제하기 위한 메서드
	 */
	private void deleteBoard() {
		
		System.out.println();
		System.out.println("수정할 게시글 번호를 입력하세요.");
		System.out.print("글 번호 >> ");
		
		int boardNo = scan.nextInt();
		
		int cnt = boardService.removeBoard(boardNo);
		
		if(cnt > 0) {
			System.out.println(boardNo + " 번 글 삭제 성공.");
		} else {
			System.out.println(boardNo + "번 글 삭제 실패!!!");
		}
				
	}

	/**
	 * 회원정보를 수정하기 위한 메서드
	 */
	private void updateBoard() {
		
		boolean exist = false;
		
		int boardNo;
		
		do {
			System.out.println();
			System.out.println("수정할 게시글 정보를 입력하세요.");
			System.out.print("글 번호 >> ");
			boardNo = scan.nextInt(); 
			
			exist = boardService.checkBoard(boardNo);
			
			if(!exist) { // 자료가 없으면
				System.out.println("글 번호가 " + boardNo + "인 게시글은 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		} while(!exist); //exist가 true일 동안에 

		
		System.out.print("글 제목 >>");
		String boardTitle = scan.next();

		System.out.print("글쓴이 >>");
		String boardWriter = scan.next();

		scan.nextLine(); // 버퍼 비우기
		
		System.out.print("글 내용>>");
		String boardContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		int cnt = boardService.modifyBoard(bv);
		
		if(cnt > 0) {
			System.out.println(boardNo + " 번 글 수정 성공.");
		} else {
			System.out.println(boardNo + " 번 글 수정 실패!!!");
		}
		
	}

	/**
	 * 회원정보 추가하는 메서드
	 */
	private void insertBoard() {

//		boolean exist = false; //존재하는 사람인지 체크하기 위한
//		int boardNo=0;


		System.out.print("글 제목 >>");
		String boardTitle = scan.next();
		
		scan.nextLine(); // 버퍼 비우기

		System.out.print("글쓴이 >>");
		String boardWriter = scan.next();

		scan.nextLine(); // 버퍼 비우기
		
		System.out.print("글 내용 >>");
		String boardContent = scan.nextLine();
		
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardContent(boardContent);
		bv.setBoardWriter(boardWriter);
		
		
		int cnt = boardService.registBoard(bv);
		
		if(cnt > 0) {
			System.out.println("글 작성 성공.");
		} else {
			System.out.println("글 작성 실패!!!");
		}
	}
	
	/**
	 * 회원정보를 검색하기 위한 메서드
	 */
	public void searchBoard() {
	// 검색할 회원ID, 회원이름, 전화번호, 주소 등을 입력하면
	// 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
	// 주소는 입력한 값이 포함만 되어도 검색되도록 한다.
	// 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
		
		scan.nextLine(); // 버퍼에 비우기
		System.out.println();
		System.out.println("검색할 글 정보를 입력하세요.");
		//System.out.print("글 번호 >> ");
		//int boardNo = scan.nextInt(); //trim()으로 좌우공백 지우기

		System.out.print("글 제목 >> ");
		String boardTitle = scan.nextLine().trim(); 
		
		System.out.print("글 내용 >> ");
		String boardContent = scan.nextLine().trim(); 

		System.out.print("글쓴이 >> ");
		String boardWriter = scan.nextLine().trim(); 
		
		BoardVO bv = new BoardVO();
		
		//bv.setBoardNo(boardNo);
		bv.setBoardTitle(boardTitle);
		bv.setBoardContent(boardContent);
		bv.setBoardWriter(boardWriter);
		
		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println(" 번호\t제 목\\t글내용\t글쓴이\t등록일자  ");
		System.out.println("-------------------------------------------------------");
		
		List<BoardVO> boardList = boardService.searchBoard(bv);
		
		if(boardList.size()==0) {
			System.out.println("게시글이 존재하지 않습니다");
		} else {
			
			for (BoardVO bv2 : boardList) {

				System.out.println(bv2.getBoardNo() + "\t"
						+ bv2.getBoardTitle() + "\t"
						+ bv2.getBoardContent() + "\t"
						+ bv2.getBoardWriter() + "\t"
						+ bv2.getRegDate());		
			}
			
		}
		
		System.out.println("-------------------------------------------------------");
		System.out.println("검색 작업 끝.");
		
		
	}

	
	public static void main(String[] args) {
		BoardMain boardObj = new BoardMain();
		boardObj.start();
	}

}






