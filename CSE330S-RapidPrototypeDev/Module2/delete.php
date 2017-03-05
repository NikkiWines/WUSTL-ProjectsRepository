<?php
session_start();

// get filename and username
$filename = $_GET['file'];
$username = trim($_SESSION['username']);

//delete file
unlink("/srv/uploads/$username/$filename");
echo "The file has been deleted";
echo "<br>";
echo "<a href=\"upload.php\">Return to Uploads Page</a>";
exit;

?>
