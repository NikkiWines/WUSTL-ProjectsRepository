<!DOCTYPE HTML>
<html>
    <head>
        <title>News Site: Add Comment</title>
    </head>
    <body>
        <h1> News Site: Add Comments </h1>
        <fieldset>
        <!-- story information from users -->
        <legend>Comment Information: </legend>
        <form method="post" action="addComment.php">
        <label>Comment</label>
        <br>
        <input type="text" name="comment"> <br>
        <input type="hidden" name="story_id" value = "<?php echo $_GET['story_id']; ?>">
        <input type="hidden" name="user_id" value="<?php session_start(); echo $_SESSION['user_id']; ?>" />
        <br>
        <input type="submit" name="submit" value="Submit"/>
      </form>
  </fieldset>
  <br>
  <a href="registeredHomepage.php">Return to Homepage</a>

</body>
</html>
