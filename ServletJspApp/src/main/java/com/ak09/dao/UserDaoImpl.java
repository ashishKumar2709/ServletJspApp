package com.ak09.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ak09.db.SQLMethods;
import com.ak09.models.User;
import com.ak09.util.HibernateUtil;

public class UserDaoImpl implements UserDao {
	SQLMethods sql = new SQLMethods();

	@Override
	public User getUserById(int userId) {
		String query = "SELECT * FROM users WHERE user_id = ?";
		try (Connection connect = sql.getConnection(); PreparedStatement pst = connect.prepareStatement(query);) {
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
		try (Connection connect = sql.getConnection();
				PreparedStatement pst = connect.prepareStatement(query);
				ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				users.add(new User(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email"), rs.getString("role")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public String getUserNameById(int userId) {
		String query = "SELECT * FROM users WHERE user_id = ?";
		try (Connection connect = sql.getConnection(); PreparedStatement pst = connect.prepareStatement(query);) {
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getString("first_name") + " " + rs.getString("last_name");
			}
			rs.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int addNewUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(user);
			transaction.commit();
			 HibernateUtil.logCacheStatistics();
			return 1;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> getAllUserIds() {
		String query = "SELECT * FROM users";
		List<Map<String, Object>> userIds = new ArrayList<>();
		try (Connection connect = sql.getConnection();
				PreparedStatement pst = connect.prepareStatement(query);
				ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				Map<String, Object> userId = new HashMap<>();
				String userName = rs.getString("first_name") + " " + rs.getString("last_name");
				userId.put("name", userName);
				userId.put("id", rs.getString("user_id"));
				userIds.add(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userIds;
	}

	@Override
	public User getUserForUpdate(int userId) {
		 Transaction transaction = null;
	        User user = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            user = session.get(User.class, userId); // Fetch user by ID
	            transaction.commit();
	            HibernateUtil.logCacheStatistics();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        return user;
	}

	@Override
	public int updateUserDetails(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(user);
			transaction.commit();
			 HibernateUtil.logCacheStatistics();
			return 1;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteUserById(int userId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			 // Retrieve the user to delete
			User user = session.get(User.class, userId);
	        if (user != null) {
	            // Remove the user
	        	session.remove(user);
	            System.out.println("User deleted successfully.");
	        } else {
	            System.out.println("User not found with ID: " + userId);
	        }
			transaction.commit();
			 HibernateUtil.logCacheStatistics();
			return 1;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			return 0;
		}
	}

}
