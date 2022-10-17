package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.or.ddit.board.util.JDBCUtil3;
import kr.or.ddit.board.vo.BoardVO;

/**
 * 필요한 쿼리문

create table myboard(
    board_no number not null,  -- 글번호
    board_title varchar2(100) not null, -- 글제목
    board_content varchar2(2000) not null, -- 글내용
    board_writer varchar2(50) not null, --  글쓴이
    reg_date DATE DEFAULT sysdate,  -- 등록일자
    CONSTRAINT myboard_PK PRIMARY KEY (board_no)
);

시퀀스 쿼리문 
create SEQUENCE seq_board
  INCREMENT BY 1 
  start with 1
  minvalue 1
  MAXVALUE 9999999999
  nocycle
  nocache;
  
  
    select seq_board.nextval from dual;
  
    insert into myboard (board_no, board_title, board_content, user_name, reg_date) 
  values (seq_board.nextval, '내용', '내용을 넣어주는 곳이다', '별명', sysdate);
 */
public class BoardDaoImplForJDBC implements IBoardDao {
	
	private static IBoardDao boardDao;
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private BoardDaoImplForJDBC() {
	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImplForJDBC();
		}
		return boardDao;
	}
	
	@Override
	public int insertBoard(BoardVO bv) {

		int cnt = 0;
		
		try {

			conn = JDBCUtil3.getConnection(); 
			
			String sql = " insert into myboard "
					   + "(board_no, board_title, board_content, board_writer, reg_date)" 
					   + "values (seq_board.nextval, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardContent());
			pstmt.setString(3, bv.getBoardWriter());
			
			cnt = pstmt.executeUpdate(); 
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public boolean checkBoard(int boardNo) {

		boolean exist = false;

		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select count(*) as cnt "
					   + "from myboard " 
					   + "where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql); // pstmt 객체가 만들어짐
			pstmt.setInt(1, boardNo);
			
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
		
		return exist;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "update myboard " 
			           + "set board_title = ?," 
			           + " board_content = ?," 
					   + " board_writer = ?,"
			           + " reg_date = sysdate "
					   + " where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardContent());
			pstmt.setString(3, bv.getBoardWriter());
			pstmt.setInt(4, bv.getBoardNo());
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from myboard where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> selectAllBoard() {

		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from MYBOARD";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				BoardVO bv = new BoardVO();
				bv.setBoardNo(rs.getInt("board_no"));
				bv.setBoardTitle(rs.getString("board_title"));
				bv.setBoardContent(rs.getString("board_content"));
				bv.setBoardWriter(rs.getString("board_writer"));
				
				boardList.add(bv);
			
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {

		List<BoardVO> boardList = new ArrayList<BoardVO>(); //비어있는 리스트를 넣어준다(null은 아님), 언제든지 변경할 수 있는 array
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from myboard where 1=1 ";
			if(bv.getBoardNo()!=0){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and board_no = ? ";
			}
			if(bv.getBoardTitle()!=null 
					&& !bv.getBoardTitle().equals("")){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and board_title = ? ";
			}
			if(bv.getBoardContent()!=null 
					&& !bv.getBoardContent().equals("")){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and board_content like '%'|| ? ||'%' ";
			}
			if(bv.getBoardWriter()!=null 
					&& !bv.getBoardWriter().equals("")){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and board_writer = ? ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1; //첫번째 물음표의 위치값, if문은 조건만 활용한다
			if(bv.getBoardNo()!=0){ 
				pstmt.setInt(index++, bv.getBoardNo());
			}
			if(bv.getBoardTitle()!=null 
					&& !bv.getBoardTitle().equals("")){
				pstmt.setString(index++, bv.getBoardTitle());
			}
			if(bv.getBoardContent()!=null 
					&& !bv.getBoardContent().equals("")){ 
				pstmt.setString(index++, bv.getBoardContent());

			}
			if(bv.getBoardWriter()!=null 
					&& !bv.getBoardWriter().equals("")){ 
				pstmt.setString(index++, bv.getBoardWriter());
				
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardVO bv2 = new BoardVO(); 
				bv2.setBoardNo(rs.getInt("board_no"));
				bv2.setBoardTitle(rs.getString("board_title"));
				bv2.setBoardContent(rs.getString("board_content"));
				bv2.setBoardWriter(rs.getString("board_writer"));
				bv2.setRegDate(rs.getDate("reg_date"));
				
				boardList.add(bv2);
			
			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return boardList;
	}
	
}
