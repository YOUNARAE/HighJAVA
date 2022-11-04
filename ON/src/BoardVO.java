

import java.util.Date;

public class BoardVO {
	
	
	//최경수너는정말 너는 정말
	//최경수너어어어어어어는 정말 
	
	
	private long boardNo; // 글 번호 자동 증가
	private String boardTitle; // 제목
	private String boardContent; //내용
	private String boardWriter; // 작성자
	private Date regDate; // 작성날짜
	
	public BoardVO() {
	
		super();
	}
	
	public BoardVO(int boardNo, String boardTitle, String boardContent, String boardWriter, Date regDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.regDate = regDate;
	}
	
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
