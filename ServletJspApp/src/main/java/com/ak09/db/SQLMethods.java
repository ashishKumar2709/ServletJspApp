package com.ak09.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ak09.config.Config;

public class SQLMethods {
	static Config config = new Config();
	private static String url=config.getProperty("url");
	private static String userName=config.getProperty("userName");
	private static String passString=config.getProperty("passString");
	
	public Connection getConnection() throws ClassNotFoundException {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		try{
			Connection connect = DriverManager.getConnection(url, userName, passString);
			return connect;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
