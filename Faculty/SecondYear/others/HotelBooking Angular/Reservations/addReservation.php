<?php

include('/Applications/MAMP/htdocs/HotelBooking/Resources/connection.php');

$connection = OpenConnection();

$firstName = $_POST['firstName'];
$lastName = $_POST['lastName'];
$roomId = $_POST['roomId'];
$startDate = $_POST['startDate'];
$endDate = $_POST['endDate'];

$query = "INSERT INTO reservations (firstName, lastName, roomId, startDate,endDate) values ('$firstName', '$lastName', '$roomId', '$startDate','$endDate')";
$result = mysqli_query($connection, $query);

if (!$result)
    echo 'Could not add new reservation!';
else
    echo 'Reservation added successfully!';

CloseConnection($connection);

?>