<?php

  session_start();
  $mysqli = new mysqli('localhost', 'root', 'root', 'calendar');

  if ($mysqli -> connect_errno) {
    printf ("Connection Failed: %s\n", $mysqli -> connect_error);
    exit;
  }
?>
