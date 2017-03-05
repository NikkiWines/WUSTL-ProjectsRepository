$(document).ready(function() {
  document.getElementById("logout").style.display="none";
  document.getElementById("addevent").style.display="none";

  //log in function
  $('#login').submit(function(e) {
    e.preventDefault();
    $.ajax({
      type: "POST",
      url: '/login.php',
      data: $(this).serialize(),
      success: function(data)
      {
        //if some success idenfier is present then do show hide.

        var mydata = JSON.parse(data);
        if (mydata.success) {

          document.getElementById("login").style.display="none";
          document.getElementById("add").style.display="none";
          document.getElementById("delete").style.display="none";
          document.getElementById("logout").style.display="block";
          document.getElementById("addevent").style.display="block";

          getEvents();
        }

      }
    });
  });
  // log out function
  $('#logout').submit(function(e) {
    e.preventDefault();
    $.ajax({
      type: "POST",
      url: '/logout.php',
      data: $(this).serialize(),
      success: function(data)
      {


        document.getElementById("login").style.display="block";
        document.getElementById("add").style.display="block";
        document.getElementById("delete").style.display="block";
        document.getElementById("logout").style.display="none";
        document.getElementById("addevent").style.display="none";
        removeEvents();

      }
    });
  });
  //add user function
  $('#add').submit(function(e) {
    e.preventDefault();
    $.ajax({
      type: "POST",
      url: '/adduser.php',
      data: $(this).serialize(),
      success: function(data)
      {
        document.getElementById("login").style.display="none";
        document.getElementById("add").style.display="none";
        document.getElementById("delete").style.display="none";
        document.getElementById("logout").style.display="block";
        document.getElementById("addevent").style.display="block";


      }
    });
  });
  //delete user function
  $('#delete').submit(function(e) {
    e.preventDefault();
    $.ajax({
      type: "POST",
      url: '/deleteuser.php',
      data: $(this).serialize(),
      success: function(data)
      {
        alert(data);
        document.getElementById("login").style.display="block";
        document.getElementById("add").style.display="block";
        document.getElementById("delete").style.display="block";
        document.getElementById("logout").style.display="none";
        document.getElementById("addevent").style.display="none";
      }
    });
  });
  // add event function
  $('#addevent').submit(function(e) {
    e.preventDefault();
    $.ajax({
      type: "POST",
      url: '/addevent.php',
      data: $(this).serialize(),
      success: function(data)
      {
        // alert(data);
        document.getElementById("login").style.display="none";
        document.getElementById("add").style.display="none";
        document.getElementById("delete").style.display="none";
        document.getElementById("logout").style.display="block";
        document.getElementById("addevent").style.display="block";
        dateforward();
        dateback();
      }
    });
  });

});

const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
const numDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];


const calLayout = document.getElementById('calLayout');
const modalEventName = document.getElementById("eventName");
const modalEventDate = document.getElementById("eventDate");
const modalEventTime = document.getElementById("eventTime");
const modalEventId = document.getElementById("eventId");
const modalEventTag = document.getElementById("eventTag");
const modalEventDesc = document.getElementById("eventDesc");


//change the months array syntax
//w3 schools: http://www.w3schools.com/jsref/jsref_getmonth.asp
var d = new Date();
var month = new Array();
month[0] = "January";
month[1] = "February";
month[2] = "March";
month[3] = "April";
month[4] = "May";
month[5] = "June";
month[6] = "July";
month[7] = "August";
month[8] = "September";
month[9] = "October";
month[10] = "November";
month[11] = "December";

//Built in javascript function Date()
//Gets all of the current date's information
var todaysDate = new Date();
var todaysMonth = todaysDate.getMonth();
var todaysYear = todaysDate.getFullYear();

function elementWithClassAndId(kind, className, id) {
  var element = document.createElement(kind);
  element.setAttribute("class", className);
  if (id !== undefined) {
    element.setAttribute("id", id);
  }
  return element;
}

//splits the calendar grid into 'cells'
function splitDay(cell) {
  var row = elementWithClassAndId("div", "row");
  var holdNumDay = elementWithClassAndId("div", "column");
  row.appendChild(holdNumDay);
  cell.appendChild(row);
  return cell;
}

//full calendar layout
function createCalLayout(numRow,numCol) {
  var header = elementWithClassAndId("tr", "week", "daysOfWeek");
  for (var day of days) {
    var singleDay = elementWithClassAndId("td", "day");
    singleDay.innerText = day;
    header.appendChild(singleDay);
  }
  calLayout.appendChild(header);

  //adding the days of the week to the calendar
  for (var i = 1; i <= numRow; i++) {
    var row = elementWithClassAndId("tr", "week", "week" + i);
    for (var j = 1; j <= numCol; j++) {
      var col = elementWithClassAndId("td", "day", "day" + ((7*(i)+j)-8));
      row.appendChild(splitDay(col));
    }
    calLayout.appendChild(row);
  }
}

//setting the date for each day
function setDate(firstDay, month1) {
  var squareNum = 1;
  var dayOfMonth = 1;
  var totalDays = numDays[month1-1];
  for (var i = 2; i < calLayout.childNodes.length; i++) {
    var week = calLayout.childNodes[i];
    for (var day of week.childNodes) {
      var row = day.childNodes[0];
      var dateSlot = row.childNodes[0];
      if (squareNum >= firstDay && dayOfMonth <= totalDays) {
        dateSlot.innerHTML = `${dayOfMonth}`;
        dateSlot.setAttribute("id", dayOfMonth);
        dayOfMonth++;
      } else {
        dateSlot.innerHTML = "";
        dateSlot.setAttribute("id", "");
      }
      squareNum++;
    }
  }
}




document.getElementById("dateback").addEventListener("click", dateback, false);

//switching back and forth between the months
function dateback(event){
  dismonth = dismonth - 1;
  showmonth = showmonth -1;
  var year = todaysYear;
  if (dismonth === 0 || dismonth == -1) {
    todaysYear = todaysYear -1;
    dismonth = 12;
    showmonth = 11;
  }

//json object to show the month
  var datestring = dismonth+"/1/"+todaysYear;
  var sdate = new Date(datestring).getDay() + 1 ;
  setDate(sdate, dismonth);
  document.getElementById("label").innerHTML = month[showmonth] + " " + todaysYear ;
  var tag = $('input[name=check]:checked').val();
  var jsonobject = {
    thisMon: dismonth ,
    thisYear: year,
    eventTag: tag
  };
  $.post('/showevent.php', jsonobject)
  .done( function (data) {
    $.each(data,function( row ) {
    });
    showevent(data);
  });
}

document.getElementById("dateforward").addEventListener("click", dateforward, false);

//moving forward a month
function dateforward(event){
  dismonth = dismonth + 1;
  showmonth = showmonth + 1;
  var year = todaysYear;
  if (dismonth == 13 ) {
    todaysYear = todaysYear +1;
    dismonth = 1;
    showmonth = 0;
  }
  var datestring = dismonth+"/1/"+todaysYear;
  var sdate = new Date(datestring).getDay() + 1 ;
  setDate(sdate, dismonth);
  document.getElementById("label").innerHTML = month[showmonth] + " " + todaysYear ;
  var tag = $('input[name=check]:checked').val();

  var jsonobject = {
    thisMon: dismonth ,
    thisYear: year,
    eventTag: tag
  };

  $.post('/showevent.php', jsonobject)
  .done( function (data) {
    $.each(data,function( row ) {
    });
    showevent(data);
  });
}

//showing the event on the calendar
function showevent (data) {
  for (var event of data) {
    var date = new Date(`${event.date} ${event.time} -500`);
    for (var j=0; j < numDays[dismonth-1]; j++) { // cycle through days
      if (date.getDate() == j) { //if the day value equals the date.day val
        var node = document.createElement("div");
        node.setAttribute("id", (event.event_id));
        node.setAttribute("class", "btn btn-default userEvent");
        node.setAttribute("data-toggle","modal");
        node.setAttribute("data-target", "#eventModal");
        node.setAttribute("data-date", date);

        //can take an event, and it should have the information from it
        //set conetent to the stuff in that button
        //use an onclick --> when it's clicked on, you can have a function that takes in 'event'
        //you have access to the object that was clicekd on

        var textnode = document.createTextNode(event.name);
        var timenode = document.createTextNode(": " + event.time);

        var tagspan = document.createElement('span');
        var descspan = document.createElement('span');
        var tagName = document.createTextNode(event.event_tag);
        var descName= document.createTextNode(event.event_description);
        tagspan.appendChild(tagName);
        descspan.appendChild(descName);

        tagspan.style.display= 'none';
        descspan.style.display= 'none';

        node.normalize();

        node.appendChild(textnode);
        node.appendChild(timenode);
        node.appendChild(tagspan);
        node.appendChild(descspan);

        document.getElementById(j).appendChild(node);
      }
    }
  }

  //on-click to parse the information from the event
  $(".userEvent").on("click", function(event){
    // console.log(event.target.childNodes[3].innerHTML);
    modalEventName.value = event.target.childNodes[0].data;
    var date = new Date(event.target.dataset.date);
    modalEventTime.value = date.toTimeString().split(" ")[0];
    modalEventDate.value = date.toISOString().split("T")[0];
    modalEventId.value = event.target.id;
    modalEventTag.value = event.target.childNodes[2].innerHTML;
    modalEventDesc.value = event.target.childNodes[3].innerHTML;
  });
}

//deleting the event
  $("#deleteEvent").on("click", function(event){
    var jsonobject = {
      eventName: modalEventName.value,
      eventDate: modalEventDate.value,
      eventTime: modalEventTime.value,
      eventId: modalEventId.value,
      eventTag: modalEventTag.value,
      eventDescription: modalEventId.value
    };

  $.post('/deleteEvent.php', jsonobject)
    .done( function (data) {
      dateforward();
      dateback();
    });
  });

//editing the event
  $("#editEvent").on("click", function(event){
    var jsonobject = {
      eventName: modalEventName.value,
      eventDate: modalEventDate.value,
      eventTime: modalEventTime.value,
      eventId: modalEventId.value,
      eventTag: modalEventTag.value,
      eventDescription: modalEventId.value
    };

  $.post('/editevent.php', jsonobject)
    .done( function (data) {
      dateforward();
      dateback();
    });
  });

//gets the events
function getEvents() {
  $.post('/showevent.php', jsonobject)
  .done( function (data) {
    $.each(data,function( row ) {
    });
    showevent(data);
  });
}
// var tag = $('input[name=check]:checked').val();
// $.post('/addTags.php', tag)
//   .done( function (data) {
//     console.log(data);
//   });
//root level
createCalLayout(6, 7);
var showmonth = d.getMonth();
document.getElementById("label").innerHTML = month[showmonth] + " " + todaysYear;
var dismonth = todaysMonth+1;
// disp. new month -- ajax backend request...
// do this through calendar.php
var datestring = dismonth+"/1/"+todaysYear;
var sdate = new Date(datestring).getDay() + 1 ; // starting day is date + 1 for weekdays
setDate(sdate, todaysMonth+1);

// events when pg loads
var tag = $('input[name=check]:checked').val();

var jsonobject = {
  thisMon: dismonth ,
  thisYear: todaysYear,
  eventTag: tag
};



$.post('/showevent.php', jsonobject)
.done( function (data) {
  $.each(data,function( row ) {
  });
  showevent(data);
});

var radios = document.forms["radios"].elements["check"];
for(var i = 0, max = radios.length; i < max; i++) {
    radios[i].onclick = function(){
      dateforward();
      dateback();
    };
}
//removing events
function removeEvents() {
  var divsToHide = document.getElementsByClassName("userEvent"); //divsToHide is an array
  for(var i = 0; i < divsToHide.length; i++){
    divsToHide[i].style.visibility = "hidden"; // or
    divsToHide[i].style.display = "none"; // depending on what you're doing
  }
}