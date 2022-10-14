package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	//나 자신을 담기 위한 멤버변수 선언
	private static IMemberDao memDao;
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MemberDaoImpl() {
		
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	} // 싱글톤 방식으로 클래스를 제공
	

	@Override
	public int insertMember(MemberVO mv) {

		int cnt = 0;
		
		try {

			conn = JDBCUtil3.getConnection(); 
			
			String sql = "insert into mymember "
					   + "(MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR, REG_DT)" 
					   + "values (?, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			
			cnt = pstmt.executeUpdate(); 
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

		boolean exist = false;

		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select count(*) as cnt "
					   + "from mymember " 
					   + "where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql); // pstmt 객체가 만들어짐
			pstmt.setString(1, memId);
			
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
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "update mymember " 
			           + "set mem_name = ?," 
					   + " mem_tel = ?,"
			           + " mem_addr = ?" 
					   + " where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> selectAllMember() {

		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				MemberVO mv = new MemberVO();
				mv.setMemId(rs.getString("mem_id"));
				mv.setMemName(rs.getString("mem_name"));
				mv.setMemTel(rs.getString("mem_tel"));
				mv.setMemAddr(rs.getString("mem_addr"));
				
				memList.add(mv);
			
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {

		List<MemberVO> memList = new ArrayList<MemberVO>(); //비어있는 리스트를 넣어준다(null은 아님), 언제든지 변경할 수 있는 array
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember where 1=1 ";
			if(mv.getMemId()!=null 
					&& !mv.getMemId().equals("")){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and MEM_ID = ? ";
			}
			if(mv.getMemName()!=null 
					&& !mv.getMemName().equals("")){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and MEM_NAME = ? ";
			}
			if(mv.getMemTel()!=null 
					&& !mv.getMemTel().equals("")){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and MEM_TEL = ? ";
			}
			if(mv.getMemAddr()!=null 
					&& !mv.getMemAddr().equals("")){ //회원아이디가 빈 값이 아니라는 뜻
				sql += " and MEM_ADDR like '%'|| ? ||'%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1; //첫번째 물음표의 위치값, if문은 조건만 활용한다
			if(mv.getMemId()!=null 
					&& !mv.getMemId().equals("")){ 
				pstmt.setString(index++, mv.getMemId());
			}
			if(mv.getMemName()!=null 
					&& !mv.getMemName().equals("")){
				pstmt.setString(index++, mv.getMemName());
			}
			if(mv.getMemTel()!=null 
					&& !mv.getMemTel().equals("")){ 
				pstmt.setString(index++, mv.getMemTel());

			}
			if(mv.getMemAddr()!=null 
					&& !mv.getMemAddr().equals("")){ 
				pstmt.setString(index++, mv.getMemAddr());

			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				MemberVO mv2 = new MemberVO(); // 파라미터가 mv라서 겹치지 말라고 mv2라고 바꿔줌
				mv2.setMemId(rs.getString("mem_id"));
				mv2.setMemName(rs.getString("mem_name"));
				mv2.setMemTel(rs.getString("mem_tel"));
				mv2.setMemAddr(rs.getString("mem_addr"));
				
				memList.add(mv2);
			
			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}
	
}
