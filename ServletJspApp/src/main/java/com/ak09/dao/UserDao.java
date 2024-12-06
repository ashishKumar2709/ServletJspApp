package com.ak09.dao;
import java.util.List;
import java.util.Map;

import com.ak09.models.User;

public interface UserDao {
    User getUserById(int userId);
    List<User> getAllUsers();
    String getUserNameById(int userId);
    int addNewUser(User user);
    List<Map<String,Object>>getAllUserIds();
    User getUserForUpdate(int userId);
    int updateUserDetails(User user);
    int deleteUserById(int id);
}