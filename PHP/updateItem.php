<?php
//create connection
$mysqli = new mysqli("cs-database.cs.loyola.edu","ijleppo","1740533","jajeimo");

//get variables from app
$title = $_POST['ItemTitle'];
$name = $_POST['Name'];
$chb = $_POST['CHB'];

//update currentHighestBid in database
$sql = "update items set currentHighestBid = '$chb' where title = '$title';";
$result = $mysqli->query($sql);
if($result === FALSE)
{
    echo "something went wrong - Modified not updated";
}
$rows = $mysqli->affected_rows;


//update currentHighestBidder in database
$sql2 = "update items set currentHighestBidder = '$name' where title = '$title';";
$result = $mysqli->query($sql2);
if($result === FALSE)
{
    echo "something went wrong - ItemsBidOn not updated";
}
$rows = $mysqli->affected_rows;

mysql_close( $mysqli );
?>

~                       
