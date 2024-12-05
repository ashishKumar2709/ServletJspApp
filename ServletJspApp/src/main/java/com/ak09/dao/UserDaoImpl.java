package com.ak09.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ak09.db.SQLMethods;
import com.ak09.models.User;

public class UserDaoImpl implements UserDao{
	SQLMethods sql = new SQLMethods();
    @Override
    public User getUserById(int userId) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (
        		Connection connect = sql.getConnection();
				PreparedStatement pst = connect.prepareStatement(query);
            		 ) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
                                rs.getString("email"), rs.getString("role"));
            }
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	@Override
	public List<User> getAllUsers() {
		String query = "SELECT * FROM users";
		List<User> users = new ArrayList<>();
		try(
				Connection connect = sql.getConnection();
				PreparedStatement pst = connect.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				){
			while(rs.next()) {
	             users.add(new User(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
                         rs.getString("email"), rs.getString("role")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public String getUserNameById(int userId) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (
        		Connection connect = sql.getConnection();
				PreparedStatement pst = connect.prepareStatement(query);
            		 ) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            	return rs.getString("first_name") +" " +rs.getString("last_name");
            }
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return null;
	}

}
