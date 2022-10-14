package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	
	private IMemberDao memDao;
	
	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();
	}
	
	@Override
	public int registMember(MemberVO mv) {

		int cnt = memDao.insertMember(mv);
		
		if(cnt > 0) {
			// 메일발송기능 호출....
		}
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

		boolean exist = memDao.checkMember(memId);
		return exist;
	}

	@Override
	public int modifyMember(MemberVO mv) {

		int cnt = memDao.updateMember(mv);
		return cnt;
	}

	@Override
	public int removeMember(String memId) {

		int cnt = memDao.deleteMember(memId);
		return cnt;
	}

	@Override
	public List<MemberVO> selectAllMember() {

		List<MemberVO> memList = memDao.selectAllMember();
		return memList;
	}
	
	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		//서비스가 해당 다오에게 할일을 위임하는 것
		List<MemberVO> memList = memDao.searchMember(mv);
		return memList; 
	}

}
