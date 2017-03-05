<!DOCTYPE HTML>
<html>
    <head>
        <title>News Site: Add Story</title>
    </head>
    <body>
        <h1> News Site: Add Story </h1>
        <fieldset>
        <!-- story information from users -->
        <legend>Story Information: </legend>
        <form method="post" action="homepage.php">
        <label>Story Title</label>
        <br>
        <input type="text" name="story_title"/>
        <br>
        <br>
        <label>Story Description</label>
        <br>
        <textarea name="story" rows="5" cols="40"></textarea>
        <br>
        <br>
        <label>Comment <i>(Optional)</i></label>
        <br>
        <textarea name="comment" rows="5" cols="40"></textarea>
        <br>
        <br>
        <label>Link </label>
        <br>
        <input type="url" name="link" />
        <br>
        <br>
        <input type="submit" name="submit" value="Submit"/>
      </form>
  </fieldset>
</body>
</html>
