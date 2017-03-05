<?php

    //deleting a story
    require 'database.php';

    // if ($_SESSION['token'] != $_POST['token']) {
    //     die("Event deletion failed.");
    // }


    if (isset($_POST['eventId'])) {
      $eventId= $_POST['eventId'];

        mysqli_query($mysqli, "DELETE FROM events WHERE event_id = '$eventId'");

    }

    echo "Event Deleted!";
    mysqli_close($mysqli);

?>
