<?php

include('/Applications/MAMP/htdocs/HotelBooking/Resources/connection.php');
$connect = OpenConnection();


$roomId = $_GET["roomId"];

$query = "";

$query = "SELECT * FROM rooms WHERE roomId = '$roomId'";

$result = mysqli_query($connect, $query);

if (mysqli_num_rows($result) > 0) {
    echo "hello";
    $row = mysqli_fetch_array($result);
    $category = $row["category"];
    $type = $row["type"];
    $price = $row["price"];
    $hotel = $row["hotel"];
    $result = array("$category", "$type", "$price", "$hotel");
    $myJSON = json_encode($result);
    echo $myJSON;
} else
    echo 'Room with this ID not found!';

CloseConnection($connect);

?>