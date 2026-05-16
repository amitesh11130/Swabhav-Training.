<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.studentcourse.model.Student, com.studentcourse.model.Course"%>
<!DOCTYPE html>
<html>
<head>
<title>Syllabus Assignment Processing Form</title>
</head>
<body>
	<h2>Process Student Course Registration</h2>
	<%
	if (request.getAttribute("error") != null) {
	%>
	<p style="color: red; font-weight: bold;"><%=request.getAttribute("error")%></p>
	<%
	}
	%>
	<form action="${pageContext.request.contextPath}/registration/add"
		method="post">
		Select Student: <select name="studentId" required>
			<%
			
			for (Student s : (List<Student>) request.getAttribute("students")) {
			%>
			<option value="<%=s.getStudentId()%>"><%=s.getStudentName()%></option>
			<%
			}
			%>
		</select><br>
		<br> Select Course: <select name="courseId" required>
			<%
			for (Course c : (List<Course>) request.getAttribute("courses")) {
			%>
			<option value="<%=c.getCourseId()%>"><%=c.getCourseName()%></option>
			<%
			}
			%>
		</select><br>
		<br> Registration Date: <input type="date"
			name="registrationDate" required><br>
		<br> Initial Status: <select name="status">
			<option value="Active">Active</option>
			<option value="Completed">Completed</option>
			<option value="Cancelled">Cancelled</option>
		</select><br>
		<br>

		<button type="submit">Finalize Registration</button>
		<a href="${pageContext.request.contextPath}/registrations">Cancel</a>
	</form>
</body>
</html>