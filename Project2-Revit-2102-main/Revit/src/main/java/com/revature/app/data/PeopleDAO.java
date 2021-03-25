package com.revature.app.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.People;
import com.revature.app.exceptions.PersonNotFoundException;


@Repository
public interface PeopleDAO extends JpaRepository<People, Integer>{
	public People findByUsername(String username) throws PersonNotFoundException;
	public People findByPeopleId(Integer id) throws PersonNotFoundException;
}
