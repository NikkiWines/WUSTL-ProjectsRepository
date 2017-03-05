<?php
    require 'database.php';

    //if (!isset($_SESSION['token']) || $_SESSION['token'] !== $_POST['token']) {
    //die('
    //    <!DOCTYPE html>
    //    <html>
    //    <head>
    //    <title>Cannot edit. Please log in.</title>
    //    </head>
    //    <body>
    //    <h1>Cannot edit.</h1>
    //    <a href="index.html">Return to the log-in page</a>
    //    </body>
    //    </html>
    //');
    //}

    $story_id = $_POST['story_id'];

    $currentUserID = $_SESSION['user_id'];

    $stmt = $mysqli->prepare("SELECT user_id FROM stories WHERE story_id=$story_id");
    if (!$stmt) {
        printf("Query Prep Failed: %s\n", $mysqli -> error);
        exit;
    }

    $stmt->bind_param('i', $story_id);

    $stmt->execute();

    $stmt->bind_result($user_id);

    if ($stmt->fetch()){
        $txt1 = $user_id;
    }
    $stmt->close();

    if ($txt1 !== $currentUserID) {
        die('
        <!DOCTYPE html>
            <html>
                <head>
                    <title>Cannot edit</title>
                </head>
                <body>
                    <h1>Current signed-in ID does not match post ID. Cannot edit.</h1>
                    <a href="homepage.php">Return to the homepage</a>
                </body>
            </html>
        ');
    }

    $new_storytitle = $_POST['new_storytitle'];
    $new_story = $_POST['new_story'];
    $new_link = $_POST['new_link'];

    $stmt = $mysqli->prepare("UPDATE stories SET story_title='$new_storytitle', story='$new_story', link='$new_link' where story_id='$story_id'");
    if (!$stmt) {
        printf("Query Prep Failed: %s\n", $mysqli->error);
        exit;
    }

    $stmt->bind_param('ssi', $new_storytitle, $new_story, $new_link, $story_id);

    $stmt->execute();

    $stmt->close();

    header('Location: registeredHomepage.php');

?>
