package com.revature.services;

import java.util.Set;

import com.revature.beans.StoryPitch;
import com.revature.data.DAOFactory;
import com.revature.data.PitchDAO;

public class PitchServiceImpl implements PitchService{

	private PitchDAO pitchDao;
	
	public PitchServiceImpl() {
		pitchDao = DAOFactory.getPitchDAO();
	}
	
	@Override
	public Integer addPitch(StoryPitch p) {
		return pitchDao.add(p).getId();
	}

	@Override
	public StoryPitch getPitchById(Integer id) {
		return pitchDao.getById(id);
	}

	@Override
	public Set<StoryPitch> getAvailablePitches(Integer status) {
		return pitchDao.getAvailablePitches(status);
	}

	@Override
	public void updatePitch(StoryPitch p) {
		if (getPitchById(p.getId()) != null)
		pitchDao.update(p);
	}

	@Override
	public void deletePitch(StoryPitch p) {
		if (getPitchById(p.getId()) != null)
			pitchDao.delete(p);
		//else
			//log.debug("Cat didn't exist in the database.");
	}

	@Override
	public Set<StoryPitch> getByStatus(Integer status, String genre) {
		return pitchDao.getByStatus(status, genre);
	}

	@Override
	public Set<StoryPitch> getByUserStatus(Integer status, Integer userID) {
		return pitchDao.getByUserStatus(status, userID);
	}
		
	}

