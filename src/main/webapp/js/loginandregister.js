function getJSON(url) {
	var JSONRequest = new XMLHttpRequest();
	JSONRequest.open("GET", url, false);
	JSONRequest.send(null);

	var txt = null;
	var obj = null;

	if (JSONRequest.responseText != "") {
		txt = JSONRequest.responseText;
		obj = eval("(" + txt + ")");
	}

	return obj;
}

function validatePassword(JSONObject, password) {
	if (JSONObject.password == password) {
		return true;
	}
	document.getElementById("error").innerHTML = "Invalid Password";
	return false;
}

function goToView(JSONObject) {
	switch (JSONObject.userType) {
	case 'sysAdmin':
		document.write("Loading the page for a " + JSONObject.userType);
		window.location.replace("./viewSysAdmin.html");
		break;
	case 'networkEng':
		document.write("Loading the page for a " + JSONObject.userType);
		window.location.replace("./NetworkManagementEngineerMenu.html");
		break;
	case 'customerServiceRep':
		document.write("Loading the page for a " + JSONObject.userType);
		window.location.replace("./CustomerServiceMenu.html");
		break;
	case 'supportEng':
		document.write("Loading the page for a " + JSONObject.userType);
		window.location.replace("./SupportEngineerMenu.html");
		break;

	default:
		document.getElementById("error").innerHTML = "INVALID USER TYPE: "
				+ JSONObject.userType;
		break;
	}
}

function populateUserTable() {
	obj = getJSON("./jaxrs/users");
	if (obj != null) {
		document
				.write("<table><tr><th>Username</th><th>Password</th><th>Type</th></tr>");
		for (var i = 0; i < obj.length; i++) {
			document.write("<tr><td>" + obj[i].userName + "</td><td>"
					+ obj[i].password + "</td><td>" + obj[i].userType
					+ "</td></tr>");
		}
		document.write("</table>");
	} else {
		document.getElementById("error").innerHTML = "Invalid Username";
	}
}

function register() {
	var username = document.userForm.uname.value;
	var password = document.userForm.pword.value;
	var usertype = document.userForm.utype.value;

	if (username == "" || password == "") {
		document.getElementById("error").innerHTML = "Fields cannot be empty";
	} else {
		var request = new XMLHttpRequest();
		var url = "./jaxrs/users/" + username + "/" + password + "/" + usertype;
		request.open("GET", url, false);
		request.send(null);

		if (request.status == 200) {
			window.location.replace("./confirm.html");
		} else if (request.status == 400) {
			window.location.replace("./failed.html");
		}
	}
}

function login() {
	var username = document.myForm.uname.value;
	var password = document.myForm.pword.value;

	if (username == "" || password == "") {
		document.getElementById("error").innerHTML = "Fields cannot be empty";
	} else {
		var JSONObject = getJSON("./jaxrs/users/" + username);

		if (JSONObject != null) {
			if (validatePassword(JSONObject, password)) {
				createSession(JSONObject);
				goToView(JSONObject);
			}
		} else {
			document.getElementById("error").innerHTML = "Invalid Username";
		}
	}
}

function createSession(JSONObject) {
	sessionStorage.setItem("userName", JSONObject.userName);
	sessionStorage.setItem("userType", JSONObject.userType);
}
