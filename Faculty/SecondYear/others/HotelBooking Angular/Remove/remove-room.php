<?php


include('/Applications/MAMP/htdocs/HotelBooking/Resources/connection.php');
$connection = OpenConnection();

$id = $_POST['id'];

$query = "DELETE FROM rooms WHERE id = '$id'";
$result = mysqli_query($connection, $query);

if (!$result) {
    echo 'Could not delete the room!';
} else {
    echo 'Room deleted successfully!';
}

CloseConnection($connection);
