package hotelMVC.service;

import java.util.List;

import hotelMVC.dao.HotelDaoImpl;
import hotelMVC.dao.IHotelDao;
import hotelMVC.vo.ReservationVO;

public class HotelServiceImpl implements IHotelService{

	private IHotelDao hotelDao;
	
	public HotelServiceImpl() {
		hotelDao = new HotelDaoImpl();
	}
	
	@Override
	public int reservationRoom(ReservationVO hv) {
		
		int cnt = hotelDao.insert(hv);
		
		if(cnt > 0) {
			// 메일발송기능 호출...
		}
		
		return cnt;
	}

	@Override
	public boolean checkMember(String roomId) {
		
		boolean exist = hotelDao.checkMember(roomId);
		return exist;
	}

	@Override
	public int removeReservation(String roomId) {
		
		int cnt = hotelDao.delete(roomId);
		return cnt;
	}

	@Override
	public List<ReservationVO> selectAll() {
		List<ReservationVO> reservationList = hotelDao.selectAll();
		return reservationList;
	}

}
