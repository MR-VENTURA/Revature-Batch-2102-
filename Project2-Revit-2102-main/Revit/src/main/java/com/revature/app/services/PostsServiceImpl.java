package com.revature.app.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.app.beans.People;
import com.revature.app.beans.Posts;
import com.revature.app.data.ContentDAO;
import com.revature.app.data.PeopleDAO;
import com.revature.app.data.PostsDAO;
import com.revature.app.data.StatusDAO;
import com.revature.app.exceptions.PersonNotFoundException;
import com.revature.app.exceptions.PostNotFoundException;

@Service
public class PostsServiceImpl implements PostsService {
	
	private PostsDAO postsDao;
	private PeopleDAO peopleDao;
	private ContentDAO contentDao;
	private StatusDAO statusDao;
	
	@Autowired
	public PostsServiceImpl(PostsDAO ps, PeopleDAO p, ContentDAO c, StatusDAO s) {
		postsDao = ps;
		peopleDao = p;
		contentDao = c;
		statusDao = s;
	}

	
	@Override
	public Integer addPosts(Posts ps) {
		return postsDao.save(ps).getPostId();
	}

	@Override
	public Posts findByPostId(Integer id) throws PostNotFoundException {
		return postsDao.getOne(id);
	}

	@Override
	public People getPeopleByPostId(Integer id) throws PersonNotFoundException, PostNotFoundException {
		return postsDao.getPeopleByPostId(id);
	}

	@Override
	public void updatePosts(Posts ps) throws PostNotFoundException {
		if (findByPostId(ps.getPostId()) != null)
			postsDao.save(ps);
	}


	@Override
	public Set<Posts> getAllPosts() {
		List<Posts> postList = postsDao.findAll();
		Set<Posts> postSet = new HashSet<>();
		postSet.addAll(postList);
		return postSet;
	}
	
	@Override
	public Set<Posts> findAllByLatestDesc() {
		return postsDao.findAllByLatestDesc();
	}
	
	@Override
	public Set<Posts> findAllByParentPostIdDesc(Integer id) {
		return postsDao.findAllByParentPostId(id);
	}
}
