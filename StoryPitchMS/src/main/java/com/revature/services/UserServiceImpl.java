package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.data.DAOFactory;
import com.revature.data.UserDAO;
import com.revature.exceptions.NonUniqueUsernameException;

public class UserServiceImpl implements UserService{

	private UserDAO userDao;

	//private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	public UserServiceImpl() {
		userDao = DAOFactory.getUserDAO();
	}

	@Override
	public Integer addUser(User u) throws NonUniqueUsernameException {
		return userDao.add(u).getId();
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//TODO For Console
	public User getUserByUsername(String username) {
		return userDao.getByUsername(username);
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<User> getEditors(Integer rank) {
		return userDao.getEditors(rank);
	}

	@Override
	public Set<User> getAll() {
		return userDao.getAll();
	}
}
