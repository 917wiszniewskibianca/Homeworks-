<?php

include('/Applications/MAMP/htdocs/HotelBooking/Resources/connection.php');

$connection = OpenConnection();

$id =$_POST['id'];


$query = "DELETE FROM reservations where id = '$id'";
$result = mysqli_query($connection, $query);

if ($result)
    echo 'Reservation canceled!';
else
    echo 'Could not cancel reservation!';

CloseConnection($connection);

?>