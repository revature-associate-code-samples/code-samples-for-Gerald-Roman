window.onload = function () {
	loadLoginView();
	loadManagerView();
	$('#loginNav').on('click', loadLoginView);
	$('#homeNav').on('click', loadHomeView);
	$('#StatusNav').on('click', loadAuthorView);
	$('#SubmitNav').on('click', loadSubmitionView);
}
/********************************************************************************/
/* 	Loads login view and validates user input. Redirects to the correct page 
	depending on user role.*/
/********************************************************************************/

function loadLoginView() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4) {
			$('#view').html(xhr.responseText);
			$('#btnLogin').on('click', validateUser);
		}
	}
	xhr.open("GET", "login.view", true);
	xhr.send();
}
function validateUser() {
	var obj = {
		username: $('input[name=username]').val(),
		password: $('input[name=password]').val()
	}
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4) {
			$('#view').html(xhr.responseText);
		}
	}
	xhr.open("POST", "login", true);
	xhr.setRequestHeader("Content-type", "application/json");
	var toSend = JSON.stringify(obj);
	console.log(toSend);
	xhr.send(toSend);
}
/********************************************************************************/
/* 	Loads the home view for the employee. Employee can see add a reimbursement, 
	view past and current reimbursements*/
/********************************************************************************/
function loadHomeView() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			$('#view').html(xhr.responseText);
			$('#btnReimb').on('click', addReimbursement)
		}
	}
	xhr.open("GET", "employee.view", true);
	xhr.send();
}
function addReimbursement() {
	var obj = {
		amount: $('input[name=amount]').val(),
		typeId: $('input[name=subType').val(),
		description: $('input[name=reimbDescription]').val()
	};

	console.log(obj);
	var toSend = JSON.stringify(obj);

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4) {
			console.log(xhr.status);
			console.log(xhr.responseText);
			console.log(xhr.responseType);
		}
	}
	xhr.open("POST", "reimbursement", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	console.log(toSend);
	xhr.send(toSend);
}

/** ***************************************************************************** */

function loadStatusView() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			$('#view').html(xhr.responseText);

		}
	}
	xhr.open("GET", "status.view", true);
	xhr.send();
}
function loadSubmitionView() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			$('#view').html(xhr.responseText);
		}
	}
	xhr.open("GET", "status.view", true);
	xhr.send();

}
/********************************************************************************/
/*	Loads manager view if the user id is a manager. Managers can see all 
	reimbursements, accepts or decline requests. */
/********************************************************************************/
function loadManagerView() {
	console.log("before xhr");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.status)
			console.log("inside manager view js")
			$('#view').html(xhr.responseText);
			$('#allbtn').on('click', getALL);
		}
	}
	xhr.open("GET", "manager.view", true);
	xhr.send();
}
function getReimbursements() {
	console.log("inside getReimb");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.responseText);
			let reimbursements = JSON.parse(xhr.responseText);
			for (let r of reimbursements) {
				managerReimbursementList(r);
			}
		}
	}
	xhr.open("GET", "manager");
	xhr.send();
}

function appendToReimbursementsList(r) {
	var submitted = new Date(r.submitted);
	var resolver = new Date(r.resolver);

	var table = $(`<tr>
								
			<td>${r.amount}</td>
			<td>${submitted}</td>
			<td>${r.resolved}</td>
			<td>${r.description}</td>
			<td>${r.author}</td>
			<td>${resolver}</td>
			<td id = "statusId${r.id}">${r.statusId}</td>
			<td>${r.typeId}</td>
			<td>
			<button class="btn btn-primary" onclick="updateStatus(${r.id},0)">Denied</button>
			<button class="btn btn-primary" onclick="updateStatus(${r.id},2)">Aproved</button>
			</td>
			</tr>
			`)
	$('#managerReimbursementList').append(table);
}
/** ***************************************************************************** */

function loadAuthorView() {
	console.log("before xhr in status");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.status)
			console.log("inside status")
			$('#view').html(xhr.responseText);
			getAuthorReimbursements();
		}
	}
	xhr.open("GET", "status.view", true);
	xhr.send();
}
function getAuthorReimbursements() {
	console.log("inside getAuthor");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.responseText);
			let reimb = JSON.parse(xhr.responseText);
			for (let r of reimb) {
				appendAuthorReimbursementsList(r);
			}
		}
	}
	xhr.open("GET", "reimbursement");
	xhr.send();
}


function getALL() {
	console.log("inside getAuthor");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.responseText);
			let reimb = JSON.parse(xhr.responseText);
			for (let r of reimb) {
				var table = $(`<tr>							
						<td>${r.amount}</td>
						<td>${submitted}</td>
						<td>${r.resolved}</td>
						<td>${r.description}</td>
						<td>${r.author}</td>
						<td>${resolver}</td>
						<td id = "statusId${r.id}">${r.statusId}</td>
						<td>${r.typeId}</td>
						<td>${r.statusId}</td>
						</tr>
						`)
				$('#reimbursementsList').append(table);
			}
		}
	}
	xhr.open("GET", "manager");
	xhr.send();
}

function appendAuthorReimbursementsList(r) {
	var submitted = new Date(r.submitted);
	var resolver = new Date(r.resolver);
	var table = $(`<tr>							
				<td>${r.amount}</td>
				<td>${submitted}</td>
				<td>${r.resolved}</td>
				<td>${r.description}</td>
				<td>${r.author}</td>
				<td>${resolver}</td>
				<td id = "statusId${r.id}">${r.statusId}</td>
				<td>${r.typeId}</td>
				<td>${r.statusId}</td>
				</tr>
				`)
	$('#reimbursementsList').append(table);
}
/** ***************************************************************************** */
function loadReimbursements() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let reimbursements = JSON.parse(xhr.responseText);
			for (let reims of reimbursements) {
				reimburseLists(reims);
			}
		}
	}
	xhr.open("GET", "manager", true);
	xhr.send();
}

function refreshStatus() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let reimbursements = JSON.parse(xhr.responseText);
			console.log('1', xhr.responseText);
			for (let reims of reimbursements) {
				console.log(reims);
				reimburseLists(reims);
			}
			$("#reimInfo tr").remove();
			loadReimbursements();
		}
	}
	xhr.open("GET", "manager", true);
	xhr.send();

}
function reimburseLists(reims) {
	let statusId = "";
	let type = "";
	switch (reims.status_id) {
		case 0:
			status = "Denied";
			break;
		case 1:
			status = "Pending";
			break;
		case 2:
			status = "Approved";
			break;
		default:
			break;
	}
	switch (reims.type_id) {
		case 1:
			type = "Lodging";
			break;
		case 2:
			type = "Travel";
			break;
		case 3:
			type = "Food";
			break;
		case 4:
			type = "Other";
			break;
		default:
			break;
	}
	var submitted = new Date(reims.submitted);
	var resolver = new Date(reims.resolver);

	var info = $(`<tr>
			<td>${reims.amount}</td>
			<td>${submitted}</td>
			<td>${reims.description}</td>
			<td>${reims.author}</td>
			<td id = "statusId${reims.id}">${reims.statusId}</td>
			<td>${reims.typeId}</td>
			
			<td>${reims.resolved}</td>
			<td>${resolver}</td>

			<td>
			<button class="btn btn-primary" onclick="updateStatus(${reims.id},0)">Denied</button>
			<button class="btn btn-primary" onclick="updateStatus(${reims.id},2)">Aproved</button>
			</td>
			</tr>
			`)
	$('#reimInfo').append(info);
}

function updateStatus(id, statusId) {

	var stat = {
		id, statusId
	}

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {

		if (xhr.readyState == 4 && xhr.status == 200) {
			$(`statusId${id}`).html(statusId);
			refreshStatus();
		}
	}
	xhr.open("POST", "reimbursement", true);
	xhr.setRequestHeader("Content-type", "application/json");
	var toSend = JSON.stringify(stat);
	console.log(toSend);
	xhr.send(toSend);
}