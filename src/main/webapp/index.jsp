<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web App</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="js/script.js"></script>
</head>
<body>
	<h1>Login Page</h1>
	<hr>
	<h2>User Login</h2>
	<form action="" method="post" name="myForm">
		<fieldset>
			<input type="text" name="username" size="20" maxlength="45" id="uname"><br>
			<input type="password" name="password" size="20" maxlength="45" id="pword"><br>
			<input type="button" onclick="login()" value="Login" size="20" />
			<p id="error"></p>
		</fieldset>
	</form>
</body>
</html>



