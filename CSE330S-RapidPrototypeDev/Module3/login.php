<?php
require 'database.php';

?>
<!DOCTYPE HTML>
<html>
<head>
<title>News Site: Login Page</title>
</head>
<body>
 <h1> News Site: Login Page </h1>
    <fieldset>
      <!-- login page for users -->
      <legend>Login Please: </legend>
        <form method="post" action="userlogin.php">

        <br>
        <label>Username </label>
        <br>
        <input type="text" name="username" required/>
        <br>
        <br>
        <label>Password </label>
        <br>
        <input type="password" name="password" required/>
        <br>
        <br>
        <input type="submit" name="submit" value="Login"/>
      </form>
  </fieldset>

  <fieldset>
    <!-- adding user region -->
    <legend>Add User: </legend>
      <form method="post" action="editUser.php">
      <br>
      <label>Username </label>
      <br>
      <input type="text" name="username" required/>
      <br>
      <br>
      <label>Password </label>
      <br>
      <input type="password" name="password" required/>
      <br>
      <br>
      <input type="submit" name="submit" value="Submit"/>
    </form>
</fieldset>
<fieldset>
    <!--deleting user region -->
    <legend>Delete User: </legend>
      <form method="post" action="deleteUser.php">
      <br>
      <label>Username </label>
      <br>
      <input type="text" name="username" required/>
      <br>
      <br>
      <label>Password </label>
      <br>
      <input type="password" name="password" required/>
      <br>
      <br>
      <input type="submit" name="submit" value="Submit"/>
    </form>
</fieldset>
<!-- can write php here -->
</body>
</html>
