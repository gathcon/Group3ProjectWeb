<!DOCTYPE html>
<html>
<head>
<script>

	function getPhoneModels(url) {

		try {
			var JSONRequest = new XMLHttpRequest();

			JSONRequest.open("GET", url, false);
			JSONRequest.send(null);

			var txt = JSONRequest.responseText;
			var obj = eval("(" + txt + ")");

			var size = obj.length;

			var mySelector = "<select id='modelSelector'>";

			for (var i = 0; i < size; i++) {

				var model = obj[i].userEquipment.model;

				mySelector += "<option value=i>" + model + "</option>";
			}
			mySelector += "</select>";

			document.getElementById("selectorspace").innerHTML = mySelector;


		} catch (err) {
			document.getElementById("msg").innerHTML = "No phone models available.";
		}
	}

	function processData(){

		var selector = document.getElementById("modelSelector");
		var model = selector.options[selector.selectedIndex].text;	

		getJSON('jaxrs/base_Datas/FailureCountByModelAndByDateRange/' 
				+ model + '/' 
				+ document.getElementById('startdatetime').value + '/' 
				+ document.getElementById('enddatetime').value);
		}

	function getJSON(url) {

		try {
			var JSONRequest = new XMLHttpRequest();

			JSONRequest.open("GET", url, false);
			JSONRequest.send(null);

			var txt = JSONRequest.responseText;
			var obj = eval("(" + txt + ")");

			document.getElementById("msg").innerHTML = "The number of failures for this phone model";
			document.getElementById("count").innerHTML = obj;

		} catch (err) {
			document.getElementById("msg").innerHTML = "No record found for inputs submitted.";
		}
	}
</script>
</head>

<body onload="getPhoneModels('jaxrs/base_Datas/allPhoneModels/')">
	<b>Get the count of failures for a phone model between date range.</b>
	<br>

	<div>
		<form name='inputForm'>
			<fieldset>
				
				<label for=selectorspace>Phone Model:</label> 
				<span id="selectorspace"></span> <br> 
				<label for="startdatetime">Start DateTime:</label> 
				<input type="datetime-local" id="startdatetime" name="startDate">
				<label for="enddatetime">End DateTime:</label> 
				<input type="datetime-local" id="enddatetime" name="endDate"> 
				<label for="submit"></label> 
				<input type="button" name="submit" onclick="processData()"
					value="Query" size="20">
			</fieldset>
		</form>
	</div>

	<br>
	<p id="msg"></p>
	<p id="count"></p>

</body>
</html>