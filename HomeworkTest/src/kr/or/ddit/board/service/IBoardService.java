package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

/**
 * 컨트롤러의 요청을 받아 처리하는 서비스의 Interface.
 * @author PC-06
 */
public interface IBoardService {
	
	/**
	 * 회원등록하기 위한 메서드
	 * @param mv 등록할 데이터가 저장된 MemberVO객체
	 * @return 회원등록이  성공하면 1 이상의 값이 반환됨.
	 */
	public int registBoard(BoardVO bv); 
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내기 위한 메서드
	 * @param memId 확인대상 회원ID
	 * @return 해당 회원ID가 있으면 true, 없으면 false
	 */
	public boolean checkBoard(int boardNo);
	
	/**
	 * 회원정보를 수정하기 위한 메서드
	 * @param mv update할 회원정보가 들어있는 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int modifyBoard(BoardVO bv);
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int removeBoard(int boardNo);
	
	/**
	 * 전체 회원 정보를 조회하기 위한 메서드
	 * @return 회원정보를 담고있는 List타입의 객체
	 */
	public List<BoardVO> selectAllBoard();
	
	/**
	 * 회원정보를 검색하는 메서드
	 * @param mv 회원정보를 검색하기 위한 데이터
	 * @return 검색된 결과를 담고 있는 List타입의 객체
	 */
	public List<BoardVO> searchBoard(BoardVO bv);
	
}
