package com.ak09.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ak09.service.UserServices;

@WebServlet("/get-user-ids")
public class GetUserIds extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserServices service = new UserServices();
		List<Map<String, Object>> userIds = service.getAllUserIds();
		System.out.println(userIds);
		request.setAttribute("userIds", userIds);
		RequestDispatcher rd = request.getRequestDispatcher("updateUser.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserServices service = new UserServices();
		List<Map<String, Object>> userIds = service.getAllUserIds();
		System.out.println(userIds);
		request.setAttribute("userIds", userIds);
		RequestDispatcher rd = request.getRequestDispatcher("updateUser.jsp");
		rd.forward(request, response);
	}
}
