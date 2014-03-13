function getJSON(url) {
	var JSONRequest = new XMLHttpRequest();
	JSONRequest.open("GET", url, false);
	JSONRequest.send(null);
	var txt = JSONRequest.responseText;
	var obj = eval("(" + txt + ")");
	return obj;
}

function postJSON(url, json) {
	var JSONRequest = new XMLHttpRequest();
	JSONRequest.open("POST", url, false);
	JSONRequest.send(json);
	var txt = JSONRequest.responseText;
	document.write(txt);
	var obj = eval("(" + txt + ")");
	return obj;
}

function goTo() {
	window.location.replace("./userInfo.jsp");
}

function login() {
	var username = document.myForm.uname.value;
	var password = document.myForm.pword.value;
	var JSONObject = getJSON("./jaxrs/users/" + username);
	if (JSONObject.userType == 'Network Engineer') {
		document.write("Loading the page for a " + JSONObject.userType);
		goTo();
	}
}