package com.revature.data;

import java.util.Set;

import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;

public interface UserDAO extends GenericDAO<User> {
	public User add(User u) throws NonUniqueUsernameException;
	public User getByUsername(String username);
	public Set<User> getEditors(Integer rank);
	public Set<User> getAll();
}

