<!--make it so that this manipulates the database-->
<?php

    session_start();
    require 'database.php';

    //if not isset, then fail
    if ((!isset($_POST['link'])) || (!isset($_POST['story'])) || (!isset($_POST['story_title']))) {
        echo "Adding story failed";
    }

    else {
        $link = (string) $_POST['link'];
        $story = (string) $_POST['story'];
        $story_title = (string) $_POST['story_title'];
        $user_id = (int) $_SESSION['user_id'];

        $stmt = $mysqli -> prepare("insert into stories (link, story, story_title, user_id) values ('$link', '$story', '$story_title', '$user_id')");

        if (!$stmt) {
            printf("Query Prep Failed: %s\n", $mysqli -> error);
            exit;
        }
        header("Location:/~nikkiwines/registeredHomepage.php");
        $stmt -> bind_param('sssi', $link, $story, $story_title, $user_id);
        $stmt -> execute();
        $stmt -> close();
    }
?>
