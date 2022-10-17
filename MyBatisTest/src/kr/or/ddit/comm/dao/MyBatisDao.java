package kr.or.ddit.comm.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MyBatisUtil;

public class MyBatisDao {
	
	//
	public  <T> T selectOne(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		T obj = null;
		
		try {
				
			obj = sqlSession.selectOne(statement, parameter);
			
		} catch (PersistenceException ex) {
			
			throw new RuntimeException("데이터 조회 중 에러발생!", ex);
			
		} finally {
			sqlSession.close();
		}
		return obj;
	}
	
	//
	public <T> List<T> selectList(String statement, Object parameter){
		
		List<T> list = Collections.emptyList();
		
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		try {
			
			if(parameter==null) {
				list = sqlSession.selectList(statement);
			} else {
				list = sqlSession.selectList(statement, parameter);
			}
			
		} catch (PersistenceException ex) {
			
			throw new RuntimeException("데이터 목록 조회 중 에러발생!", ex);
			
		} finally {
			sqlSession.close();
		}
		return list;
	}
	
	//
	public int insert(String statement, Object parameter) {
		
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		int cnt = 0;
		
		try {
			cnt = sqlSession.insert(statement, parameter);
			sqlSession.commit();
		} catch (PersistenceException ex) {
			sqlSession.rollback();
			throw new RuntimeException("데이터 등록 중 에러발생!", ex);
		} finally {
			sqlSession.close();
		}
		return cnt;
	}
	
	//
	public int update(String statement, Object parameter) {
		
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		int cnt = 0;
		
		try {
			cnt = sqlSession.update(statement, parameter);
			sqlSession.commit();
		} catch (PersistenceException ex) {
			sqlSession.rollback();
			throw new RuntimeException("데이터 수정 중 에러발생!", ex);
		} finally {
			sqlSession.close();
		}
		return cnt;
	}
	
	//
	public int delete(String statement, Object parameter) {
		
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		int cnt = 0;
		
		try {
			cnt = sqlSession.delete(statement, parameter);
			sqlSession.commit();
		} catch (PersistenceException ex) {
			sqlSession.rollback();
			throw new RuntimeException("데이터 삭제 중 에러발생!", ex);
		} finally {
			sqlSession.close();
		}
		return cnt;
	}
	
}
