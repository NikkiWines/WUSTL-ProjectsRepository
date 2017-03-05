<!DOCTYPE HTML>
    <html>
        <head>
            <title>News Site</title>
        </head>
        <body>
            <h1>Today's News</h1>
                <form method="post" action="logout.php">
                     <input type="submit" value="Logout">
                </form>
                <form action="" method="post"></form>
        </body>
    </html>
    <?php

        require 'database.php';

        if (!isset($_SESSION['username'])) {
            echo "Error: Not logged in";
            header("Location: unregisteredHomepage.php");
        }

        else {

            //taking information from the database
            $stmt = $mysqli->prepare("select user_id, link, story, story_title, story_id from stories order by story_title");

            if (!$stmt) {
                printf("Query Prep Failed: %s\n", $mysqli->error);
                exit;
            }

            $stmt->execute();

            $stmt->bind_result($user_id, $link, $story, $story_title, $story_id);

            echo "<ul>\n";
            while ($stmt->fetch()) {
                printf("\t<li>%s %s</li>\n",
                    "<a href='$link'>Read Story</a>",
                    htmlspecialchars($story_title)
                );
                printf("\t%s\n",
                    htmlspecialchars($story)
                );

                //like button
                echo "<form method='post' action='likes.php'>";
                echo "<input type='submit' name='submit' value='Like'</a><br>";
                //display the total number of values in the like table
                echo "</form>";

                //comments button
                echo "<form method='post' action='commentsPage.php?story_id=".$story_id."'>";
                echo "<input type='submit' name='submit' value='Comments'</a><br>";
                echo "</form>";

                //edit story button
                //have some if statement here with the user_id
                if ($user_id == $_SESSION['user_id']) {
                    //edit story button
                    echo "<form method='post' action='editStory.php?story_id=".$story_id."'>";
                    echo "<input type='submit' name='submit' value='Edit'</a><br>";
                    echo "</form>";

                    //delete story button
                    echo "<form method='post' action='deleteStory.php?story_id=".$story_id."'>";
                    echo "<input type='hidden' name='story_id' value='$story_id'/>";
                    echo "<input type='submit' name='submit' value='Delete'</a><br>";
                    echo "</form>";
                }

            }

            echo "</ul>\n";

            $stmt->close();
        }
    ?>
    <html>
        <body>
             <!--adding a story--call creatingStories.html-->
             <form method="post" action="creatingStories.html">
             <input type="submit" name="submit" value="Add a new story"/>
             </form>
        </body>
    </html>
