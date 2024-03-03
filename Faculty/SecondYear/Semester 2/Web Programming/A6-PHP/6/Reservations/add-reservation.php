<?php
include('/Applications/MAMP/htdocs/6/Resources/connection.php');
$connect = OpenConnection();

$roomID=$_POST['roomID'];
$cnp =$_POST['cnp'];
$lastName =$_POST["lastName"];
$firstName =$_POST["firstName"];
$startDate =$_POST["startDate"];
$endDate =$_POST["endDate"];

$query = "INSERT INTO reservations (roomID,cnp,lastName,firstName,startDate,endDate) VALUES ('$roomID', '$cnp', '$lastName', '$firstName', '$startDate', '$endDate')";
$result = mysqli_query($connect, $query);

if (!$result)
    echo 'Could not add new reservation!';
else
    echo 'reservation added successfully!';

CloseConnection($connect);

?>