<!DOCTYPE HTML>
    <html>
        <head>
            <meta charset="UTF-8">
            <title>Edit Article</title>
        </head>

    <?php

    //editing a story
    require 'database.php';

    //checking user session
    if (!isset($_SESSION['username'])) {
        session_destroy();
        header("Location: registeredHomepage.php");
        exit;
    }

    //if ($_SESSION['token'] != $_POST['token']) {
    //    die("Request forgery detected!");
    //}

    // $story_id = $_GET['story_id'];
    $comment_id = $_GET['comment_id'];


    $stmt = $mysqli->prepare("SELECT user_id, comment FROM comments WHERE comment_id='$comment_id'");

    if(!$stmt){
        printf("Query Prep Failed: %s\n", $mysqli->error);
        exit;
    }

    $stmt->bind_param('i', $comment_id);
    $stmt->execute();
    $stmt->bind_result($user_id, $comment);

    if ($stmt -> fetch()) {
        $txt1 = htmlspecialchars($comment);

    }

    $stmt->close();

    ?>

    <fieldset>
    <form method = "POST" action = "finishedComment.php">
        <p>Comment</p>
        <input type = "text" name = "new_comment" value = "<?php print $txt1;?>">
        <!-- <input type = "hidden" name = "story_id" value = "<?php echo $story_id;?>"> -->
        <input type = "hidden" name = "comment_id" value = "<?php echo $comment_id;?>">
        <input type="hidden" name="token" value="<?php echo $_SESSION['token'];?>" />
        <input type = "submit">
    </form>
    </fieldset>
      <br>
      <a href="registeredHomepage.php">Cancel and return to Home</a>
</html>
