<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EventID and Cause Code by IMSI</title>
<script>

function getImsis(url) {

	try {
	var JSONRequest = new XMLHttpRequest();

	JSONRequest.open("GET", url, false);
	JSONRequest.send(null);

	var txt = JSONRequest.responseText;
	var obj = eval("(" + txt + ")");

	var size = obj.length;

	var mySelector = "<select id='ImsiSelector'>";

	for (var i = 0; i < size; i++) {

	var imsi = obj[i].imsi;

	mySelector += "<option value='"+imsi+"'>" + imsi + "</option>";
	}
	mySelector += "</select>";

	document.getElementById("selectorspace").innerHTML = mySelector;


	} catch (err) {
	document.getElementById("msg").innerHTML = "No imsi's available.";
	}
	}



	function validate(imsi) {

		if ((!(/^[0-9 -:.]+$/.test(imsi)))) {
			alert("Invalid input characters.");
		} else if (imsi.length != 15) {
			alert("Invalid input length.");
		}
	}

	function getJSON(url) {

		try {
			var JSONRequest = new XMLHttpRequest();

			JSONRequest.open("GET", url, false);
			JSONRequest.send(null);

			var txt = JSONRequest.responseText;
			var obj = eval("(" + txt + ")");
			var size = obj.length;

			var myTable = "<table style=\'width: 500px\' border=\"1\">";
			myTable += "<tr><td>Number</td><td>Description</td><td><u>Event ID</u></td><td><u>Cause Code</u></td></tr>";

			for (var i = 0; i < size; i++) {

				var eventId = obj[i].id.eventId;
				var causeCode = obj[i].id.causeCode;
				var description = obj[i].description;
				var num = i+1;
				
				myTable += "<tr><td>"+ num +"</td><td>"+ description +"</td><td>" + eventId + "</td><td>" + causeCode
						+ "</td></tr>";
			}
			myTable += "</table>";

			document.getElementById("tablespace").innerHTML = myTable;

		}

		catch (err) {

			document.getElementById("tablespace").innerHTML = "No record found for Imsi submitted ";

		}
	}

	function processData(){

		var selector = document.getElementById("ImsiSelector");
		var imsiText = selector.options[selector.selectedIndex].text;
		var imsi = new Number(imsiText);
		getJSON('jaxrs/base_Datas/eventID_CauseCodeByImsi/'
		+ imsi);
		}

	
</script>
</head>
<body onload="getImsis('jaxrs/base_Datas/allImsis/')">

	 <b>Get EventId and Cause Codes associated with the given
		Imsi.</b></h>
	<hr>

	<!-- <div>
		<input id="imsiInput" name="imsi" type="text" pattern="[0-9]{15}"
			title="15 digit numbers only input required"> <label
			for="imsi">Imsi</label>

	</div>
 -->	<div>
		<form name='inputForm'>
			<fieldset>

				<label for=selectorspace>Imsi:</label>
				<span id="selectorspace"></span> <br>
				<label for="submit"></label>
				<input type="button" name="submit" onclick="processData()"
				value="Query" size="20">
			</fieldset>
		</form>
	</div>
	<!--<button type="button"
		onclick="getJSON('jaxrs/base_Datas/eventID_CauseCodeByImsi/' + ; validate(document.getElementById('imsiInput').value)">Submit
		Query</button>
		
		document.getElementById('imsiInput').value) var model = selector.options[selector.selectedIndex].text; -->

	<br>

	<br>

	<div id="tablespace"></div>


</body>
</html>
