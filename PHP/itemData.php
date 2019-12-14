<?php
//create connection
$mysqli = new mysqli("cs-database.cs.loyola.edu","ijleppo","1740533","jajeimo");

//pull from table
$sql = "select title, description, minInc, tags, currentHighestBid, image, currentHighestBidder from items;";

$result = $mysqli->query($sql);
if($result === FALSE)
{
    echo "something went wrong";
}
while( $row = $result->fetch_assoc( ) )
{
	echo( "" . $row['title'] . ";" . $row['description'] . ";" . $row['minInc'] .  ";" . $row['tags'] . ";" . $row['currentHighestBid'] . ";" . base64_encode($row['image']) . ";" . $row['currentHighestBidder'] . ";" );
}

mysql_close( $mysqli );
?>
