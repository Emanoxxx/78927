<?php
$server="localhost";
$username="amary";
$password="lux34";
$db="tw";

// Create connection
$connection = new mysqli($server, $username, $password,$db);

if(!$connection) {
    die ("Connection failed: " . mysqli_connect_error());
}
?>