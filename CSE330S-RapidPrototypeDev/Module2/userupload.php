<?php
session_start();

// Get the filename and make sure it is valid
$filename = basename($_FILES['uploadedfile']['name']);

if( !preg_match('/^[\w_\.\-]+$/', $filename) ){
	echo "Invalid filename";
	exit;
}

// Get the username and make sure it is valid
$username = trim($_SESSION['username']);
if( !preg_match('/^[\w_\-]+$/', $username) ){
	echo "Invalid username";
	exit;
}

$full_path = sprintf("/srv/uploads/%s/%s", $username, $filename);

if( move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $full_path) ){
	echo "The file has been uploaded";
	echo "<br>";
	echo "<a href=\"upload.php\">Return to Uploads Page</a>";

	exit;
}else{
	echo "Upload Failure <br>";
	echo "<a href=\"upload.php\">Return to Uploads Page</a>";
	exit;
}
?>
