<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
	<h1>Admin Login</h1>
	<form action="login" method="POST">
		Email ID: <input id="emailId" name="emailId" type="email" placeholder="Enter email ID"/><br><br>
		Password: <input id="password" name="password" type="password" placeholder="Enter password"/><br><br>
		<button type="submit">Login</button>
	</form>
	<p>${errorMessage}</p>
</body>
</html>