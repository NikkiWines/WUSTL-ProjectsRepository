<?php
    require 'database.php';

    //if not isset, then fail
    if ((!isset($_POST['date'])) || (!isset($_POST['time'])) || (!isset($_POST['name']))) {
    }

    else {
        $date = (string) $_POST['date'];
        $s = (string) $_POST['time'];
        $time = trim(trim($s) . ":00");


        $name = (string) $_POST['name'];
        $event_tag = (string) $_POST['eventTag'];
        $event_description = (string) $_POST['description'];

        $user_id = (int) $_SESSION['user_id'];

        $stmt = $mysqli -> prepare("insert into events (date, time, name, user_id, event_tag, event_description) values ('$date', '$time', '$name', '$user_id', '$event_tag', '$event_description')");

        if (!$stmt) {
            printf("Query Prep Failed: %s\n", $mysqli -> error);
            exit;
        }
        $stmt -> bind_param('sssiss', $date, $time, $name, $user_id, $event_tag, $event_description);
        $stmt -> execute();
        $stmt -> close();
    }
 ?>
