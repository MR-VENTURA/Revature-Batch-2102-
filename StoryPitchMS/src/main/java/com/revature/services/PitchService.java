package com.revature.services;

import java.util.Set;

import com.revature.beans.StoryPitch;

public interface PitchService {
	
	public Integer addPitch(StoryPitch p);
	public StoryPitch getPitchById(Integer id);
	public Set<StoryPitch> getAvailablePitches(Integer status);
	//public Set<StoryPitch> getByStatus(Integer Status);
	public Set<StoryPitch> getByStatus(Integer status, String genre);
	public Set<StoryPitch> getByUserStatus(Integer status, Integer userID);
	public void updatePitch(StoryPitch p);
	public void deletePitch(StoryPitch p);

}
