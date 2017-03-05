<!DOCTYPE HTML>
    <html>
        <head>
            <title>News Site</title>
        </head>
        <body>
            <h1>Today's News</h1>
                <form method="post" action="login.php">
                     <input type="submit" value="Login">
                </form>
        </body>
    </html>
    <?php

        session_start();
        require 'database.php';

        //taking information from the database
        $stmt = $mysqli->prepare("select link, story, story_title from stories order by story_title");

        if (!$stmt) {
            printf("Query Prep Failed: %s\n", $mysqli->error);
            exit;
        }

        $stmt->execute();

        $stmt->bind_result($link, $story, $story_title);

        echo "<ul>\n";
        while ($stmt->fetch()) {
             printf("\t<li>%s %s</li>\n",
                   "<a href='$link'>Read Story</a>",
                   //htmlspecialchars($link),
                   htmlspecialchars($story_title)
            );
            printf("\t%s\n",
                    htmlspecialchars($story)
            );

            
        }

        echo "</ul>\n";

        $stmt->close();
    ?>
