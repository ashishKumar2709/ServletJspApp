package com.ak09.dao;
import java.util.List;

import com.ak09.models.User;

public interface UserDao {
    User getUserById(int userId);
    List<User> getAllUsers();
    String getUserNameById(int userId);
}