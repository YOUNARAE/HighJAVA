package kr.or.ddit.board.vo;
/**
 * DB 테이블에 존재하는 컬럼이름을 기준으로 데이터를 객체화한 클래스
 * @author PC-06
 * 
 * <p>
 *  DB 테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다.<br>
 * </p>
 * 
 */

import java.util.Date;

public class BoardVO {
	
	private int boardNo; // 글 번호 자동 증가
	private String boardTitle; // 제목
	private String boardContent; //내용
	private String boardWriter; // 작성자
	private Date regDate; // 작성날짜
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	
}
