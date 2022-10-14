package hotelMVC.service;

import java.util.List;

import hotelMVC.vo.ReservationVO;

public interface IHotelService {

	public int reservationRoom(ReservationVO hv);
	
	public boolean checkMember(String roomId);
	
	public int removeReservation(String roomId);
	
	public List<ReservationVO> selectAll();
	
}
