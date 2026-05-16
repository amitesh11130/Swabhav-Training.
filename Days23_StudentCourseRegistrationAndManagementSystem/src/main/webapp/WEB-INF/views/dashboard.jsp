<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>
</head>
<body>
	<h1>Management Dashboard</h1>
	<h3>
		Welcome,
		<%=session.getAttribute("loggedInUser")%>
		(Logged in at:
		<%=session.getAttribute("loginTime")%>)
	</h3>
	<hr />
	<p>
		<strong>Metrics Summary Overview:</strong>
	</p>
	<ul>
		<li>Total Registered Students: ${totalStudents}</li>
		<li>Total Managed Courses: ${totalCourses}</li>
		<li>Total System Enrollments: ${totalRegistrations}</li>
	</ul>
	<nav>
		<a href="${pageContext.request.contextPath}/students">Manage
			Students</a> | <a href="${pageContext.request.contextPath}/courses">Manage
			Courses</a> | <a href="${pageContext.request.contextPath}/registrations">Course
			Registrations</a> | <a href="${pageContext.request.contextPath}/logout">Logout</a>
	</nav>
</body>
</html>