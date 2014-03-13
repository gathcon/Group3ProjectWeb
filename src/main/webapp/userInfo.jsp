<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Javascript</h1>
	<p>Name: <span id="j1"></span><br /> </p>
	<p>Type: <span id="j2"></span><br /></p>
	<script type="text/javascript">
		function getJSON(url) {
			
			var JSONRequest = new XMLHttpRequest();
			
			JSONRequest.open( "GET", url, false );
			JSONRequest.send( null );
			
			document.write(JSONRequest.responseText);
			
			var txt = JSONRequest.responseText;
			var obj = eval ("(" + txt + ")");
			return obj;
			
		}
		
		var JSONObject = getJSON("./jaxrs/users/Conor");
		
		document.getElementById("j1").innerHTML=JSONObject.userName;
		document.getElementById("j2").innerHTML=JSONObject.userType;
		
	</script>

</body>
</html>