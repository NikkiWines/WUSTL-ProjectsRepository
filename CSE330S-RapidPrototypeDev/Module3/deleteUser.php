<?php
    require 'database.php';

    $form_name = $_POST['username'];
    $form_pass = $_POST['password'];

    // from wiki -- checking against database.
    $stmt= $mysqli->prepare("SELECT COUNT(*), user_id, password FROM user_information WHERE username=?"); // checks if user/userinformation already exists

    // Bind the parameter
    $stmt->bind_param('s', $username);
    $username = (string) $_POST['username'];
    $stmt->execute();

    // Bind the results
    $stmt->bind_result($count, $user_id, $password);
    $stmt->fetch();



    $stmt->close();

    // if the user/userinformation doesn't exist
    if ($count != 0 && crypt($form_pass, $password)==$password) {



    $stmt = $mysqli->prepare("DELETE FROM user_information WHERE username='$form_name'");
        if(!$stmt){
            printf("Query Prep Failed: %s\n", $mysqli->error);
            echo "Unable to delete user :( ";
            echo "<a href=\"login.php\">Return to Login Page</a>";
            exit;
        }

        $stmt->bind_param('sss', $form_name);

        $stmt->execute();

        $stmt->close();
        echo "User Deleted! ";
        echo "<br>";
        echo "<a href=\"login.php\">Return to Login Page</a>";
    }
    else {
        echo "User Doesn't Exist. Deletion Failed:  ";
        echo "<br>";
        echo "<a href=\"login.php\">Return to Login Page</a>";
    }
?>
