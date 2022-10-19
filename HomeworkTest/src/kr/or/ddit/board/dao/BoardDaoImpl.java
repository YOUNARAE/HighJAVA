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

import kr.or.ddit.batis.MyBatisDao;
import kr.or.ddit.board.util.JDBCUtil3;
import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl extends MyBatisDao implements IBoardDao { // MyBatisDao를 상속받음
	
	private static IBoardDao boardDao; 
	
	private BoardDaoImpl() {
	}
	
	public static IBoardDao getInstance() {
		if(boardDao==null){
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	// 싱글톤

	@Override
	public int insertBoard(BoardVO bv) {
		return insert("board.insertBoard",bv);
	}

	@Override
	public boolean checkBoard(int boardNo) {
		boolean exist = false;
		int cnt = (int) selectOne("board.checkBoard", boardNo);
		
		if(cnt>0) {
			exist = true;
		}
		return exist;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		return update("board.updateBoard", bv);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return delete("board.deleteBoard", boardNo);
	}

	@Override
	public List<BoardVO> selectAllBoard() {
		return selectList("board.selectAllBoard", null);
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		return selectList("board.searchBoard", bv);
	}
	
}
