package com.revature.app.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.revature.app.beans.People;
import com.revature.app.data.PeopleDAO;
import com.revature.app.exceptions.NonUniqueUsernameException;
import com.revature.app.exceptions.PersonNotFoundException;

@Service
public class PeopleServiceImpl implements PeopleService{
	private PeopleDAO peopleDao;
	
	@Autowired
	public PeopleServiceImpl(PeopleDAO pDao) {
		peopleDao = pDao;
	}

	
	@Override
	public Integer addPeople(People p) throws NonUniqueUsernameException {
		return peopleDao.save(p).getPeopleId();
	}

	@Override
	public People findPeopleById(Integer id) throws PersonNotFoundException {
		return peopleDao.getOne(id);
	}

	@Override
	public People findPeopleByUsername(String username) throws PersonNotFoundException {
		return peopleDao.findByUsername(username);
	}

	@Override
	public void updatePeople(People p) throws PersonNotFoundException {
		if (findPeopleById(p.getPeopleId()) != null)
			peopleDao.save(p);
		
	}


	@Override
	public Set<People> getAllPeople() {
		// TODO Auto-generated method stub
		List<People> peopleList = peopleDao.findAll();
		Set<People> peopleSet = new HashSet<>();
		peopleSet.addAll(peopleList);
		return peopleSet;
	}
}
