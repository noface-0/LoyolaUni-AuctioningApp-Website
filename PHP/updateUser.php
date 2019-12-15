<?php
//create connection
$mysqli = new mysqli("cs-database.cs.loyola.edu","ijleppo","1740533","jajeimo");

//get variables
$itemsBidOn = $_POST['ItemsBidOn'];
$FirstName = $_POST['FirstName'];
$LastName = $_POST['LastName'];
$date = date('Y-m-d h:i:s');

//get email
$sql3 = "select Email from users where FirstName = '$FirstName' and LastName = '$LastName';";
$result = $mysqli->query($sql3);
if($result === FALSE)
{
    echo "<br>something went wrong - email not found";
}
while( $row = $result->fetch_assoc( ) )
{
    $email = $row['Email'];
}


//update Modified
$sql = "update users set Modified = '$date' where email = '$email';";
$result = $mysqli->query($sql);
if($result === FALSE)
{
    echo "something went wrong - Modified not updated";
}
$rows = $mysqli->affected_rows;


//update ItemsBidOn
$sql2 = "update users set ItemsBidOn = '$itemsBidOn' where email = '$email';";
$result = $mysqli->query($sql2);
if($result === FALSE)
{
    echo "something went wrong - ItemsBidOn not updated";
}
$rows = $mysqli->affected_rows;

mysql_close( $mysqli );
?>

