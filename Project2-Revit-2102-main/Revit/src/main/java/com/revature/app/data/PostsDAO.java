package com.revature.app.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.People;
import com.revature.app.beans.Posts;
import com.revature.app.exceptions.PersonNotFoundException;
import com.revature.app.exceptions.PostNotFoundException;

@Repository
public interface PostsDAO extends JpaRepository<Posts, Integer>{
	public Posts findByPostId(Integer id) throws PostNotFoundException;
	public People getPeopleByPostId(Integer id) throws PersonNotFoundException;
	@Query(value = "select * from posts where parent_post_id is null order by last_activity_date desc", nativeQuery = true)
	public Set<Posts> findAllByLatestDesc();
	@Query(value = "select * from posts where parent_post_id = ? order by last_activity_date desc", nativeQuery = true)
	public Set<Posts> findAllByParentPostId(Integer id);
}
