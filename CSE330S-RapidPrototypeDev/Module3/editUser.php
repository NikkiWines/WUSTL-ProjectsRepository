<?php
    require 'database.php';

    $form_name = $_POST['username'];
    $form_pass = crypt($_POST['password']);

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
    if ($count == 0) {

    $stmt = $mysqli->prepare("insert into user_information (username, password) values ('$form_name', '$form_pass')");
        if(!$stmt){
            printf("Query Prep Failed: %s\n", $mysqli->error);
            echo "Unable to add user :(";
            echo "<a href=\"login.php\">Return to Login Page</a>";
            exit;
        }

        $stmt->bind_param('sss', $form_name, $form_pass);

        $stmt->execute();

        $stmt->close();
        $_SESSION['user_id'] = $user_id;
        header("Location:/~nikkiwines/registeredHomepage.php"); // redirect to Reg
    }
    else {
        echo "User Already Exists! ";
        echo "<br>";
        echo "<a href=\"login.php\">Return to Login Page</a>";
    }
?>
