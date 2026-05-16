<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.studentcourse.model.Student"%>
<!DOCTYPE html>
<html>
<head>
<title>Students Registration Metrics</title>
</head>
<body>
	<h2>Student Registry</h2>
	<a href="${pageContext.request.contextPath}/dashboard">Back to
		Dashboard</a> |
	<a href="${pageContext.request.contextPath}/student/add">Add New
		Student</a>
	<hr />

	<%
	if (request.getAttribute("errorMessage") != null) {
	%>
	<div
		style="background-color: #f8d7da; color: #721c24; padding: 10px; margin-bottom: 15px; border: 1px solid #f5c6cb; border-radius: 4px;">
		<strong>Error:</strong>
		<%=request.getAttribute("errorMessage")%>
	</div>
	<%
	}
	%>

	<table border="1" cellpadding="5">
		<tr>
			<th>Student ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Age</th>
			<th>City</th>
			<th>Actions</th>
		</tr>
		<%
		List<Student> list = (List<Student>) request.getAttribute("studentList");
		if (list != null) {
			for (Student s : list) {
		%>
		<tr>
			<td><%=s.getStudentId()%></td>
			<td><%=s.getStudentName()%></td>
			<td><%=s.getEmail()%></td>
			<td><%=s.getPhone()%></td>
			<td><%=s.getAge()%></td>
			<td><%=s.getCity()%></td>
			<td><a
				href="${pageContext.request.contextPath}/student/edit?id=<%= s.getStudentId() %>">Edit</a>
				| <a
				href="${pageContext.request.contextPath}/student/delete?id=<%= s.getStudentId() %>"
				onclick="return confirm('Are you sure you want to delete this student record?')">Delete</a>
			</td>
		</tr>
		<%
		}
		}
		%>
	</table>
</body>
</html>