<?php
include('/Applications/MAMP/htdocs/6/Resources/connection.php');
$connect = OpenConnection();

$cnp = $_POST["cnp"];
$roomID=$_POST['roomID'];
$startDate =$_POST["startDate"];
$endDate =$_POST["endDate"];


$query = "DELETE FROM reservations WHERE cnp = '$cnp' AND roomID = '$roomID' AND startDate = '$startDate' AND endDate = '$endDate'";
$result = mysqli_query($connect, $query);

if (!$result)
    echo 'Reservation can not be canceled!';
else
    echo 'Reservation canceled successfully!';

CloseConnection($connect);

?>