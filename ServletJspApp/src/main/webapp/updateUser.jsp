<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update User</title>
</head>
<body>
	<a href=logout>Logout</a>
	<br>
	<a href="dashboard.jsp">back</a>
	<h1>Update And Delete User</h1>
	<form action=get-user-for-update>
		<label for="updateUser">Select user to update: </label> 
		<select name="user_id">
			<option value="">User name (User ID)</option>
			<c:forEach var="user" items="${userIds}">
				<option value="${user['id']}">${user['name']}
					(${user['id']})</option>
			</c:forEach>
		</select>
		<button type="submit">Update User</button>
	</form><br>

	<c:choose>
		<c:when test="${userDetail!=null}">
			<form action="update-user-details" method="post">
				<h2>Edit and submit</h2>
				<label for="userId">User ID: </label> 
				<input name="userId" id="userId" type=number value="${userDetail['id']}" readonly /><br><br> 
				
				<label for="firstName">First name: </label> 
				<input name="firstName" id="firstName" type=text value="${userDetail['firstName']}" placeholder="Please enter first name" /><br><br> 
				
				<label for="lastName">Last name: </label> 
				<input name="lastName" id="lastName" value="${userDetail['lastName']}" type=text placeholder="Please enter last name" /><br> <br> 
				
				<label for="emailId"> Email Id: </label> 
				<input name="emailId" value="${userDetail['email']}" id="emailId" type=email placeholder="Please enter email ID" /><br> <br>
				
				<label for="password">Password: </label>
				<input name="password" value="${userDetail['password']}" id="password" type=password placeholder="Please enter password"/><br><br>
		
				<label for="userRole">User role: </label>
				<input name="userRole" value="${userDetail['role']}" id="userRole" type=text placeholder="Please enter user role"/><br><br>
		
				<button type="submit">Submit</button><br>
			</form>
		</c:when>
	</c:choose><br><br>
	<form action=delete-user-by-id method="post">
		<label for="deleteUser">Select user for delete: </label> 
		<select name="userId" id="deleteUser">
			<option value="">User name (User ID)</option>
			<c:forEach var="user" items="${userIds}">
				<option value="${user['id']}">
				${user['name']} (${user['id']})
				</option>
			</c:forEach>
		</select>
		<button type="submit">Delete User</button>
	</form>
	
	<p>${errorMessage}</p>
	<p>${message}</p>
</body>
</html>