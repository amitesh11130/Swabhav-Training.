<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Login</title>
</head>
<body>
	<h2>System Administration Authentication</h2>
	<%
	if (request.getAttribute("error") != null) {
	%>
	<p style="color: red;"><%=request.getAttribute("error")%></p>
	<%
	}
	%>
	<form action="${pageContext.request.contextPath}/login-action"
		method="post">
		Username: <input type="text" name="username"
			value="${rememberedUser != null ? rememberedUser : ''}"><br>
		<br> Password: <input type="password" name="password"><br>
		<br> <input type="checkbox" name="rememberMe"
			${rememberedUser != null && rememberedUser != '' ? 'checked' : ''}>
		Remember Username<br>
		<br>
		<button type="submit">Secure Login</button>
	</form>
</body>
</html>