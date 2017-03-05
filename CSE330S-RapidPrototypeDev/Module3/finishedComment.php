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

    // $story_id = $_POST['story_id'];
    $comment_id = $_POST['comment_id'];

    $currentUserID = $_SESSION['user_id'];

    echo "$comment_id";

    $stmt = $mysqli->prepare("SELECT user_id FROM comments WHERE comment_id=$comment_id");
    if (!$stmt) {
        printf("Query Prep Failed: %s\n", $mysqli -> error);
        exit;
    }

    $stmt->bind_param('i', $comment_id);

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
                    <h4>Current signed-in ID does not match post ID. Cannot edit.</h4>
                    <a href="registeredhomepage.php">Return to Home</a>
                </body>
            </html>
        ');
    }

    $new_comment = $_POST['new_comment'];


    $stmt = $mysqli->prepare("UPDATE comments SET comment='$new_comment' where comment_id='$comment_id'");
    if (!$stmt) {
        printf("Query Prep Failed: %s\n", $mysqli->error);
        exit;
    }

    $stmt->bind_param('si', $new_comment, $comment_id);

    $stmt->execute();

    $stmt->close();
    echo "Comment Updated";
    header('Location: registeredHomepage.php');

?>
