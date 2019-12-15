<?php
//create connection
$mysqli = new mysqli("cs-database.cs.loyola.edu","ijleppo","1740533","jajeimo");

//get variables
$email = $_POST['Email'];
$password = $_POST['Password'];
$FirstName = $_GET['FirstName'];
$LastName = $_GET['LastName'];

$sql = "select * from users where email like '$email' and password like '$password';";

//insert into table
$result = $mysqli->query($sql);

if(mysqli_num_rows($result) > 0)
{
	$sql2 = "select FirstName, LastName, ItemsBidOn from users where email like '$email' and password like '$password';";
	$result = $mysqli->query($sql2);
	while( $row = $result->fetch_assoc( ) )
	{
                echo( "$email,$password," . $row['FirstName'] . "," . $row['LastName'] . "," . $row['ItemsBidOn'] );
	}
}

mysql_close( $mysqli );
?>

