<?php
include('/Applications/MAMP/htdocs/6/Resources/connection.php');
$connect = OpenConnection();


$roomID = $_GET["roomID"];

$query="";

$query = "SELECT * FROM rooms WHERE roomID = '$roomID'";

$result = mysqli_query($connect, $query);

if(mysqli_num_rows($result)>0){
    $row = mysqli_fetch_array($result);
    $category = $row["category"];
    $price = $row["price"];
    $type = $row["type"];
    $hotel = $row["hotel"];
    $result = array("$category","$type","$price","$hotel");
    $myJSON = json_encode($result);
    echo $myJSON;
}
else
    echo 'Room with this ID not found!';

CloseConnection($connect);

?>