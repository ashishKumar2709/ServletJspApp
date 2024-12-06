<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<a href=logout>Logout</a>
	<h1>${name}'s Dashboard</h1>
	<p>${email}</p><br><br>
	<div>
		<form action="show-user-by-id" method="get">
			<label id="user_id">Search User By Id: </label>
			<input name="user_id" type=number placeholder="Please enter user id"/>
			<button type="submit">Submit</button><br>
		</form>
		<p>${errorMessage}</p>
	</div><br>
	<div>
		<form action="get-all-users" method="get">
			<label id="list">See Complete User List: </label>
			<button id="list" type="submit">Show User List</button>
		</form>
	</div><br><br> <br>
	<div>
		<form action="addUser.jsp">
			<label>Add User:</label>
			<button id="addUser" type="submit">Add</button><br>
		</form>
	</div><br><br>
	<div>
		<form action="get-user-ids">
			<label>Update And Delete User:</label>
			<button id="updateUser" type="submit">Go</button><br>
		</form>
	</div>
</body>
</html>