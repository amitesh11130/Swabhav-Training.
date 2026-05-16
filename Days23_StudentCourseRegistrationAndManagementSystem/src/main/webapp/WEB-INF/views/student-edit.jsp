<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.studentcourse.model.Student"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Student Identity Profile</title>
</head>
<body>
	<h2>Modify Student Profile Data</h2>
	<%
	if (request.getAttribute("error") != null) {
	%>
	<p style="color: red;"><%=request.getAttribute("error")%></p>
	<%
	}
	Student s = (Student) request.getAttribute("student");
	%>
	<form action="${pageContext.request.contextPath}/student/edit"
		method="post">
		<input type="hidden" name="studentId" value="<%=s.getStudentId()%>">
		Name: <input type="text" name="studentName"
			value="<%=s.getStudentName()%>" required><br>
		<br> Email: <input type="email" name="email"
			value="<%=s.getEmail()%>" required><br>
		<br> Phone: <input type="text" name="phone"
			value="<%=s.getPhone()%>" required><br>
		<br> Age: <input type="number" name="age"
			value="<%=s.getAge()%>" required><br>
		<br> City: <input type="text" name="city"
			value="<%=s.getCity()%>" required><br>
		<br>
		<button type="submit">Update Record</button>
		<a href="${pageContext.request.contextPath}/students">Cancel</a>
	</form>
</body>
</html>