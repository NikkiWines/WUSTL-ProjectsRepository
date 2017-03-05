<?php
require 'database.php';
?>
<!DOCTYPE HTML>
<html>
<head>
  <title>Calendar</title>
  <link rel="stylesheet" type="text/css" href="calendar.css"/>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>


  <script>
  //stack overflow: http://stackoverflow.com/questions/14449118/php-ajax-login
  $(document).ready(function() {
    document.getElementById("logout").style.display="none";
    document.getElementById("addevent").style.display="none";
    document.getElementById("radios").style.display="none";
    document.getElementById("editpass").style.display="none";


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
            document.getElementById("radios").style.display="block";
            document.getElementById("editpass").style.display="block";
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
          document.getElementById("radios").style.display="none";
          document.getElementById("editpass").style.display="none";
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
    $('#editpass').submit(function(e) {
      e.preventDefault();
      $.ajax({
        type: "POST",
        url: '/resetPassword.php',
        data: $(this).serialize(),
        success: function(data)
        {
          alert(data);
        }
      });
    });

  });
  </script>

</head>
<body>
  <form method="POST"  id="login">
    <div class="input-group">
      <label>Username: </label>
      <input type="text" name="username" id="username" required/>

      <label>Password: </label>
      <input type="password" name="password" id="password" required/>
      <input type="submit" name="submit" value="Login"/>
    </div>
  </form>
  <form method="post"  id="logout">
    <label>Logout: </label>
    <input type="submit" value="Logout">
  </form>
  <form method="post"  id="add">
    <p><b> -- Edit User -- </b></p>
    <label>Username </label>
    <input type="text" name="username" required/>
    <label>Password </label>
    <input type="password" name="password" required/>
    <input type="submit" name="add" value="Add"/>
  </form>
  <form method="post"  id="delete">
    <label>Username </label>
    <input type="text" name="username" required/>
    <label>Password </label>
    <input type="password" name="password" required/>
    <input type="submit" name="delete" value="Delete"/>
  </form>
  <br>



  <form method="post"  id="addevent">
    <p><b> -- Add Event  -- </b></p>
    <label>Date </label>
    <input type="date" name="date" />
    <label>Time </label>
    <input type="time" name="time" />
    <label>Event Name </label>
    <input type="text" name="name" />
    <label>Event Description</label>
    <input type="text"  name="description"/>
    <select name="eventTag">
      <option selected value="Birthday">Birthday</option>
      <option value="School">School</option>
      <option value="To-Do">To-Do</option>
      <option value="Work">Work</option>
      <option value="Other">Other</option>
    </select>
    <br>
    <input type="submit" name="addevent" value="Add Event"/>

    <br>

      </form>
      <form  id="editpass" method="post">
        <p><b> --  Edit Password  -- </b></p>
        <label>New Password </label>
        <input type="password" name="newPassword" required/>
        <label>Confirm Password </label>
        <input type="password" name="confirmPassword" required/>
        <input type="submit" name="submit" value="Save"/>
      </form>
    <br><br>
    <form  id="radios">
    <fieldset id="eventTag">
      <input type="radio" name="check" value="Birthday" checked="checked" ><label> Birthday</label>
      <input type="radio" name="check" value="School"><label> School</label>
      <input type="radio" name="check" value="To-Do"><label> To-Do</label>
      <input type="radio" name="check" value="Work"><label> Work</label>
      <input type="radio" name="check" value="Other"><label> Other</label>
    </fieldset>


  </form>




  <br>
  <div class="container">
    <div class="heading">
      <button id="dateback">Prev</button><label id="label"></label><button id="dateforward">Next</button>
      <div class="row">
        <div class="table col-md-8">
          <table id='calLayout'>
          </table>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="eventModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Event Information</h4>
              </div>
              <div class="modal-body">
                <label>Event Name</label>
                <br>
                <input type="text" name="eventName" id="eventName">
                <br><br>
                <label>Event Date</label>
                <br>
                <input type="date" name="eventDate" id="eventDate">
                <br><br>
                <label>Event Time</label>
                <br>
                <input type="time" name="eventTime" id="eventTime">
                <br>
                <br>
                <label>Event Description</label>
                <br>
                <input type="text" name="eventDescription" id="eventDesc">
                <br>
                <br>
                <label>Event Tag</label>
                <select class="custom-select" id="eventTag">

                  <option selected value="Birthday">Birthday</option>

                  <option value="School">School</option>

                  <option value="To-Do">To-Do</option>

                  <option value="Work">Work</option>

                  <option value="Other">Other</option>

                </select>
                <br>
                <input type="hidden" name="eventId" id="eventId">
              </div>
              <div class="modal-footer">
                <input type="submit" class="btn btn-primary" data-dismiss="modal" value="Delete" id="deleteEvent">
                <input type="submit" class="btn btn-primary" data-dismiss="modal" value="Edit" id="editEvent">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>



  <script src="createCalLayout.js"></script>

</body>

</html>
