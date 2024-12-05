package com.ak09.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ak09.db.SQLMethods;

@WebServlet("/login")
public class Login extends HttpServlet {
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        
	        String userEmail = request.getParameter("emailId");
	        String password = request.getParameter("password");
	        System.out.println(userEmail +" "+ password);
	        SQLMethods sqlConnect = new SQLMethods(); 
	        String sql = "SELECT * FROM users WHERE email = ? AND login_password = ?";
	        try (
	                Connection connect = sqlConnect.getConnection();
	        		PreparedStatement stmt = connect.prepareStatement(sql);
	        		){            
	            stmt.setString(1, userEmail);
	            stmt.setString(2, password);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                // User found, get additional details
	                String email = rs.getString("email");
	                String role = rs.getString("role");
	                String name = rs.getString("first_name") +" "+ rs.getString("last_name");
	                System.out.println("result"+" "+ role );
	                if (!"admin".equals(role)) {
	                    request.setAttribute("errorMessage", "User is not admin");
	                    request.getRequestDispatcher("/").forward(request, response);
	                    return;
	                }
	                // Store details in session
	                HttpSession session = request.getSession();
	                session.setAttribute("email", email);  // Additional attribute
	                session.setAttribute("role", role);    // Additional attribute
	                session.setAttribute("name", name);
	                // Redirect to welcome page
	                response.sendRedirect("dashboard.jsp");
	            } else {
	                // User not found, redirect back to login with error
	                request.setAttribute("errorMessage", "Invalid username or password");
	                request.getRequestDispatcher("/").forward(request, response);
	            }
	            rs.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
