<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Support Engineer Menu</title>
</head>
<body>

<h> <b>Support Engineer Query Menu</b></h>
	<hr>
	
	<div><button type="button"
		onclick="location.href='IMSIWithFailuresQuery.jsp'">
		Find IMSIs with failures</button>
	</div>
	
	<div><button type="button"
		onclick="location.href='IMSIByFailureId.jsp'">
		Find IMSIs by failure class</button>
	</div>
	
	<div><button type="button"
		onclick="location.href='FailureCountByModel.jsp'">
		Find Failures by phone model</button>
	</div>

</body>
</html>