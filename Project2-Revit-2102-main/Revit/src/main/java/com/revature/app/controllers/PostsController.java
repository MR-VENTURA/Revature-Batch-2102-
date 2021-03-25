package com.revature.app.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.app.beans.People;
import com.revature.app.beans.Posts;
import com.revature.app.exceptions.PersonNotFoundException;
import com.revature.app.exceptions.PostNotFoundException;
import com.revature.app.services.PostsService;

@RestController
@CrossOrigin(origins="http://projectrevit.s3-website-us-west-1.amazonaws.com", allowCredentials="true")
@RequestMapping(path="/posts")
public class PostsController {
	private final PostsService postServ;
	
	@Autowired
	public PostsController(PostsService p) {
		this.postServ = p;
	}
	
	@GetMapping
	public ResponseEntity<Set<Posts>> getAllPosts(){
		Set<Posts> posts = postServ.findAllByLatestDesc();
		return ResponseEntity.ok(posts);
	}
	
	@PostMapping
	public ResponseEntity<Posts> addPost(@RequestBody Posts p){
		Integer id = postServ.addPosts(p);
		return getPostById(id);
	}
	
	@GetMapping(path ="/{id}")
	public ResponseEntity<Posts> getPostById(@PathVariable("id") Integer id){
		Posts p = null;
		try {
			p = postServ.findByPostId(id);
		} catch (PostNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(p != null) {
			return ResponseEntity.ok(p);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path="/comments/{id}")
    public ResponseEntity<Set<Posts>> getComments(@PathVariable("id") Integer id){
		Set<Posts> posts = new HashSet<Posts>();
		try {
			posts = postServ.findAllByParentPostIdDesc(id);
		}catch(Exception e) {
			System.out.println("no posts");
		}
		return ResponseEntity.ok(posts);
    }
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Void> updatePost(@PathVariable("id") Integer id, @RequestBody Posts p) {
		try {
			System.out.println(p + " ******************");
			postServ.updatePosts(p);
		} catch (PostNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(p != null) {
			try {
				postServ.updatePosts(p);
				return ResponseEntity.ok().build();
			} catch (PostNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path="/people/{id}")
	public ResponseEntity<People> getPeopleByPostId(@PathVariable("id") Integer id){
		People p =null;
		try {
			p= postServ.getPeopleByPostId(id);
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PostNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(p != null) {
			return ResponseEntity.ok(p);
		}
		return ResponseEntity.badRequest().build();
	}
}
