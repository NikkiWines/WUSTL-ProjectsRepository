<?php

//editing a story
require 'database.php';


//checking user session
if (!isset($_SESSION['user_id'])) {
  exit;
}

$newPassword= $_POST['newPassword'];
$confirmPassword= $_POST['confirmPassword'];
$user_id = $_SESSION['user_id'];

if ($newPassword != $confirmPassword) {
  echo "Your passwords don't match!";
}
else {
$newPassword= crypt($_POST['newPassword']);

$stmt = $mysqli->prepare("UPDATE user_information SET password='$newPassword' WHERE user_id='$user_id'");
if (!$stmt) {
  printf("Query Prep Failed: %s\n", $mysqli->error);
  exit;
}

echo $user_id;
$stmt->execute();


$stmt->close();
}
?>
