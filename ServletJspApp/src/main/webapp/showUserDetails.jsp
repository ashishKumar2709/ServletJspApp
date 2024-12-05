<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Details</title>
</head>
<body>
<a href=logout>Logout</a><br>
<a href="dashboard.jsp">back</a>
	<c:choose>
		<c:when test="${userData.size()==0}">
			<p>No User Found!</p>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${userName!=null}">
					<h1>${userName}'s Details</h1>
				</c:when>
				<c:otherwise>
					<h1>User Detail List</h1>
				</c:otherwise>
			</c:choose>
			<table border='1'>
				<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Role</th></tr>
	 			<c:forEach var="user" items="${userData}">
			 		<tr>
						<td>${user["id"]}</td>
						<td>${user["firstName"]}</td>
						<td>${user["lastName"]}</td>
						<td>${user["email"]}</td>
						<td>${user["role"]}</td>
					</tr>
	 			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>