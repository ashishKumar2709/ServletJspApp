<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<a href=logout>Logout</a>
	<h1>${name}'s Dashboard</h1>
	<p>${email}</p>
	<div>
		<form action="get-user-by-id" method="get">
			<label id="user_id">Search User By Id: </label>
			<input name="user_id" type=number placeholder="Please enter user id"/>
			<button type="submit">Submit</button><br>
		</form>
		<p>${errorMessage}</p>
	</div><br>
	<form action="get-all-users" method="get">
		<label id="list">See Complete User List: </label>
		<button id="list" type="submit">Show User List</button><br><br> <br>
	</form>
</body>
</html>