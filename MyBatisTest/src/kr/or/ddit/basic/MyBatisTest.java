package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.management.RuntimeErrorException;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

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
		MemberVO mv = new MemberVO();
		mv.setMemId("d001");
		mv.setMemName("강감찬");
		mv.setMemTel("010-1111-1111");
		mv.setMemAddr("경남 진주시");
		
		//  2) SqlSession 객체를 이용하여 해당 쿼리문을 실행한다.
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		
//		try {
//			int cnt = sqlSession.insert("memberTest.insertMember", mv); //"네임스페이스.해당쿼리아이디"
//			
//			if(cnt > 0) {
//				System.out.println("insert 작업 성공.");
//			} else {
//				System.out.println("insert 작업 실패 !!!");
//			}
//			sqlSession.commit(); // 커밋
//			
//		} catch (PersistenceException ex) {
//			sqlSession.rollback(); //롤백
//			throw new RuntimeException(
//						"데이터 등록중 예외발생", ex);
//		} finally {
//			sqlSession.close(); //세션 종료
//		}
		
		System.out.println("---------------------------------------");
		//인서트나 업데이트나 딜리트는 트랜잭션 단위로 관리가 필요하다,ex은행사이트, 롤백,커밍을 해줘야된다
		
		// 2-2 update 작업 연습
		System.out.println("update 작업 시작....");
		
		mv = new MemberVO();
		mv.setMemId("d001");
		mv.setMemName("김서빈");
		mv.setMemTel("010-1111-1111");
		mv.setMemAddr("대전");
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			
			int cnt = sqlSession.update("memberTest.updateMember", mv);

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
		
	}
}
