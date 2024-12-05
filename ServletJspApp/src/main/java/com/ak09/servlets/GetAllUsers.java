package com.ak09.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ak09.models.User;
import com.ak09.service.UserServices;

@WebServlet("/get-all-users")
public class GetAllUsers extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserServices service = new UserServices();
		List<User> users= service.getAllUsers();
		System.out.println(users);
		if(users.size()==0) {
			request.setAttribute("errorMessage", "No user found");
			RequestDispatcher rd = request.getRequestDispatcher("showUserDetails.jsp");
			rd.forward(request, response);
			
			return;
		}
		request.setAttribute("userData", users);
		RequestDispatcher rd = request.getRequestDispatcher("showUserDetails.jsp");
		rd.forward(request, response);
	}

}
