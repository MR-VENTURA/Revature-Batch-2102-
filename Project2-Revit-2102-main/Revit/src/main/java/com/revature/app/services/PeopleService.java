package com.revature.app.services;

import java.util.Set;

import com.revature.app.beans.People;
import com.revature.app.exceptions.NonUniqueUsernameException;
import com.revature.app.exceptions.PersonNotFoundException;

public interface PeopleService {
		// create
		public Integer addPeople(People p) throws NonUniqueUsernameException;
		// read
		public People findPeopleById(Integer id) throws PersonNotFoundException;
		public People findPeopleByUsername(String username) throws PersonNotFoundException;
		// update
		public void updatePeople(People p) throws PersonNotFoundException;
		// delete
		//public void deleteUser(People p);
		public Set<People> getAllPeople();
	}

