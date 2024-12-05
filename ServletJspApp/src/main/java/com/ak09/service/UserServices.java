package com.ak09.service;

import java.util.List;

import com.ak09.dao.UserDaoImpl;
import com.ak09.models.User;

public class UserServices {
	UserDaoImpl userDao = new UserDaoImpl();
	public User getUserById(int userId) {
		 return userDao.getUserById(userId);
	 }
	 public List<User> getAllUsers(){
		 return userDao.getAllUsers();
	 }
	public String getUserNameById(int userId) {
			 return userDao.getUserNameById(userId);
	}
	 
}
