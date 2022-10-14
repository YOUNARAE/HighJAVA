package hotelMVC.vo;

import java.util.Date;

public class ReservationVO {
	
	private String roomId;
	private String memName;
	private Date regDate;
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "HotelVO [roomId=" + roomId + ", memName=" + memName + ", regDate=" + regDate + "]";
	}
}
