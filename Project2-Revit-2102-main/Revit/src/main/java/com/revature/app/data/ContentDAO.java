package com.revature.app.data;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.app.beans.Content;
import com.revature.app.exceptions.ContentNotFoundException;


@Repository
public interface ContentDAO extends JpaRepository<Content, Integer>{
	public Content findByContentId(Integer id) throws ContentNotFoundException;
	
}
