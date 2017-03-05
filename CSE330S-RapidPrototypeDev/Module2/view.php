<?php
session_start();

// Get the filename
$filename = $_GET['file'];

// Get the username
$username = $_SESSION['username'];

$full_path = sprintf("/srv/uploads/%s/%s", $username, $filename);

// from http://php.net/manual/en/function.readfile.php
  header('Content-type: application');
  header('Content-Disposition: inline; filename="' . $filename . '"');
  header('Content-Transfer-Encoding: binary');
  header('Accept-Ranges: bytes');
  readfile($full_path);

?>
