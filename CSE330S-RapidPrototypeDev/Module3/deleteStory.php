<?php

    //deleting a story
    require 'database.php';

    if (isset($_GET['story_id'])) {

        $story_id = $_GET['story_id'];



        mysqli_query($mysqli, "DELETE FROM stories WHERE story_id='$story_id'");
        header("Location: registeredHomepage.php");




    }

    mysqli_close($mysqli);

?>
