<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.studentcourse.model.Course"%>
<!DOCTYPE html>
<html>
<head>
<title>Academic Syllabus Track Registry</title>
</head>
<body>
	<h2>Course List Catalog</h2>
	<a href="${pageContext.request.contextPath}/dashboard">Back to Dashboard</a> |
	<a href="${pageContext.request.contextPath}/course/add">Create
		Course</a>
	<hr />
	<%
	if (request.getAttribute("error") != null) {
	%>
	<p style="color: red; font-weight: bold;"><%=request.getAttribute("error")%></p>
	<%
	}
	%>
	<table border="1" cellpadding="5">
		<tr>
			<th>Course ID</th>
			<th>Course Name</th>
			<th>Duration</th>
			<th>Fees</th>
			<th>Trainer Name</th>
			<th>Actions</th>
		</tr>
		<%
		List<Course> list = (List<Course>) request.getAttribute("courseList");
		if (list != null) {
			for (Course c : list) {
		%>
		<tr>
			<td><%=c.getCourseId()%></td>
			<td><%=c.getCourseName()%></td>
			<td><%=c.getDuration()%></td>
			<td>$<%=c.getFees()%></td>
			<td><%=c.getTrainerName()%></td>
			<td><a
				href="${pageContext.request.contextPath}/course/edit?id=<%= c.getCourseId() %>">Edit</a>
				| <a
				href="${pageContext.request.contextPath}/course/delete?id=<%= c.getCourseId() %>"
				onclick="return confirm('Are you sure you want to delete this course record?')">Delete</a>
			</td>
		</tr>
		<%
		}
		}
		%>
	</table>
</body>
</html>