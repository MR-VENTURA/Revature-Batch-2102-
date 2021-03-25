package com.revature.app.services;

import java.util.Set;

import com.revature.app.beans.People;
import com.revature.app.beans.Posts;
import com.revature.app.exceptions.PersonNotFoundException;
import com.revature.app.exceptions.PostNotFoundException;

public interface PostsService {
	
	//create
	public Integer addPosts(Posts ps);
	//read
	public Posts findByPostId(Integer id) throws PostNotFoundException;
	public People getPeopleByPostId(Integer id) throws PersonNotFoundException, PostNotFoundException;
	//update
	public void updatePosts(Posts ps)throws PostNotFoundException;
	public Set<Posts> getAllPosts();
	
	public Set<Posts> findAllByLatestDesc();
	
	public Set<Posts> findAllByParentPostIdDesc(Integer id);
}
