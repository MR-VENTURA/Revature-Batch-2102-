package com.revature.services;

import java.util.Set;

import com.revature.beans.RejectForm;

public interface RejectService {
	
	public Integer addRejectForm(RejectForm i);
	public RejectForm getRejectById(Integer id);
	public RejectForm getRejectByPitch(Integer id);
	//public Set<RejectForm> getByPitch(Integer pitchID);
	public void updateRejectForm(RejectForm i);
	public void deleteRejectForm(RejectForm i);

}
