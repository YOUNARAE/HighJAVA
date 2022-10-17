package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{
	
	private IBoardDao boardDao;
	
	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}
	
	@Override
	public int registBoard(BoardVO bv) {

		int cnt = boardDao.insertBoard(bv);
		
		if(cnt > 0) {
			// 메일발송기능 호출....
		}
		
		return cnt;
	}

	@Override
	public boolean checkBoard(int boardNo) {

		boolean exist = boardDao.checkBoard(boardNo);
		return exist;
	}

	@Override
	public int modifyBoard(BoardVO bv) {

		int cnt = boardDao.updateBoard(bv);
		return cnt;
	}

	@Override
	public int removeBoard(int boardNo) {

		int cnt = boardDao.deleteBoard(boardNo);
		return cnt;
	}

	@Override
	public List<BoardVO> selectAllBoard() {

		List<BoardVO> boardList = boardDao.selectAllBoard();
		return boardList;
	}
	
	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		
		//서비스가 해당 다오에게 할일을 위임하는 것
		List<BoardVO> boardList = boardDao.searchBoard(bv);
		return boardList; 
	}

}
