const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
//change the months array syntax
//Built in javascript function Date()
function elementWithClassAndId(kind, className, id) {
//splits the calendar grid into 'cells'
function splitDay(cell) {
//full calendar layout
  //adding the days of the week to the calendar
//setting the date for each day


//json object to show the month
  var datestring = dismonth+"/1/"+todaysYear;
  var jsonobject = {
document.getElementById("dateforward").addEventListener("click", dateforward, false);
//moving forward a month
function dateforward(event){

  var jsonobject = {
  $.post('/showevent.php', jsonobject)
//showing the event on the calendar
function showevent (data) {
        //can take an event, and it should have the information from it
        var textnode = document.createTextNode(event.name);
  //on-click to parse the information from the event
//gets the events
function getEvents() {
// do this through calendar.php
var datestring = dismonth+"/1/"+todaysYear;
var sdate = new Date(datestring).getDay() + 1 ; // starting day is date + 1 for weekdays
setDate(sdate, todaysMonth+1);

// events when pg loads
  thisMon: dismonth ,
  thisYear: todaysYear,
  eventTag: tag
};


$.post('/showevent.php', jsonobject)
var radios = document.forms["radios"].elements["check"];
for(var i = 0, max = radios.length; i < max; i++) {
    radios[i].onclick = function(){
      dateforward();
      dateback();
    };
}
//removing events