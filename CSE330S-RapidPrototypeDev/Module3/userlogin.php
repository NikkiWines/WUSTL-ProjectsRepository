<?php

	//session_start();
	require 'database.php';

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
	if ( $count == 1 && crypt($form_pass, $password)==$password){
		// Login succeeded!
		$_SESSION['user_id'] = $user_id;
		$_SESSION['username'] = $username;

		header("Location:/~nikkiwines/registeredHomepage.php"); // redirect to Reg
	}

	else {
		// Login failed; redirect back to the login screen
		echo "Login Failed: ";
		echo "<br>";
		echo "<a href=\"login.php\">Return to Login Page</a>";
	}
?>
