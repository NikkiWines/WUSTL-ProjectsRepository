<!DOCTYPE HTML>
    <html>
        <head>
            <title>News Site: Comments</title>
        </head>
        <body>
            <h1>Story Comments</h1>
            <input type="hidden" name="story_id" value = "<?php echo $_GET['story_id']; ?>">
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
            $stmt = $mysqli->prepare("select story_id, comment, comment_id, user_id from comments order by comment_id");

            if (!$stmt) {
                printf("Query Prep Failed: %s\n", $mysqli->error);
                exit;
            }

            $stmt->execute();

            $stmt->bind_result($story_idc, $comment, $comment_id, $user_idc);

            $story_id= $_GET['story_id'];
            $user_id = $_SESSION['user_id'];

            echo "<ul>\n";
            while ($stmt->fetch()) {

              if ($story_idc == $story_id) {

                printf("\t<li>%s</li>\n",
                    htmlspecialchars($comment)
                );

                printf("\t%i\n", htmlspecialchars($user_id));

                //edit story button
                //have some if statement here with the user_id
                if ($user_id == $user_idc) {
                    //edit comment button
                    echo "<form method='post' action='editComment.php?comment_id=".$comment_id."'>";
                    echo "<input type='submit' name='submit' value='Edit'</a><br>";
                    // echo "<input type='hidden' name='comment_id' value='$comment_id'/>";

                    echo "</form>";

                    //delete comment button
                    echo "<form method='post' action='deleteComment.php'>";
                    echo "<input type='hidden' name='comment_id' value='$comment_id'/>";
                    echo "<input type='submit' name='submit' value='Delete'</a><br>";
                    echo "</form>";
                }

                echo "<br><br>";

                }

            }
            echo "</ul>\n";

            $stmt->close();



            echo "</ul>\n";

            // add comments button
            echo "<form method='post' action='creatingComments.php?story_id=".$story_id."'>";
            echo "<input type='submit' name='submit' value='Add a Comment'</a><br>";
            echo "</form>";


            $stmt->close();

            echo "<br>";
            echo "<form method='post' action='registeredHomepage.php'>";
            echo "<input type='submit' name='submit' value='Return to Homepage' </a><br>";
            echo "</form>";
        }
    ?>
