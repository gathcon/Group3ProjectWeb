<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<b>Get IMSIs with failures between date range.</b></h>
	<hr>

	<form id="myDateRangeForm" action="jaxrs/base_Datas/imsifailure" title="" method="post">
		<div>
			<label for="imsi">imsi</label> 
			<input id="imsi" name="imsi" type="text"> 
				
			<label for="startDate">Start Date</label> 
				<input id="startdatetimepicker" name="startDate" type="text"
				value="YYYY-MM-DD HH:mm:ss"> 
				
			<label for="endDate">End Date</label> 
				<input id="enddatetimepicker" name="endDate" type="text"
				value="YYYY-MM-DD HH:mm:ss">
		</div>

		<div>
			<input type="submit" id="submitButton" name="submitButton"
				value="Query">
		</div>
	</form>
</body>
</html>