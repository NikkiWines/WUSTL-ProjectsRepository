<?php

//editing a story
require 'database.php';
if ($_SESSION['token'] != $_POST['token']) {
  die("Request forgery detected!");
}

//checking user session
if (!isset($_POST['eventName'])) {
  // session_destroy();
  // header("Location: registeredHomepage.php");
  exit;
}

$eventName = (string)trim($_POST['eventName']);
$eventTime = (string)trim($_POST['eventTime']);
$eventDate = (string)trim($_POST['eventDate']);
$eventId = (int)trim($_POST['eventId']);
$eventTag = (string)trim($_POST['eventTag']);
$eventDescription = (string)trim($_POST['eventDescription']);





$stmt = $mysqli->prepare("UPDATE events SET name='$eventName', date='$eventDate', time='$eventTime', event_tag='$eventTag', event_description='$eventDescription' WHERE event_id='$eventId'");
if (!$stmt) {
  printf("Query Prep Failed: %s\n", $mysqli->error);
  exit;
}


$stmt->execute();

// $stmt->bind_result($eventName, $eventDate, $eventTime, $eventId);
// $json=array();
// while($row = $stmt->fetch()){
//   $rowJSON=array();
//   $rowJSON['eventName'] = $eventName;
//   $rowJSON['eventDate'] = $eventDate;
//   $rowJSON['eventTime'] = $eventTime;
//   $rowJSON['eventId'] = $eventId;
//
//   $json[]=$rowJSON;
// }
// header('Content-Type: application/json');
// echo json_encode($json);


$stmt->close();

?>
