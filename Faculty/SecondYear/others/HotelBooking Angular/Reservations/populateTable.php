<?php
include('/Applications/MAMP/htdocs/HotelBooking/Resources/connection.php');

$connection = OpenConnection();

$reservationId= $_GET['id'];
$roomId = $_GET['roomId'];
$category = $_GET['category'];
$type = $_GET['type'];
$price = $_GET["price"];
$hotel = $_GET["hotel"];
$startDate = $_GET["startDate"];
$endDate = $_GET["endDate"];

$query = "";

$query = "SELECT * FROM rooms JOIN reservations ON rooms.id = reservations.roomId WHERE roomId = '$roomId'";
$query1= "SELECT * FROM reservations  JOIN rooms  ON reservations.roomId = rooms.id  WHERE reservations.id ='$reservationId'";

$result = mysqli_query($connection, $query1);

if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_array($result)) {
        echo "<tr>";
        echo "<td>" . $reservationId . "</th>";
        echo "<td>" . $row['roomId'] . "</th>";
        echo "<td>" . $row['category'] . "</th>";
        echo "<td>" . $row['type'] . "</th>";
        echo "<td>" . $row['hotel'] . "</th>";
        echo "<td>" . $row['price'] . "</th>";
        echo "<td>" . $row['startDate'] . "</th>";
        echo "<td>" . $row['endDate'] . "</th>";
        echo "</tr>";
    }

}


CloseConnection($connection);

?>