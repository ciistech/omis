function bookingNoteAction(event) {
	$.ajax($(this).attr("href"),
	{
		type: "GET",
		data: {"bookingNoteIndex":currentBookingNoteIndex},
		success: function(bn) {
				$("#bookingNoteTableBody").append(bn);
				applyBookingNoteItemBehavior(currentBookingNoteIndex);
				currentBookingNoteIndex++;
		}, 
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
				+ errorThrown);
			$("#bookingNoteTableBody").html(jqXHR.responseText );
		}
	});	
	event.preventDefault();
}

function applyBookingNoteItemBehavior(index) {
	applyDatePicker(document.getElementById("bookingNoteItemDate"+index));
	applyRemoveLinkBehavior(index);
}

function applyRemoveLinkBehavior(index) {
	$("#removeBookingNoteLink"+index).click(function() {
		if ($("#bookingNoteOperation"+index).val() == "UPDATE") {
			$("#bookingNoteOperation"+index).val("REMOVE");
			$("#bookingNoteItemRow"+index).addClass("removeRow");
		} else if($("#bookingNoteOperation"+index).val() == "REMOVE") {
			$("#bookingNoteOperation"+index).val("UPDATE");
			$("#bookingNoteItemRow"+index).removeClass("removeRow");
		} else if($("#bookingNoteOperation"+index).val() == "CREATE") {
			$("#bookingNoteItemRow"+index).remove();
		}
		return false;
	});
}