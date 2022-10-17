package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.board.vo.BoardVO;

public class MyBatisTest {

	public static void main(String[] args) {
		
		// MyBatis를 이용하여 DB데이터를 처리하는 작업 순서
		// 1. MyBatis의 환경설정 파일을 읽어와 실행시킨다.
		
		SqlSessionFactory sqlSessionFactory = null;
		
		try {
			
			// 1-1. xml 설정파일 읽어오기
			// 설정파일의 인코딩정보 설정하기
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("mybatis-config.xml");
			
			// 1-2 위에서 읽어온 Reader객체를 이용하여 
			// SqlSessionFactory객체 생성하기
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		/////////////////////////////////////////////////////////////////
		
		// 2. 실행할 SQL문에 맞는 쿼리문을 호출하여 원하는 작업을 수행한다.
		
		// 2-1. insert 연습
		System.out.println("insert 작업 시작....");
		
		// 1) 저장할 데이터를 VO에 담는다.
		BoardVO bv = new BoardVO();
		bv.setBoardTitle("제목");
		bv.setBoardContent("내용입니다");
		bv.setBoardWriter("지더래곤");

		
		//  2) SqlSession 객체를 이용하여 해당 쿼리문을 실행한다.
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int cnt = sqlSession.insert("boardTest.insertBoard", bv); //"네임스페이스.해당쿼리아이디"
			
			if(cnt > 0) {
				System.out.println("insert 작업 성공.");
			} else {
				System.out.println("insert 작업 실패 !!!");
			}
			sqlSession.commit(); // 커밋
			
		} catch (PersistenceException ex) {
			sqlSession.rollback(); //롤백
			throw new RuntimeException(
						"데이터 등록중 예외발생", ex);
		} finally {
			sqlSession.close(); //세션 종료
		}
		
		System.out.println("---------------------------------------");
		//인서트나 업데이트나 딜리트는 트랜잭션 단위로 관리가 필요하다,ex은행사이트, 롤백,커밍을 해줘야된다
		
		// 2-2 update 작업 연습
		System.out.println("update 작업 시작....");
		
		bv = new BoardVO();
		bv.setBoardNo(4);
		bv.setBoardTitle("지금막바꾼제목");
		bv.setBoardContent("바뀐내용입니다");
		bv.setBoardWriter("박명수");
		
		sqlSession = sqlSessionFactory.openSession();
		
		try {
			
			int cnt = sqlSession.update("boardTest.updateBoard", bv);

			if(cnt > 0) {
				System.out.println("update 작업 성공.");
			} else {
				System.out.println("update 작업 실패 !!!");
			}
			sqlSession.commit(); // 커밋
			
		} catch (PersistenceException ex) {
			sqlSession.rollback();
			
			throw new RuntimeException("수정 중 예외 발생!!!", ex);
		} finally {
			sqlSession.close();
		}
		
		//2-3 delete 연습
		System.out.println("delete 작업시작...");
		
		sqlSession = sqlSessionFactory.openSession();
		
		try {
			
			int cnt = sqlSession.delete("board.deleteBoard","d001");
			
			if(cnt>0) {
				System.out.println("delete 작업 성공");
				sqlSession.commit();
			} else {
				System.out.println("delete 작업 실패 !!!");
			}
			
		} catch (Exception ex) {
			sqlSession.rollback();
			
			throw new RuntimeException("삭제 중 예외발생!!", ex);
		} finally {
			sqlSession.close();
		}
		
		
		// 2-4. select 연습
		// 응답의 결과가 여러개일 경우...(리스트로 받고 싶은 경우)
		System.out.println("select연습(결과가 여러개인 경우...)....");
		
		sqlSession = sqlSessionFactory.openSession();
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			boardList = sqlSession.selectList("board.selectAllBoard");
			
			if(boardList.size()==0) {
				System.out.println("조회된 게시물이 없습니다");
			} else {
				for (BoardVO bv3 : boardList) {
					System.out.println("글 번호 : " + bv3.getBoardNo());
					System.out.println("글 제목 : " + bv3.getBoardTitle());
					System.out.println("글 내용 : " + bv3.getBoardContent());
					System.out.println("글쓴이 : " + bv3.getBoardWriter());
					System.out.println("등록날짜 : " + bv3.getRegDate());
				}
				System.out.println("출력 끝...");
			}
		} finally {
			sqlSession.close();
		}
		
		
		// 2-5 응답결과가 1개일 경우...
		System.out.println("select 연습(결과가 1개인 경우...");
		
		sqlSession = sqlSessionFactory.openSession();
		
		try {
			BoardVO bv4 = (BoardVO) sqlSession.selectOne("board.selectBoard",8);
			System.out.println("글 번호 : " + bv4.getBoardNo());
			System.out.println("글 제목 : " + bv4.getBoardTitle());
			System.out.println("글 내용 : " + bv4.getBoardContent());
			System.out.println("글쓴이 : " + bv4.getBoardWriter());
			System.out.println("등록날짜 : " + bv4.getRegDate());
		} finally {
			sqlSession.close();
		}
		
		
		
	}
}

