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

@WebServlet("/show-user-by-id")
public class ShowUserDetailsById extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetUserById");
		if(request.getParameter("user_id").isEmpty() || request.getParameter("user_id")=="") {
			request.setAttribute("errorMessage", "Please enter a Id");
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
			
			return;
		}
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		System.out.println(user_id);
		UserServices service = new UserServices();
		User user = service.getUserById(user_id);
		String userName = service.getUserNameById(user_id);
		List<User> users= new ArrayList<>();
		if(user!=null) {
			users.add(user);
		}
		request.setAttribute("userName", userName);
		request.setAttribute("userData", users);
		RequestDispatcher rd = request.getRequestDispatcher("showUserDetails.jsp");
		rd.forward(request, response);
	}

}
