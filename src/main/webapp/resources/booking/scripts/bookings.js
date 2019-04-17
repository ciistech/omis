
window.onload = function() {
	actionMenus = Array.from(document.getElementsByClassName("actionMenuItem"));
	actionMenus.forEach(function(x) {
		applyActionMenu(x);
	});
};