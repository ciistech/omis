

window.onload = function() {
	applyDatePicker(document.getElementById("date"));
	applyStaffSearch(document.getElementById("transportOfficerInput"), document.getElementById("transportOfficer"), null, document.getElementById("currentStaffAssignmentLink"), document.getElementById("clearStaffAssignmentLink"));
}