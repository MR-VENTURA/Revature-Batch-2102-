package com.revature.app.data;

import org.springframework.stereotype.Repository;

import com.revature.app.beans.Status;
import com.revature.app.exceptions.StatusNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StatusDAO extends JpaRepository<Status, Integer>{
	public Status findByStatusId(Integer id) throws StatusNotFoundException;
	
}
