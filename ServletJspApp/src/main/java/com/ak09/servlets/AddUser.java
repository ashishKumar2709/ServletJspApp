package com.ak09.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ak09.models.User;
import com.ak09.service.UserServices;

@WebServlet("/add-user")
public class AddUser extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailId = request.getParameter("emailId");
		String password = request.getParameter("password");
		String userRole = request.getParameter("userRole");
		String message = "";
		Boolean missingField=false;
		if(firstName.isEmpty()||firstName=="") {
			message="Please enter first name.";
			missingField=true;
		}else if(lastName.isEmpty()||lastName=="") {
			message="Please enter last name.";
			missingField=true;
		}else if(emailId.isEmpty()||emailId=="") {
			message="Please enter email ID.";
			missingField=true;
		}
		if(missingField==true) {
			request.setAttribute("errorMessage", message);
			RequestDispatcher rd = request.getRequestDispatcher("addUser.jsp");
			rd.forward(request, response);
			return;
		}
		User user = new User(firstName, lastName, emailId, password, userRole);
		UserServices service = new UserServices();
		int res = service.addNewUser(user);
		if(res==0) {
			request.setAttribute("errorMessage", "Error while adding user.");
			RequestDispatcher rd = request.getRequestDispatcher("addUser.jsp");
			rd.forward(request, response);
			return;
		}
		request.setAttribute("message", "User added successfully!");
		RequestDispatcher rd = request.getRequestDispatcher("addUser.jsp");
		rd.forward(request, response);
	}

}
