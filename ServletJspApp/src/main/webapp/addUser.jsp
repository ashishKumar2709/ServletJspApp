<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add User</title>
</head>
<body>
	<a href=logout>Logout</a><br>
    <a href="dashboard.jsp">back</a>
	<h1>Add User</h1>
	<form action="add-user" method="post">
		<label for="firstName">First name: </label>
		<input name="firstName" id="firstName" type=text placeholder="Please enter first name"/><br><br>
		
		<label for="lastName">Last name: </label>
		<input name="lastName" id="lastName" type=text placeholder="Please enter last name"/><br><br>
		
		<label for="emailId"> Email Id: </label>
		<input name="emailId" id="emailId" type=email placeholder="Please enter email ID"/><br><br>
		
		<label for="password">Password: </label>
		<input name="password" id="password" type=password placeholder="Please enter password"/><br><br>
		
		<label for="userRole">User role: </label>
		<input name="userRole" id="userRole" type=text placeholder="Please enter user role"/><br><br>
		
		<button type="submit">Submit</button><br>
	</form>
	<p>${errorMessage}</p>
	<p>${message}</p>
</body>
</html>