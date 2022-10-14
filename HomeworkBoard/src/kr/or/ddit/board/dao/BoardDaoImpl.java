package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil3;

public class BoardDaoImpl implements IBoardDao {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	@Override
	public int insertBoard(BoardVO bv) {

		int cnt = 0;
		
		try {

			conn = JDBCUtil3.getConnection(); 
			
			String sql = "insert into jdbc_board "
					   + "(BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)" 
					   + "values (?, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getBoardNo());
			pstmt.setString(2, bv.getBoardTitle());
			pstmt.setString(3, bv.getWriter());
			pstmt.setString(4, bv.getBoardContent());
			
			cnt = pstmt.executeUpdate(); 
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public boolean checkBoard(String boardNo) {

		boolean exist = false;

		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select count(*) as cnt "
					   + "from jdbc_board " 
					   + "where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql); // pstmt 객체가 만들어짐
			pstmt.setString(1, boardNo);
			
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
			
			String sql = "update jdbc_board " 
			           + "set board_title = ?," 
					   + " board_writer = ?,"
			           + " board_content = ?" 
					   + " where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardTitle());
			pstmt.setString(3, bv.getWriter());
			pstmt.setString(4, bv.getBoardContent());
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(String boardNo) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
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
			
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				BoardVO bv = new BoardVO();
				bv.setBoardNo(rs.getString("board_no"));
				bv.setBoardTitle(rs.getString("board_title"));
				bv.setWriter(rs.getString("board_writer"));
				bv.setBoardContent(rs.getString("board_content"));
				
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
			
			String sql = "select * from jdbc_board where 1=1 ";
			if(bv.getBoardNo()!=null 
					&& !bv.getBoardNo().equals("")){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and board_no = ? ";
			}
			if(bv.getBoardTitle()!=null 
					&& !bv.getBoardTitle().equals("")){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and board_title = ? ";
			}
			if(bv.getWriter()!=null 
					&& !bv.getWriter().equals("")){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and board_writer = ? ";
			}
			if(bv.getBoardContent()!=null 
					&& !bv.getBoardContent().equals("")){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and board_content like '%'|| ? ||'%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1; //첫번째 물음표의 위치값, if문은 조건만 활용한다
			if(bv.getBoardNo()!=null 
					&& !bv.getBoardNo().equals("")){ 
				pstmt.setString(index++, bv.getBoardNo());
			}
			if(bv.getBoardTitle()!=null 
					&& !bv.getBoardTitle().equals("")){
				pstmt.setString(index++, bv.getBoardTitle());
			}
			if(bv.getWriter()!=null 
					&& !bv.getWriter().equals("")){ 
				pstmt.setString(index++, bv.getWriter());

			}
			if(bv.getBoardContent()!=null 
					&& !bv.getBoardContent().equals("")){ 
				pstmt.setString(index++, bv.getBoardContent());

			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardVO bv2 = new BoardVO(); // 파라미터가 mv라서 겹치지 말라고 mv2라고 바꿔줌
				bv2.setBoardNo(rs.getString("board_no"));
				bv2.setBoardTitle(rs.getString("board_title"));
				bv2.setWriter(rs.getString("board_writer"));
				bv2.setBoardContent(rs.getString("board_content"));
				
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
