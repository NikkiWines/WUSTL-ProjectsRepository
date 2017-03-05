<?php
session_start();
$file = fopen(__DIR__ . "/../users/users.txt", "r+"); //read users.txt
$filestring = "../users/users.txt";
$username = (string)$_POST['username']; //get username from form

if( !preg_match('/^[\w_\-]+$/', $username) ){
  echo "Invalid username"; //check for valid characters
  exit;
}

// loop through users.txt and compare $name to $username to check if the user already exists.
if ($_POST["user"] == 'Add') {
  $userExists = false;

  while (!feof($file)){
    $name = fgets($file);
    if (!empty($username)){ // supplied username is not empty
      if (trim($name)==trim($username)) { // user exists
        $userExists = true;
      }
    }
  }
  if ($userExists) {
    echo "User already exists";
    echo "<br>";
    echo "<a href=\"login.html\">Return to Login Page</a>";
  }
  else {
    $user= sprintf("%s\n", $username);
    file_put_contents($filestring, $user, FILE_APPEND | LOCK_EX);
    $path= "/srv/uploads/" . $username;
    mkdir($path, 0777, true);
    echo "User Added";
    echo "<br>";
    echo "<a href=\"login.html\">Return to Login Page</a>";
    exit();
  }
}
// loop through users.txt to check if user already exists. If a match is found, delete the match.
if ($_POST["user"] == 'Delete')  {
  while (!feof($file)){
    $name = fgets($file);
    if (!empty($username)){ // Is the variable empty if not proceed
      if (trim($name)==trim($username)) { //Remove spaces and check name against entered name
        $contents = file_get_contents($filestring); // taken from stackoverflow
        $contents = str_replace($username, '', $contents);
        file_put_contents($filestring, $contents);
        $path= "/srv/uploads/" . $username;
        echo "User Deleted";
        echo "<br>";
        echo "<a href=\"login.html\">Return to Login Page</a>";
        exit();
      }
    }
  }
}

?>
