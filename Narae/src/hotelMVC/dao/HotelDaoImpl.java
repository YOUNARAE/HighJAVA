package hotelMVC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotelMVC.util.JDBCUtil;
import hotelMVC.vo.ReservationVO;

public class HotelDaoImpl implements IHotelDao {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insert(ReservationVO hv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
				
			String sql = "insert into hotel "
					   + "(ROOM_ID, MEM_NAME, REG_DT)"
					   + " values (?,?,sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			 
			pstmt.setString(1, hv.getRoomId());
			pstmt.setString(2, hv.getMemName());
			
			//인서트라서 업데이트이고 셀렉트일 때는 executeQuery
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, pstmt, rs);
		}
		return cnt;
	}
	
	
	@Override
	public boolean checkMember(String roomId) {
		boolean exist = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt " 
			           + "from hotel " 
					   + "where room_id = ?";
			
			pstmt = conn.prepareStatement(sql); //
			pstmt.setString(1, roomId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				exist=true;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, pstmt, rs);
		}
		return exist;
	}
	
	
	@Override
	public int delete(String roomId) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from hotel where room_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, pstmt, rs);
		}
		return cnt;
	}
	
	
	@Override
	public List<ReservationVO> selectAll() {
		
		List<ReservationVO> reservationList = new ArrayList<ReservationVO>();
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from hotel";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				ReservationVO hv = new ReservationVO();
				hv.setRoomId(rs.getString("room_id"));
				hv.setMemName(rs.getString("mem_name"));
				hv.setRegDate(rs.getDate("REG_DT"));
				
				reservationList.add(hv);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, pstmt, rs);
		}
		return reservationList;
	}
	
	
}
