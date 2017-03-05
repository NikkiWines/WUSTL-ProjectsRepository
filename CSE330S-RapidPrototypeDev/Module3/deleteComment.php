<?php

    //deleting a story
    require 'database.php';

    if (isset($_POST['comment_id'])) {

        $comment_id = $_POST['comment_id'];

        echo $comment_id;

        mysqli_query($mysqli, "DELETE FROM comments WHERE comment_id='$comment_id'");
        header("Location: registeredHomepage.php");
    }

    mysqli_close($mysqli);

?>
