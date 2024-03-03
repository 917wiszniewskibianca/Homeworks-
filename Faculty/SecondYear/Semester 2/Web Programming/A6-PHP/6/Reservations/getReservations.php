<?php
include('/Applications/MAMP/htdocs/6/Resources/connection.php');
$connect = OpenConnection();

$cnp = $_GET["cnp"];
$roomID=$_GET['roomID'];
$category=$_GET['category'];
$type =$_GET['type'];
$price =$_GET["price"];
$hotel =$_GET["hotel"];
$startDate =$_GET["startDate"];
$endDate =$_GET["endDate"];

$query="";

$query = "SELECT * FROM rooms JOIN reservations ON rooms.roomID = reservations.roomID WHERE cnp = '$cnp'";
$result = mysqli_query($connect, $query);

if(mysqli_num_rows($result)>0){
    while ($row = mysqli_fetch_array($result)){
        echo "<tr>";
        echo "<td>".$row['roomID']."</th>";
        echo "<td>".$row['category']."</th>";
        echo "<td>".$row['type']."</th>";
        echo "<td>".$row['hotel']."</th>";
        echo "<td>".$row['price']."</th>";
        echo "<td>".$row['startDate']."</th>";
        echo "<td>".$row['endDate']."</th>";
        echo "</tr>";
    }

}

CloseConnection($connect);

?>