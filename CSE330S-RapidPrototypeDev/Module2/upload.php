<html>
<head>
<title>File Sharing Site: Upload Page</title>
</head>
<body>
 <h1> File Sharing: Upload Page</h1>

<p><?php
session_start();
$username = trim($_SESSION['username']);
echo $username; ?>, you have successfully logged in! </p>

    <fieldset>
      <legend>Upload a File: </legend>
      <form enctype="multipart/form-data" action="userupload.php" method="POST">

      		<input type="hidden" name="MAX_FILE_SIZE" value="20000000" />
      		<label for="uploadfile_input">Choose a file to upload:</label> <input name="uploadedfile" type="file" id="uploadfile_input" />
          <br>
          <input type="submit" value="Upload File" />

      </form>

  </fieldset>
  <fieldset>
    <legend> Uploaded Files: </legend>
    <?php
    foreach (scandir("/srv/uploads/$username") as $file) {
      if ($file != "." && $file != "..") {
        echo "<a href=\"view.php?file=$file\">$file</a>";
        echo " ";
        echo "<a href=\"download.php?file=$file\">Download</a>";
        echo " ";
        echo "<a href=\"delete.php?file=$file\">Delete</a><br><br>";
      }
    }

    ?>
    </fieldset>
    <br>

  <form method="post" action="logout.php">
    <input type="submit" name="logout" value="Logout"/>
  </form>

</body>
</html>
