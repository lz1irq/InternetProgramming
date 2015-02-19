$(document).ready(function() {
	"use strict"
 // events are propagated from selected element
 // up to the document (through all parents)
 // we catch the event on the first parent - document
 $(document).on("click", "[data-dismiss-sample='alert']", function() {
 	console.log("dismiss alert", arguments);
 });

 // all click events on the page
 // (except events that are not propagated)
 $(document).on("click", function() {
 	console.log("all", arguments);
 });

});