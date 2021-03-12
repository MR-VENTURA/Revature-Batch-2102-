package com.revature.services;

import java.util.Set;

import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;

public interface UserService {

		public Integer addUser(User u) throws NonUniqueUsernameException;
		public User getUserById(Integer id);
		public User getUserByUsername(String username);
		public Set<User> getEditors(Integer rank);
		public Set<User> getAll();
		public void updateUser(User u);
		public void deleteUser(User u);
}
