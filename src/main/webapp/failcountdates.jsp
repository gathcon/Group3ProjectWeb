<!DOCTYPE html>
<html>
<head>
<script>
function getAllImsis(url) {

	try {
		var JSONRequest = new XMLHttpRequest();

		JSONRequest.open("GET", url, false);
		JSONRequest.send(null);

		var txt = JSONRequest.responseText;
		//document.write(txt);
		var obj = eval("(" + txt + ")");

		var size = obj.length;

		var mySelector = "<select id='modelSelector'>";

		for (var i = 0; i < size; i++) {

			//var holder = obj[i];
			var model =  obj[i];
				//holder.value

			mySelector += "<option value=i>" + model+ "</option>";
		}
		mySelector += "</select>";

		document.getElementById("selectorspace").innerHTML = mySelector;


	} catch (err) {
		document.getElementById("msg").innerHTML = "No imsis available.";
	}
}

	function getJSON(url) {

		var JSONRequest = new XMLHttpRequest();

		JSONRequest.open("GET", url, false);
		JSONRequest.send(null);

		var txt = JSONRequest.responseText;
		var obj = eval("(" + txt + ")");
		
		document.getElementById("msg").innerHTML = "The number of failures for this imsi";
		document.getElementById("count").innerHTML = obj;
		
	}

	function processData(){

		var selector = document.getElementById("modelSelector");
		var imsi = selector.options[selector.selectedIndex].text;	

		getJSON('jaxrs/base_Datas/imsifailure/' 
				+ imsi + '/' 
				+ document.getElementById('startdatetime').value + '/' 
				+ document.getElementById('enddatetime').value);
	}


	
</script>
</head>

<body onload="getAllImsis('jaxrs/base_Datas/allImsiS/')">
	<b>Get the count of failures for an imsi between date range.</b>
	<br>

	<div>
		<form name='inputForm'>
			<fieldset>
					<label for=selectorspace>IMSI:</label> 
					<span id="selectorspace"></span> <br> <label for="startdatetime">Start
					DateTime:</label> <input type="datetime-local" id="startdatetime"
					name="startDate"> <label for="enddatetime">End
					DateTime:</label> <input type="datetime-local" id="enddatetime"
					name="endDate"> <label for="submit"></label> <input
					type="button" name="submit" onclick="processData()" value="Query"
					size="20">
			</fieldset>
		</form>
	</div>

	<br>
	<p id="msg"></p>
	<p id="count"></p>

</body>
</html>