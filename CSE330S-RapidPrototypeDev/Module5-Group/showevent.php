<?php
  require 'database.php';

  $year = (int) $_POST['thisYear'];
  $month= (int) $_POST['thisMon'];
  $event_tag= (string) $_POST['eventTag'];
  $user_id= $_SESSION['user_id'];

  // echo json_encode(array('a'=>$_SESSION["username"]);
  if (!isset($_SESSION['username'])) {
      echo "Error: Not logged in";
  }

  else {

    // grab all events for the month
    // parse the data....
      //taking information from the database
      $stmt = $mysqli->prepare("select user_id, date, time, name, event_id, event_tag, event_description from events where month(date) = '$month' and year(date) = '$year' and user_id = '$user_id' and event_tag = '$event_tag'");

      if (!$stmt) {
          printf("Query Prep Failed: %s\n", $mysqli->error);
          exit;
      }

      $stmt->execute();

      $stmt->bind_result($user_id, $date, $time, $name, $event_id, $event_tag, $event_description);
      $json=array();
      while($row = $stmt->fetch()){
        $rowJSON=array();
        $rowJSON['user_id'] = $user_id;
        $rowJSON['date'] = $date;
        $rowJSON['time'] = $time;
        $rowJSON['name'] = $name;
        $rowJSON['event_id'] = $event_id;
        $rowJSON['event_tag'] = $event_tag;
        $rowJSON['event_description'] = $event_description;


        $json[]=$rowJSON;
      }
      header('Content-Type: application/json');
      echo json_encode($json);

      $stmt->close();

    }

?>
