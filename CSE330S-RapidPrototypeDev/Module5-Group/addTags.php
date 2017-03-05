<?php
require 'database.php';

if (isset($_POST['eventTag'])) {

  $event_tag = $_POST['eventTag'];

  $stmt = $mysqli->prepare("select user_id, date, time, name, event_id, event_tag from events where event_tag = '$event_tag'");

  if (!$stmt) {
    printf("Query Prep Failed: %s\n", $mysqli->error);
    exit;
  }

  $stmt->execute();
  $stmt->bind_result($user_id, $date, $time, $name, $event_id, $event_tag);
  $json=array();
  while($row = $stmt->fetch()) {
    $rowJSON=array();
    $rowJSON['user_id'] = $user_id;
    $rowJSON['date'] = $date;
    $rowJSON['time'] = $time;
    $rowJSON['name'] = $name;
    $rowJSON['event_id'] = $event_id;
    $rowJSON['event_tag'] = $event_tag;
    $json[]=$rowJSON;
  }
  header('Content-Type: application/json');
  echo json_encode($json);

  $stmt->close();
}

?>
