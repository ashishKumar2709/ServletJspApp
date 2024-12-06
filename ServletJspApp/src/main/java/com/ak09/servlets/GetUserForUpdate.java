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


@WebServlet("/get-user-for-update")
public class GetUserForUpdate extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user_id").isEmpty() || request.getParameter("user_id")=="") {
			request.setAttribute("errorMessage", "Please select a user");
			RequestDispatcher rd = request.getRequestDispatcher("updateUser.jsp");
			rd.forward(request, response);
			return;
		}
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		System.out.println(user_id);
		UserServices service = new UserServices();
		User user = service.getUserForUpdate(user_id);
		String userName = service.getUserNameById(user_id);
		request.setAttribute("userName", userName);
		request.setAttribute("userDetail", user);
		
		RequestDispatcher rd = request.getRequestDispatcher("get-user-ids");
		rd.forward(request, response);
	}

}
