<?php
//connect
$mysqli = new mysqli("cs-database.cs.loyola.edu","ijleppo","1740533","jajeimo");

//get variables
date_default_timezone_set("America/New_York");
$FirstName = $_POST['FirstName'];
$LastName = $_POST['LastName'];
$email = $_POST['Email'];
$password = $_POST['Password'];
$date = date('Y-m-d h:i:s');

$sql = "select * from users where email like '$email';";
$result = $mysqli->query($sql);

//check for duplicate addition to table
if(mysqli_num_rows($result) > 0)
{
        echo("That email is already in use. Try a different one.");
}
else //if unique email, add to table
{
	$sql2 = "insert into users(FirstName, LastName, Email, Password, Created) values('$FirstName','$LastName','$email','$password','$date');";
        echo("$FirstName $LastName $email $password");
	$result2 = $mysqli->query($sql2);
	if($result2 === FALSE)
	{
		echo("Error caused -idk");
	}
}

mysql_close( $mysqli );
?>
