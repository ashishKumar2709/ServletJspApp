package com.ak09.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ak09.service.UserServices;

@WebServlet("/delete-user-by-id")
public class DeleteUserByID extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("userId").isEmpty() || request.getParameter("userId")=="") {
			request.setAttribute("errorMessage", "Please select a user");
			RequestDispatcher rd = request.getRequestDispatcher("get-user-ids");
			rd.forward(request, response);
			
			return;
		}
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserServices service = new UserServices();
		int res = service.deleteUserById(userId);
		if(res==0) {
			request.setAttribute("errorMessage", "Error while deleting user.");
			RequestDispatcher rd = request.getRequestDispatcher("get-user-ids");
			rd.forward(request, response);
			return;
		}
		request.setAttribute("message", "User removed successfully!");
		RequestDispatcher rd = request.getRequestDispatcher("get-user-ids");
		rd.forward(request, response);
	}

}
