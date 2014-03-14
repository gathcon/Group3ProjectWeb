<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="js/script.js"></script>
</head>
<body>
	<h1>System Administrator</h1>
	<hr>
	<form action="createUser.jsp" method="post">
		<input type="submit" name="submit" value="Create a new user" size="20" />
	</form>
	<br>
	<br>
	<form action="index.html">
		<input type="submit" name="logout" value="Logout" size="20" />
	</form>
	<div id="userlist"></div>
	<script>
		methodcall();
	</script>
</body>
</html>