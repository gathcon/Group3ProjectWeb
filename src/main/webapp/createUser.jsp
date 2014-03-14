<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<h1>Create New User Page</h1>
	<hr>
	<form action="confirmation.html" method="post">
		<fieldset>
			<label for="username">Username:</label> <input type="text"
				name="username" size="20" maxlength="45"> <label
				for="password">Password:</label> <input type="password"
				name="password" size="20" maxlength="45"> <label
				for="submit"></label> <input type="submit" name="submit"
				value="Submit" size="20" />
		</fieldset>
	</form>
	<br>
	<br>
	<form action="index.html">
		<input type="submit" name="logout" value="Logout" size="20" />
	</form>
</body>
</html>