<?php

    $readstory = $_GET['link'];

    require 'database.php';

    $stmt = $mysqli->prepare("$select story from stories order by stories");
    if (!$stmt) {
        printf("Query Prep Failed: %s\n", $mysqli->error);
        exit;
    }

    $stmt->execute();
    $stmt->bind_result($readstory);

    echo "<ul>\n";
    while ($stmt->fetch()) {
        printf("\t<li>%s</li>\n",
               htmlspecialchars($readstory)
        );
    }
    echo "</ul>\n";
    $stmt->close();
?>
