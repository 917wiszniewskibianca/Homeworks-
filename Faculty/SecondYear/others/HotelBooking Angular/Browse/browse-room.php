<?php
include('/Applications/MAMP/htdocs/HotelBooking/Resources/connection.php');
$connect = OpenConnection();


$category=$_GET['category'];
$type =$_GET['type'];
$price =$_GET["price"];
$hotel =$_GET["hotel"];
$pg = $_GET["pg"];

$offset = intval($pg) * 4 ;

$query="";

if($price != "")
    $query = "SELECT * FROM rooms WHERE category LIKE '%$category%' AND type LIKE '%$type%' AND price <= '$price'AND hotel LIKE '%$hotel%' LIMIT $offset, 4";
else
    $query = "SELECT * FROM rooms WHERE category LIKE '%$category%' AND type LIKE '%$type%' AND hotel LIKE '%$hotel%' LIMIT $offset, 4";



$result = mysqli_query($connect, $query);

if(mysqli_num_rows($result)>0){
    while ($row = mysqli_fetch_array($result)){
        echo "<tr>";
        echo "<td>".$row['category']."</th>";
        echo "<td>".$row['type']."</th>";
        echo "<td>".$row['price']."</th>";
        echo "<td>".$row['hotel']."</th>";
        echo "</tr>";
    }

}

CloseConnection($connect);

?>