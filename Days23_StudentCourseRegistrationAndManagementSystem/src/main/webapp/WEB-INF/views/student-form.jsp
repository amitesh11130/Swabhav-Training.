<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add New Student Profile</title>
</head>
<body>
	<h2>Add New Student</h2>
	<%
	if (request.getAttribute("error") != null) {
	%>
	<p style="color: red;"><%=request.getAttribute("error")%></p>
	<%
	}
	%>
	<form action="${pageContext.request.contextPath}/student/add"
		method="post">
		Name: <input type="text" name="studentName" required><br>
		<br> Email: <input type="email" name="email" required><br>
		<br> Phone: <input type="text" name="phone" required><br>
		<br> Age: <input type="number" name="age" required> (Must
		be 18+)<br>
		<br> City: <input type="text" name="city" required><br>
		<br>
		<button type="submit">Save Profile</button>
		<a href="${pageContext.request.contextPath}/students">Cancel</a>
	</form>
</body>
</html>