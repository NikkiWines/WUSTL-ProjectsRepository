<?php
session_start();
// Get the filename
$filename = $_GET['file'];

// Get the username
$username = $_SESSION['username'];

//concatenate path
$full_path = sprintf("/srv/uploads/%s/%s", $username, $filename);

// download file as attachment
header('Content-type: application');
header('Content-Disposition: attachment; filename="' . $filename . '"');
header('Accept-Ranges: bytes');
readfile($full_path);

?>
