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

    $story_id = $_GET['story_id'];

    $stmt = $mysqli->prepare("SELECT story_title, story, link FROM stories WHERE story_id='$story_id'");

    if(!$stmt){
        printf("Query Prep Failed: %s\n", $mysqli->error);
        exit;
    }

    $stmt->bind_param('i', $story_id);
    $stmt->execute();
    $stmt->bind_result($story_title, $story, $link);

    if ($stmt -> fetch()) {
        $txt1 = htmlspecialchars($story_title);
        $txt2 = htmlspecialchars($story);
        $txt3 = htmlspecialchars($link);
    }

    $stmt->close();

    ?>

    <fieldset>
    <form method = "POST" action = "finishedStory.php">
        <p>Story Title</p>
        <input type = "text" name = "new_storytitle" value = "<?php print $txt1;?>">
        <p>Story:<br></p>
        <input type = "text" name = "new_story" value = "<?php print $txt2;?>">
        <p>Link:<br></p>
        <input type = "text" name = "new_link" value = "<?php print $txt3;?>">

        <input type = "hidden" name = "story_id" value = "<?php echo $story_id;?>">
        <input type="hidden" name="token" value="<?php echo $_SESSION['token'];?>" />
        <input type = "submit">
    </form>
    </fieldset>
      <br>
      <a href="registeredHomepage.php">Cancel and return to home</a>
</html>
