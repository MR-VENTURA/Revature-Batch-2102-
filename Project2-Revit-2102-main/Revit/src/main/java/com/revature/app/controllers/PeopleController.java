package com.revature.app.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.app.beans.People;
import com.revature.app.beans.Posts;
import com.revature.app.exceptions.NonUniqueUsernameException;
import com.revature.app.exceptions.PersonNotFoundException;
import com.revature.app.exceptions.PostNotFoundException;
import com.revature.app.services.PeopleService;
import com.revature.app.services.PostsService;

@RestController
@CrossOrigin(origins="http://projectrevit.s3-website-us-west-1.amazonaws.com/", allowCredentials="true")
@RequestMapping(path="/user")
public class PeopleController {
	private final PeopleService peopleServ;
	private final PostsService postServ;
	private ObjectMapper om = new ObjectMapper();
	
	@Autowired
	public PeopleController(PeopleService p, PostsService po) {
		this.peopleServ = p;
		this.postServ = po;
	}
	
	@GetMapping
	public ResponseEntity<People> checkLogin(HttpSession session) {
		People loggedPeople = (People) session.getAttribute("username");
		if (loggedPeople == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(loggedPeople);
	}
	
	@PostMapping (path ="/login")
	public ResponseEntity<People> logIn(HttpSession session, @RequestBody String jsonBody) throws PersonNotFoundException {
		HashMap jb;
		try {
			jb = om.readValue(jsonBody, HashMap.class);
			People people = peopleServ.findPeopleByUsername(jb.get("username").toString());
			if (people != null) {
				if (people.getUserPass().equals(jb.get("password").toString())) {
					session.setAttribute("username", people);
					return ResponseEntity.ok(people);
				}
				else {
					return ResponseEntity.status(301).build();
				}
				//return ResponseEntity.badRequest().build();
			} else {
				return ResponseEntity.status(302).build();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}

	
	@DeleteMapping
	public ResponseEntity<Void> logOut(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok().build();
	}

	@PostMapping
	public ResponseEntity<Void> registerUser(HttpSession session, @RequestBody People people) {
		try {
			peopleServ.addPeople(people);
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Void> updateUser(HttpSession session, @PathVariable("id") Integer id, 
			@RequestBody People people) throws PersonNotFoundException {
		People loggedPeople = (People) session.getAttribute("username");
		if (loggedPeople.getAccountRoles().getRole().equals("Admin")) {
			peopleServ.updatePeople(people);
			return ResponseEntity.ok().build();
		}
		if (loggedPeople != null && loggedPeople.getPeopleId().equals(id)) {
			peopleServ.updatePeople(people);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@GetMapping(path="/banned")
	public ResponseEntity<Set<People>> getBannedPeople(){
		Set<People> people = peopleServ.getAllPeople();
		Set<People> bannedPeople = new HashSet<>();
		for(People person : people) {
			if(person.getAccountStatuses().getStatus().equals("Banned")) {
				bannedPeople.add(person);
			}
		}
		return ResponseEntity.ok(bannedPeople);
	}
	
	@PutMapping(path="/banned/{id}")
	public ResponseEntity<People> banPerson(@PathVariable("id") Integer id) throws PostNotFoundException{
		try {
			People person = peopleServ.findPeopleById(id);
			person.getAccountStatuses().setStatus("Banned");
			peopleServ.updatePeople(person);
			Set<Posts> posts = postServ.getAllPosts();
			for(Posts element : posts) {
				if(element.getAuthorId().getPeopleId() == id) {
					element.getContentId().setEnabled(false);
					postServ.updatePosts(element);
				}
			}
			return ResponseEntity.ok(person);
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}
}
