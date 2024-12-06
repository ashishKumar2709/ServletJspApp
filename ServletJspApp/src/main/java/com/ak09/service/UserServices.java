package com.ak09.service;

import java.util.List;
import java.util.Map;

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
	public int addNewUser(User user) {
		return userDao.addNewUser(user);
	}
	public List<Map<String,Object>>getAllUserIds(){
		return userDao.getAllUserIds();
	}
	public User getUserForUpdate(int userId) {
		 return userDao.getUserForUpdate(userId);
	 }
	public int updateUserDetails(User user) {
		return userDao.updateUserDetails(user);
	}
	public int deleteUserById(int userId) {
		return userDao.deleteUserById(userId);
	}
}
