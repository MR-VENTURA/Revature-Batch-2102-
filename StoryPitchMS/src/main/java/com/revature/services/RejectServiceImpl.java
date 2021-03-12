package com.revature.services;

import java.util.Set;

import com.revature.beans.RejectForm;
import com.revature.data.DAOFactory;
import com.revature.data.RejectDAO;

public class RejectServiceImpl implements RejectService{
	
	private RejectDAO rejectDao;
	
	public RejectServiceImpl() {
		rejectDao = DAOFactory.getRejectDAO();
	}

	@Override
	public Integer addRejectForm(RejectForm i) {
		return rejectDao.add(i).getId();
	}

	@Override
	public RejectForm getRejectById(Integer id) {
		return rejectDao.getById(id);
	}

	@Override
	public RejectForm getRejectByPitch(Integer id) {
		return rejectDao.getByPitch(id);
	}

	@Override
	public void updateRejectForm(RejectForm i) {
		if (getRejectById(i.getId()) != null)
			rejectDao.update(i);
	}

	@Override
	public void deleteRejectForm(RejectForm i) {
		if (getRejectById(i.getId()) != null)
			rejectDao.delete(i);
	}

//	@Override
//	public Set<RejectForm> getByPitch(Integer pitchID) {
//		return rejectDao.getByPitch(pitchID);
//	}

}
