<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.studentcourse.model.Registration"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>System Matriculation Logs Registry</title>

<style type="text/css">
table {
	width: 100%;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}

th, td {
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

.status {
	color: blue;
	font-weight: bold;
}
</style>
</head>

<body>

	<h2>Active System Registrations</h2>

	<a href="${pageContext.request.contextPath}/dashboard"> Back to
		Dashboard </a> |
	<a href="${pageContext.request.contextPath}/registration/add">
		Register Student to Course </a>

	<hr />

	<table>
		<tr>
			<th>Reg ID</th>
			<th>Student Name</th>
			<th>Course Name</th>
			<th>Registration Date</th>
			<th>Status Mapping</th>
			<th>Change Status</th>
			<th>Actions</th>
		</tr>

		<%
		@SuppressWarnings("unchecked")
		List<Registration> list = (List<Registration>) request.getAttribute("registrationList");

		if (list != null && !list.isEmpty()) {
			for (Registration r : list) {
		%>

		<tr>
			<td><%=r.getRegistrationId()%></td>

			<td><%=r.getStudentName()%></td>

			<td><%=r.getCourseName()%></td>

			<td><%=r.getRegistrationDate()%></td>

			<td><span class="status"> <%=r.getStatus()%>
			</span></td>

			<td>
				<form
					action="${pageContext.request.contextPath}/registration/status"
					method="post" style="display: inline;">

					<input type="hidden" name="registrationId"
						value="<%=r.getRegistrationId()%>"> <select name="status"
						onchange="this.form.submit()">

						<option value="Active"
							<%="Active".equals(r.getStatus()) ? "selected" : ""%>>
							Active</option>

						<option value="Completed"
							<%="Completed".equals(r.getStatus()) ? "selected" : ""%>>
							Completed</option>

						<option value="Cancelled"
							<%="Cancelled".equals(r.getStatus()) ? "selected" : ""%>>
							Cancelled</option>

					</select>
				</form>
			</td>

			<td><a
				href="${pageContext.request.contextPath}/registration/delete?id=<%=r.getRegistrationId()%>"
				onclick="return confirm('Are you sure you want to delete this registration record?')">

					Delete </a></td>
		</tr>

		<%
		}
		} else {
		%>

		<tr>
			<td colspan="7" style="text-align: center;">No registrations
				found.</td>
		</tr>

		<%
		}
		%>

	</table>

</body>
</html>