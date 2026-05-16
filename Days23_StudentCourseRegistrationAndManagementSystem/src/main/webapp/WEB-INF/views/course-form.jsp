<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Create Course syllabus Profile</title>
</head>
<body>
	<h2>Add New Course catalog</h2>

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

	<form action="${pageContext.request.contextPath}/course/add"
		method="post">
		Course Name: <input type="text" name="courseName" required><br>
		<br> Duration: <input type="text" name="duration"
			placeholder="e.g. 3 Months" required><br>
		<br> Fees: <input type="number" step="0.01" name="fees" required><br>
		<br> Trainer Name: <input type="text" name="trainerName" required><br>
		<br>

		<button type="submit">Save Course</button>
		<a href="${pageContext.request.contextPath}/courses">Cancel</a>
	</form>
</body>
</html>