package hotelMVC.dao;

import java.util.List;

import hotelMVC.vo.ReservationVO;

public interface IHotelDao {
	
	public int insert(ReservationVO hv);
	
	public boolean checkMember(String roomId);
	
	public int delete(String roomId);
	
	public List<ReservationVO> selectAll();
	
}
