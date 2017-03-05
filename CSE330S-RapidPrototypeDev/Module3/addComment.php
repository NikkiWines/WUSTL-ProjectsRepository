<?php
    require 'database.php';
    //echo "Hello!";
    // //if not isset, then fail
    if ((!isset($_POST['comment'])) || (!isset($_POST['user_id'])) || (!isset($_POST['story_id']))) {
        //do nothing
        printf("Adding Comment Failed: %s\n", $mysqli -> error);
        exit;
    }

    else {
        $user_id= $_POST['user_id'];
        $comment = $_POST['comment'];
        $story_id= $_POST['story_id'];

        $stmt = $mysqli -> prepare("INSERT INTO comments (user_id, comment, story_id) VALUES ('$user_id', '$comment', '$story_id')");

        if (!$stmt) {
            printf("Query Prep Failed: %s\n", $mysqli -> error);
            exit;
        }

        $stmt -> bind_param('isi', $user_id, $comment, $story_id);
        $stmt -> execute();
        $stmt -> close();
        header("Location:/~nikkiwines/registeredHomepage.php");
    }
?>
