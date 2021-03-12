package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.UserService;

public class UserHibernateTest {
	//public static UserHibernate userDao;
	private static UserService userServ;
	private static UserDAO userDao;
	private static User user;
	
	@BeforeAll
	public static void setup() {
		userDao = new UserHibernate();
		
	}
	
	@Test
	public void checkGetByUsername() {
		User testUser = userServ.getUserByUsername("user1");
		assertEquals("user1", testUser.getUsername());
		assertEquals("pass1", testUser.getPassword());
	}
	
	@Test
	public void checkAddUSer() {
		assertThrows(NonUniqueUsernameException.class, () -> {
			User addUserTest = new User();
			userServ.addUser(addUserTest);
		});
	}
}
