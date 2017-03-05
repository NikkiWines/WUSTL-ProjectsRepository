<?php
//read users.txt
$file = fopen(__DIR__ . "/../users/users.txt", "r");
session_start();

//get username from form
$username = (string)$_POST['username'];
$usertrue = false;

//check for valid characters
if( !preg_match('/^[\w_\-]+$/', $username) ){
  echo "Invalid username";
  echo $username;
  exit;
}

while (!feof($file)){
  $name = fgets($file);
  if (!empty($username)){
    if (trim($name)==trim($username)) {
      $usertrue = true;
      //set session username
      $_SESSION['username']= $username;
      header("Location:/~nikkiwines/upload.php");
      exit();
    }
  }
}

if ($usertrue == false){
  echo "Sorry! Username Not Found ";
  echo "<br>";
	echo "<a href=\"login.html\">Return to Login Page</a>";
}
exit();
?>
