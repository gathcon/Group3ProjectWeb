function getJSON(url) {
	var JSONRequest = new XMLHttpRequest();
	JSONRequest.open("GET", url, false);
	JSONRequest.send(null);
	
	var txt = null;
	var obj = null;
	
	if (JSONRequest.responseText == "") {
		document.getElementById("error").innerHTML="Not a user";
	}
	else {
		txt = JSONRequest.responseText;
		obj = eval("(" + txt + ")");
	}
	
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

function validatePassword(JSONObject, password) {
	if (JSONObject.password == password) {
		return true;
	}
	document.getElementById("error").innerHTML="Invalid Password";
	return false;
}

function validateUser(JSONObject, username) {
	if (JSONObject.userName == username) {
		return true;
	}
	document.getElementById("error").innerHTML="Invalid Username";
	return false;
}

function goToView(JSONObject) {
	switch (JSONObject.userType) {
	case 'Network Engineer':
		document.write("Loading the page for a " + JSONObject.userType);
		window.location.replace("./userInfo.jsp");
		break;
	case 'System Administrator':
		document.write("Loading the page for a " + JSONObject.userType);
		break;
	case 'Customer Service':
		document.write("Loading the page for a " + JSONObject.userType);
		break;
	case 'other':
		document.write("Loading the page for a " + JSONObject.userType);
		break;

	default:
		document.getElementById("error").innerHTML="INVALID USER TYPE: " + JSONObject.userType;
		break;
	}
}

function login() {
	var username = document.myForm.uname.value;
	var password = document.myForm.pword.value;

	var JSONObject = getJSON("./jaxrs/users/" + username);

	if (JSONObject != null) {
		if(validatePassword(JSONObject, password)) {
			goToView(JSONObject);
		}
	}
	else {
		document.getElementById("error").innerHTML="Invalid Username";
	}

}