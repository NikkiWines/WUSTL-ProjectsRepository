<!doctype html>
<html>
<head><title>Calculator</title> </head>
<body>
<strong>Calculator</strong>
<br>
<br>
For your numbers:
<?php
$value1= $_GET["value1"];
$value2= $_GET["value2"];
  echo $_GET["value1"];
?>
 and
<?php
  echo $_GET["value2"];
?>
<br>
<br>

You chose:
<?php
if ($_GET["calc"] == 'Addition') {
  echo "Addition <br> <br>";
  echo "Your calculation is: ";
    echo "$value1 + $value2 = ";
    echo $value1 + $value2 ;
  }
else if ($_GET["calc"] == 'Subtraction') {
  echo "Subtraction <br><br>";
  echo "<br>";
  echo "Your calculation is: ";
  echo "$value1 - $value2 = ";
  echo $value1 - $value2 ;
  }
else if ($_GET["calc"] == 'Multiplication') {
  echo "Multiplication <br><br>";
  echo "Your calculation is: ";
  echo "$value1 * $value2 = " ;
  echo $value1 * $value2 ;
  }
else {
echo "Division <br> <br>";
 if ($value2 == 0) {
   echo "BUT you can't divide by zero! <br>";
 }
 else {
 echo "Your calculation is: ";
   echo "$value1 / $value2 = " ;
   echo $value1 / $value2 ;
 }
}
?>
</body>
</html>
