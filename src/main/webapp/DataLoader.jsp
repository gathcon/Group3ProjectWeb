<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JAX-RS Upload Form</h1>

	<form action="jaxrs/file/upload" method="post"
		enctype="multipart/form-data">

		<label for="uploadedFile">Select a file : </label><input type="file"
			name="uploadedFile" size="50" /> <input type="submit"
			value="Upload It" />
	</form>

</body>
</html>

<!-- 

<form onsubmit="location.href='jaxrs/dataLoader/upload'"
enctype="multipart/form-data" method="post">
<p>
Load in records<br>
<input type="text" name="textline" size="30">
</p>
<p>
Please specify a file<br>
<input type="file" name="uploadedFile" size="40">
</p>
<div>
<input type="submit" value="Send">
</div>
</form>

 -->