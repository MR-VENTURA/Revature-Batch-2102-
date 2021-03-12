package com.revature.data;

import java.util.Set;

import com.revature.beans.RejectForm;

public interface RejectDAO extends GenericDAO<RejectForm> {
	public RejectForm add(RejectForm r);
	public RejectForm getByPitch(Integer pitchID);
	//public Set<RejectForm> getByPitch(Integer pitchID);
	public RejectForm getByAuthor(Integer userID);

}
