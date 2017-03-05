<?php
  require 'database.php';
  //session_start();
	$form_pass=$_POST['password'];
	// get information from database:
	$stmt= $mysqli->prepare("SELECT COUNT(*), user_id, password FROM user_information WHERE username=?"); // wont create user if it already exists

	// Bind the parameter
	$stmt->bind_param('s', $username);
	$username = (string) $_POST['username'];
	$stmt->execute();

	// Bind the results
	$stmt->bind_result($count, $user_id, $password);
	$stmt->fetch();


	// Compare the submitted password to the actual password hash
	if ($count == 1 && crypt($form_pass, $password)==$password){
		// Login succeeded!
		$_SESSION['user_id'] = $user_id;
		$_SESSION['username'] = $username;
    $_SESSION['token'] = substr(md5(rand()), 0, 10);
    $json=array();
    $json["success"] = true;
    echo json_encode($json);
	}
	else {
    $json=array();
    $json["success"] = false;
    echo json_encode($json);
    exit;
	}
?>
