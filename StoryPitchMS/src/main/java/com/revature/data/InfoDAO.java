package com.revature.data;

import java.util.Set;

import com.revature.beans.InfoForm;
import com.revature.beans.StoryPitch;

public interface InfoDAO extends GenericDAO<InfoForm> {
	public InfoForm add(InfoForm i);
	public InfoForm getByPitch(Integer pitchID);
	//public Set<InfoForm> getByPitch(Integer pitchID);
	public InfoForm getByAuthor(Integer userID);
}

