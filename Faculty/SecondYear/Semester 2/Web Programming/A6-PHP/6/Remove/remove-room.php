<?php
include('/Applications/MAMP/htdocs/6/Resources/connection.php');
$connect = OpenConnection();

$category=$_POST['category'];
$type =$_POST['type'];
$price =$_POST['price'];
$hotel =$_POST["hotel"];
$roomID = $_POST["roomID"];


$query = "DELETE FROM rooms WHERE category = '$category' AND price = '$price' AND type = '$type' AND hotel = '$hotel' AND roomID = '$roomID'";
$result = mysqli_query($connect, $query);

if (!$result)
    echo 'Room can not be deleted!';
else
    echo 'Room was deleted!';

CloseConnection($connect);

?>