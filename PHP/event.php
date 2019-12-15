<?php
//create connection
$mysqli = new mysqli("cs-database.cs.loyola.edu","ijleppo","1740533","jajeimo");

//pull from table
$sql = "select image, description, start, end from event;";

$result = $mysqli->query($sql);
if($result === FALSE)
{
    echo "something went wrong";
}
if(mysqli_num_rows($result) > 0)
{
	while( $row = $result->fetch_assoc( ) )
        {
		echo( "" . base64_encode($row['image']) . " , " . $row['description'] . " , " . $row['start'] .  " , " . $row['end'] );
        }
}

mysql_close( $mysqli );
?>

