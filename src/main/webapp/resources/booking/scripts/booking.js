

window.onload = function() {
	applyBehavior();
	applyStaffSearch(document.getElementById("transportOfficerInput"), document.getElementById("transportOfficer"), null, document.getElementById("currentStaffAssignmentLink"), document.getElementById("clearStaffAssignmentLink"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	document.getElementById("bookingNoteItemsLink").addEventListener("click",bookingNoteAction);
}

function applyBehavior() {
	dates = Array.from(document.getElementsByClassName("date"));
	removeLinks = Array.from(document.getElementsByClassName("removeBooking"));
	bookingNoteInputs = Array.from(document.getElementsByClassName("bookingNoteInput"));
	
	dates.forEach(function(x) {
		applyDatePicker(x);
	});
	
	removeLinks.forEach(function(x, index) {
		applyRemoveLinkBehavior(index);
	});
	
	bookingNoteInputs.forEach(function(x, index) {
		x.addEventListener("change", function() {
			myIndex = Array.from(x.id.matchAll(/[0-9]+/g))[0];
			document.getElementById("bookingNoteOperation"+myIndex).value = "UPDATE";
		});
		
		x.addEventListener("input", function() {
			myIndex = Array.from(x.id.matchAll(/[0-9]+/g))[0];
			document.getElementById("bookingNoteOperation"+myIndex).value = "UPDATE";
		});
	});
}