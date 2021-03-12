package com.revature.data;

import java.util.Set;

import com.revature.beans.StoryPitch;

public interface PitchDAO extends GenericDAO<StoryPitch> {
	public StoryPitch add(StoryPitch p);
	public Set<StoryPitch> getAvailablePitches(Integer status);
	//public Set<StoryPitch> getByStatus(Integer status);
	public Set<StoryPitch> getByStatus(Integer status, String genre);
	public Set<StoryPitch> getByUserStatus(Integer status, Integer userID);
}

