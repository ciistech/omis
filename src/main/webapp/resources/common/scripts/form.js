/*
 * Form behavior.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 */

/**
 * Applied update checker to form.
 * 
 * @return form
 */
function applyFormUpdateChecker(form) {
	
	var submitted = false;
	
	var oldBeforeUnload = window.onbeforeunload;
	
	var oldSubmit = form.onsubmit;
	
	form.onsubmit = function() {
		submitted = true;
		if (oldSubmit != null) {
			oldSubmit.call();
		}
	};
	
	window.onbeforeunload = function(event) {
		if (oldBeforeUnload != null) {
			var oldResult;
			oldResult = oldBeforeUnload.call();
			if (oldResult != null) {
				return oldResult;
			}
		}
		if (!submitted) {
			return checkForFormUpdates(form);
		}
	};
};

/**
 * Disables submit buttons when clicked.
 * 
 * @param form form to which to apply disable on submit
 */
function applyDisableOnSubmit(form) {
	var elts = form.getElementsByTagName("input");
	for (var index = 0; index < elts.length; index++) {
		var elt = elts[index];
		var eltType = elt.getAttribute("type");
		if (eltType != null && eltType.toLowerCase() == "submit") {
			elt.onclick = function() {
				elt.setAttribute("disabled", "disabled");
				form.submit();
			};
		}
	}
}

/**
 * Checks whether the form was updated, displays a confirmation dialog and
 * returns the result if it was. 
 * 
 * @return whether to discard changes
 */
function checkForFormUpdates(form) {
	if (isFormUpdated(form)) {
		var resolver = new common.MessageResolver("omis.msgs.common");
		message = resolver.getMessage("discardCheckMessage");
		return message;
	}
}

/**
 * Returns whether the form was updated.
 * 
 * @return whether for was updated
 */
function isFormUpdated(form) {
	var changedElt = null;
	var inputs = form.getElementsByTagName("input");
	for (var i = 0; i < inputs.length; i++) {
		var input = inputs[i];
		if (input.type == "checkbox") {
			if (input.checked != input.defaultChecked) {
				changedElt = input;
				break;
			}
		} else if (input.type == "radio") {
			if (input.checked != input.defaultChecked) {
				changedElt = input;
				break;
			}
		} else {
			if (input.value != input.defaultValue) {
				changedElt = input;
				break;
			}
		}
	}
	if (changedElt == null) {
		var textareas = form.getElementsByTagName("textarea");
		for (var i = 0; i < textareas.length; i++) {
			var textarea = textareas[i];
			if (textarea.defaultValue != textarea.value) {
				changedElt = textarea;
				break;
			}
		}
	}
	if (changedElt == null) {
		var selects = form.getElementsByTagName("select");
		for (var i = 0; i < selects.length; i++) {
			var select = selects[i];
			var noSelectedOption = true;
			for (var j = 0; j < select.options.length; j++) {
				var option = select.options[j];
				if (option.defaultSelected) {
					noSelectedOption = false;
					break;
				}
			}
			if (noSelectedOption && select.selectedIndex == 0) {
				continue; // Skip this select
			}
			for (var j = 0; j < select.options.length; j++) {
				var option = select.options[j];
				if (option.selected != option.defaultSelected) {
					changedElt = select;
					break;
				}
			}
			if (changedElt != null) {
				break;
			}
		}
	}
	return changedElt != null;
}

/**
 * Applies date picker to a field.
 * 
 * @param elt field
 * @param options options
 */
function applyDatePicker(elt, options) {
	$(elt).datepicker($.extend({
		changeMonth: true,
		changeYear: true,
		onSelect: function() {
	         var event;
	         if(typeof window.Event == "function"){
	             event = new Event('change');
	             this.dispatchEvent(event);
	         }   else {
	             event = document.createEvent('HTMLEvents');
	             event.initEvent('change', false, false);
	             this.dispatchEvent(event);
	         }
	     }
	},options));
	$(elt).attr("autocomplete", "off");
}

/**
 * Applies time picker to a field.
 * 
 * @param elt field
 * @param options options
 */
function applyTimePicker(elt, options) {
	$(elt).ptTimeSelect();
	$(elt).attr("autocomplete", "off");
}

/**
 * Applies image preview behavior.
 * 
 * @param fieldGroupElt field group element
 * @param previewElt preview element
 * @param inputElt file input element
 */
function applyImagePreview(fieldGroupElt, previewElt, inputElt) {
	if ($(previewElt).attr("src") == null || $(previewElt).attr("src") == "") {
		$(fieldGroupElt).hide();
	}
	if (window.FileReader) {
		$(inputElt).change(function(event) {
			var reader = new FileReader();
			reader.onload = function(innerEvent) {
				$(fieldGroupElt).show();
				$(previewElt).attr("src", innerEvent.target.result);
			};
			reader.readAsDataURL(event.target.files[0]);
		});
	}
}

/**
 * Applies behavior to check fields in a row for updates and to set the
 * operation of the row in accordance.
 * 
 * <p>Warning - this does not work if the form presented more than once during
 * a user operation. This might be the case when form submission is required for
 * validation.
 * 
 * @param collectionId ID of collection
 * @param index row index
 * @param operationFieldName name of operation field; typically "operation"
 * @param updateValue value used to indicate an update should be performed
 * @param fieldNames names of fields
 */
function applyEditRowUpdateCheck(collectionId, index, operationFieldName, updateValue, fieldNames) {
	var collectionItemName = collectionId + "[" + index + "]";
	for (var fieldIndex = 0; fieldIndex < fieldNames.length; fieldIndex++) {
		var fieldName = fieldNames[fieldIndex];
		var field = document.getElementById(collectionItemName + "." + fieldName);
		field.onchange = function() {
			var changed = false;
			for (var innerFieldIndex = 0; innerFieldIndex < fieldNames.length; innerFieldIndex++) {
				var innerFieldName = fieldNames[innerFieldIndex];
				var innerField = document.getElementById(collectionItemName + "." + innerFieldName);
				if (innerField.value != innerField.defaultValue) {
					changed = true;
					break;
				}
			}
			var operation = document.getElementById(collectionItemName + "." + operationFieldName);
			if (changed) {
				operation.value = updateValue;
			} else {
				operation.value = operation.defaultValue;
			}
		};
	}
}

/**
 * Applies autocomplete where a label is paired with a value.
 * 
 * <p>Change event (changeEvent) can be null or undefined if not needed.
 * If supplied, changeEvent function must accept a mandatory event parameter.
 * The event parameter is a JavaScript event.
 * 
 * @param queryInput query/label input
 * @param valueInput value input
 * @param url URL
 * @param changeEvent change event
 */
function applyValueLabelAutoComplete(queryInput, valueInput, url, changeEvent) {
	
	// Specify source so cache can be disabled
	$(queryInput).autocomplete({
		source: function(request, response) {
			$.ajax({
				url: url,
				dataType: "json",
				type: "GET",
				data: request,
				cache: false,
				success: function(data) {
					response(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status text " + textStatus + "; status " + jqXHR.status + "; URL: " + url);
				}
			})
		},
		select: function(event, ui) {
			queryInput.value = ui.item.label;
			valueInput.value = ui.item.value;
			return false;
		},
		focus: function(event, ui) {
			queryInput.value = ui.item.label;
			return false;
		},
		change: function(event, ui) {
			if (changeEvent != null && typeof(changeEvent) != "undefined") {
				changeEvent(event.originalEvent);
			}
		}
	});
}

/**
 * Applies form field tip help overlays.
 * 
 * @param form form to which to apply overlays
 */
function applyFormFieldTipOverlay(form) {
	var url = config.ServerConfig.getContextPath() + "/web/findFormFieldTips.json?form=" + form.getAttribute("id") + "&timestamp=" + new Date().getTime();
	var request = new XMLHttpRequest();
	request.open("GET", url, false);
	request.send(null);
	if (request.status == 200) {
		var labels = form.getElementsByTagName("label");
		var tips = eval("(" + request.responseText + ")");
		for (var labelIndex = 0; labelIndex < labels.length; labelIndex++) {
			var label = labels[labelIndex];
			if (label.form != null && label.form.getAttribute("id") == form.getAttribute("id")) {
				label.onclick = function() {
					var fieldName = this.getAttribute("for");
					var tip = tips[fieldName];
					if (tip != null && tip != "" && tip != undefined) {
						alert(this.form.getAttribute("id") + "[" + fieldName + "]: " + tip);
					}
				};
			}
		}
	} else {
		alert("Error - status: " + request.status + "; URL: " + url);
	}
}

/**
 * Applies a character counter to the end of a field in order to show the user
 * how many characters are remaining.
 * 
 * @param elt field
 * @param displayElt element to display character counter
 */
function applyTextCounter(elt, displayElt) {
	if(elt instanceof HTMLElement && displayElt instanceof HTMLElement) {
		displayElt.innerHTML = calculateRemainingCharacters(elt.maxLength, elt.value.length);
		elt.oninput = function(){
			displayElt.innerHTML = calculateRemainingCharacters(elt.maxLength, elt.value.length);
		};
	}
}

/**
 * Applies a character counter to the end of a field in order to show the user
 * how many characters are remaining. Dynamically generates the element required
 * to hold the character counter.
 * 
 * @param elt field
 */
function applyDynamicHTMLTextCounter(elt) {
	var wrapper = document.createElement('span');
	elt.parentNode.insertBefore(wrapper, elt);
	wrapper.appendChild(elt);
	var displayElt = document.createElement('span');
	displayElt.classList.add("characterCounter");
	wrapper.appendChild(displayElt);
	displayElt.innerHTML = calculateRemainingCharacters(elt.maxLength, elt.value.length);
	elt.oninput = function(){
		displayElt.innerHTML = calculateRemainingCharacters(elt.maxLength, elt.value.length);
	};
}

/**
 * Calculates the difference between maximum characters, and current characters
 * and returns that number.
 * 
 * @param maxCharacters maximum characters
 * @param currentCharacters current characters
 * @returns {String} characters remaining message
 */
function calculateRemainingCharacters(maxCharacters, currentCharacters) {
	var charactersRemaining = maxCharacters - currentCharacters;
	if (charactersRemaining == 1) {
		return charactersRemaining + ' character remaining';
	} else {
		return charactersRemaining + ' characters remaining';
	}
}